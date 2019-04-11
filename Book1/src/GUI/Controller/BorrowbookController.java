package GUI.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.ToggleGroup;
import q.util.myUtil;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Book.entity.Admin;
import Book.entity.Borrowbooks;
import Factory.Contextmenu;
import Factory.DaoFactory;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.RadioButton;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableView;

import javafx.scene.control.Pagination;

public class BorrowbookController extends Controller2 implements Controller1, Initializable {
	
	@FXML
	private TableView table_borrowbook;
	@FXML
	private TextField text_search;
	@FXML
	private RadioButton rbtn_BookNo;
	@FXML
	private ToggleGroup search;
	@FXML
	private RadioButton rbtn_CardNo;
	@FXML
	private CheckBox hasBorrow;
	@FXML
	private Button btn_search;
	@FXML
	private Pagination page;

	//fxml的url
	public static final URL BORROWADD = BorrowbookController.class.getResource("/GUI/BorrowAdd.fxml");
	public static final URL BORROWRETURN = BorrowbookController.class.getResource("/GUI/BorrowReturn.fxml");
	public static final URL BORROWLOST = BorrowbookController.class.getResource("/GUI/BorrowLost.fxml");
	
	//用户数组
	private final ObservableList<Borrowbooks> borrowlist = FXCollections.observableArrayList();

	//页数
	private static SimpleIntegerProperty PAGE = new SimpleIntegerProperty();
	
	//最大页数
	private static SimpleIntegerProperty CountPage = new SimpleIntegerProperty();
	
	
	//SQL语句
	private String [] sql = {"select * from borrowbooks ",""," limit 0, " + MaxPage + ""};
	
	//右键菜单选项
	private static String [] selectName = {"借书","还书","报失"};
	private static ContextMenu c = Contextmenu.getInstance(selectName);
	public BorrowbookController() {
		super(StageController.BORROW, selectName);
	}
	// Event Listener on Button.onAction
	@FXML
	public void Back(ActionEvent event) {
		StageController.StageToggle(StageController.BORROW, StageController.MAIN);
	}
	
	@FXML
	public void Reflash() {
		reflash();
	}
	
	// Event Listener on Button[#btn_search].onAction
	@FXML
	public void Search(ActionEvent event) {
		List<Borrowbooks> borrow = null;
		
		if (text_search.getText().equals("")) {
			sql[1] = "" + (hasBorrow.isSelected()?" where cd = 0":"");
		}else {
			sql[1] = " where " + (rbtn_CardNo.isSelected()?"CardNo = " + text_search.getText() +
					" ":"BookNo = '" + text_search.getText()  + "'") + (hasBorrow.isSelected()?" and cd = 0":"");
		}
		long number = DaoFactory.getBorrowbooks().getCount(sql[1]);
		number = number/MaxPage +  (number%MaxPage == 0 ? 0 : 1);
		CountPage.set((int)number);
		page.currentPageIndexProperty().setValue(null);
		sql[2] = " limit 0, " + MaxPage;
		borrowlist.clear();
		borrow = DaoFactory.getBorrowbooks().Search(myUtil.ToString(sql));
		if(borrow!=null)borrowlist.addAll(borrow);
	}
	
	//表格数据刷新
	public void reflash() {
		List<Borrowbooks>	borrows = null;
		borrowlist.clear();
		borrows  = DaoFactory.getBorrowbooks().Search(myUtil.ToString(sql));
		if(borrows !=null)
			borrowlist.addAll(borrows);
	}
	
	//翻页功能初始化
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
		tableInit(table_borrowbook, borrowlist, Borrowbooks.elementNames);
		pageInit();
		menuInit(table_borrowbook, c);
	}
}
