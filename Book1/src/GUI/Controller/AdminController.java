package GUI.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextField;

import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.WindowEvent;
import q.util.myUtil;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Book.entity.Admin;
import Factory.Contextmenu;
import Factory.DaoFactory;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.Pagination;



public class AdminController extends Controller2 implements Initializable, Controller1 {
	

	@FXML
	private TableView table_Admin;
	@FXML
	private TextField text_search;
	@FXML
	private RadioButton rbtn_No;
	@FXML
	private ToggleGroup admintoggle;
	@FXML
	private RadioButton rbtn_UserName;
	@FXML
	private Button btn_search;
	@FXML
	private Pagination page;
	
	//fxml的url
	public static final URL ADMINADD = AdminController.class.getResource("/GUI/AdminAdd.fxml");
	public static final URL ADMINUPDATE = AdminController.class.getResource("/GUI/AdminUpdate.fxml");
	//用户数组
	private final ObservableList<Admin> adminlist = FXCollections.observableArrayList();

	//页数
	private static SimpleIntegerProperty PAGE = new SimpleIntegerProperty();
	
	//最大页数
	private static SimpleIntegerProperty CountPage = new SimpleIntegerProperty();
	
	
	//SQL语句
	private String [] sql = {"select * from admin ",""," limit 0, " + MaxPage + ""};
	
	//右键菜单选项
	private static String [] selectName = {"添加","修改","删除"};
	private static ContextMenu c = Contextmenu.getInstance(selectName);
	public AdminController() {
		super(StageController.ADMIN, selectName);
	}
	
	

	// Event Listener on Button.onAction
	@FXML
	public void Back(ActionEvent event) {
		StageController.StageToggle(StageController.ADMIN, StageController.MAIN);
		
	}
	// Event Listener on Button[#btn_search].onAction
	@FXML
	public void Search(ActionEvent event) {
		List<Admin> admins = null;
		
		if (text_search.getText().equals("")) {
			sql[1] = " ";
		}else {
			sql[1] = " where " + (rbtn_No.isSelected()?" No = " + text_search.getText() +
					" ":"UserName like '%" + text_search.getText()  + "%'");
		}
		long number = DaoFactory.getAdmin().getCount(sql[1]);
		number = number/MaxPage +  (number%MaxPage == 0 ? 0 : 1);
		CountPage.set((int)number);
		page.currentPageIndexProperty().setValue(null);
		sql[2] = " limit 0, " + MaxPage + "" ;
		adminlist.clear();
		admins = DaoFactory.getAdmin().Search(myUtil.ToString(sql));
		if(admins!=null)adminlist.addAll(admins);	
	}
	
	@FXML
	public void ReFlash() {
		reflash();
	}
	
	//表格数据刷新
	public void reflash() {
		List<Admin>	admins = null;
		adminlist.clear();
		admins  = DaoFactory.getAdmin().Search(myUtil.ToString(sql));
		if(admins !=null)
			adminlist.addAll(admins);
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
		tableInit(table_Admin, adminlist, Admin.elementNames);
		pageInit();
		menuInit(table_Admin, c);
		
	}


}
