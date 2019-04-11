package GUI.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextField;

import javafx.scene.control.ToggleGroup;
import q.util.myUtil;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Book.entity.Admin;
import Book.entity.Book;
import Factory.Contextmenu;
import Factory.DaoFactory;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.RadioButton;

import javafx.scene.control.TableView;

import javafx.scene.control.Pagination;

public class BookController extends Controller2 implements Initializable, Controller1 {
	
	@FXML
	private TableView table_Book;
	@FXML
	private TextField text_search;
	@FXML
	private RadioButton rbtn_BookNo;
	@FXML
	private ToggleGroup booktoggle;
	@FXML
	private RadioButton rbtn_BookName;
	@FXML
	private Button btn_search;
	@FXML
	private Pagination page;
	
	//fxml的url
	public static final URL BOOKADD = AdminController.class.getResource("/GUI/BookAdd.fxml");
	public static final URL BOOKUPDATE = AdminController.class.getResource("/GUI/BookUpdate.fxml");

	
	//用户数组
	private final ObservableList<Book> booklist = FXCollections.observableArrayList();

	//页数
	private static SimpleIntegerProperty PAGE = new SimpleIntegerProperty();
	
	//最大页数
	private static SimpleIntegerProperty CountPage = new SimpleIntegerProperty();
	
	//SQL语句
	private String [] sql = {"select * from book ",""," limit 0, " + MaxPage + ""};
	
	//右键菜单选项
	private static String [] selectName = {"添加","修改","删除"};
	private static ContextMenu c = Contextmenu.getInstance(selectName);
	public BookController() {
		super(StageController.BOOK, selectName);
		// TODO Auto-generated constructor stub
	}
	
	@FXML
	public void Reflash() {
		reflash();
	}
	
	// Event Listener on Button.onAction
	@FXML
	public void Back(ActionEvent event) {
		StageController.StageToggle(StageController.BOOK, StageController.MAIN);
	}
	// Event Listener on Button[#btn_search].onAction
	@FXML
	public void Search(ActionEvent event) {
		List<Book> b = null;
		if(text_search.getText().equals("")) {
			sql[1] = "";
		}else {
			sql[1] = " where " + (rbtn_BookName.isSelected()?"BookName like '%" + text_search.getText() + "%'":"BookNo = '"+ text_search.getText() +"'");
		}
		long number = DaoFactory.getBook().getCount(sql[1]);
		number = number/MaxPage +  (number%MaxPage == 0 ? 0 : 1);
		CountPage.set((int)number);
		page.currentPageIndexProperty().setValue(null);
		sql[2] = " limit 0, " + MaxPage;
		booklist.clear();
		b = DaoFactory.getBook().Search(myUtil.ToString(sql));
		if(b!=null) booklist.addAll(b);
	}
	//表格数据刷新
	public void reflash() {
		List<Book>	books = null;
		booklist.clear();
		books  = DaoFactory.getBook().Search(myUtil.ToString(sql));
		if(books !=null)
			booklist.addAll(books);
	}

	public void pageInit() {
		PAGE.bind(page.currentPageIndexProperty());
		page.pageCountProperty().bind(CountPage);
		page.setMaxPageIndicatorCount(MaxPage);
		PAGE.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				sql[2] = " limit " + ((int)newValue * MaxPage) + " , " + MaxPage + ";";
				reflash();
			}
		});
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tableInit(table_Book, booklist, Book.elementNames);
		pageInit();
		menuInit(table_Book, c);
		
	}
}
