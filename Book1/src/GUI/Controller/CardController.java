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
import Book.entity.Card;
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

public class CardController extends Controller2 implements Controller1, Initializable{
	public CardController(String Type, String[] sN) {
		super(Type, sN);
		// TODO Auto-generated constructor stub
	}
	@FXML
	private TextField text_search;
	@FXML
	private RadioButton rbtn_CardNo;
	@FXML
	private ToggleGroup search;
	@FXML
	private RadioButton rbtn_CardName;
	@FXML
	private Button btn_search;
	@FXML
	private TableView table_card;
	@FXML
	private Pagination page;
	
	//借书卡添加和修改的界面fxml路径
	public static final URL BOOKADD = CardController.class.getResource("/GUI/CardAdd.fxml");	
	public static final URL BOOKUPDATE = CardController.class.getResource("/GUI/CardUpdate.fxml");
	
	//用户数组
	private final ObservableList<Card> cardlist = FXCollections.observableArrayList();

	//页数
	private static SimpleIntegerProperty PAGE = new SimpleIntegerProperty();
	
	//最大页数
	private static SimpleIntegerProperty CountPage = new SimpleIntegerProperty();
	
	
	//SQL语句
	private String [] sql = {"select * from card ",""," limit 0, " + MaxPage + ""};
	
	//右键菜单选项
	private static String [] selectName = {"添加","修改","删除"};
	private static ContextMenu c = Contextmenu.getInstance(selectName);
	public CardController() {
		super(StageController.CARD, selectName);
	}
	
	
	
	@FXML
	public void Reflash() {
		reflash();
	}

	// Event Listener on Button.onAction
	@FXML
	public void Back(ActionEvent event) {
		StageController.StageToggle(StageController.CARD, StageController.MAIN);
	}
	// Event Listener on Button[#btn_search].onAction
	@FXML
	public void Search(ActionEvent event) {
		List<Card> cards = null;
		
		if (text_search.getText().equals("")) {
			sql[1] = " ";
		}else {
			sql[1] = " where " + (rbtn_CardNo.isSelected()?"CardNo = " + text_search.getText() +
					" ":"CardName like '%" + text_search.getText()  + "%'");
		}
		long number = DaoFactory.getCard().getCount(sql[1]);
		number = number/MaxPage +  (number%MaxPage == 0 ? 0 : 1);
		CountPage.set((int)number);
		page.currentPageIndexProperty().setValue(null);
		sql[2] = " limit 0, " + MaxPage;
		cardlist.clear();
		cards = DaoFactory.getCard().Search(myUtil.ToString(sql));
		if(cards!=null)cardlist.addAll(cards);	
	}
	
	//表格数据刷新
	public void reflash() {
		List<Card>cards = null;
		cardlist.clear();
		cards  = DaoFactory.getCard().Search(myUtil.ToString(sql));
		if(cards !=null)
			cardlist.addAll(cards);
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
		tableInit(table_card, cardlist,Card.elementNames);
		pageInit();
		menuInit(table_card, c);
	}
}
