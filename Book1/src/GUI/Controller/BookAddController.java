package GUI.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import q.util.MyValidate;
import q.util.myUtil;

import java.net.URL;
import java.util.ResourceBundle;

import Book.entity.Book;
import Factory.DaoFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.DatePicker;

import javafx.scene.control.ChoiceBox;

public class BookAddController extends MyController2 implements MyController1, Initializable{
	@FXML
	private TextField tx_BookName;
	@FXML
	private ChoiceBox cb_BookSoft;
	@FXML
	private TextField tx_Price;
	@FXML
	private TextField tx_Author;
	@FXML
	private TextField tx_Press;
	@FXML
	private DatePicker dp_Pdate;
	@FXML
	private TextField tx_MaxNumber;
	@FXML
	private Button OK;
	@FXML
	private Button Cancel;
	
	private static ObservableList<String> choicelist = FXCollections.observableArrayList("马克思主义、列宁主义、毛泽东思想、邓小平理论","哲学、宗教","社会科学总论","政治、法律",
			"军事","经济","文化、科学、教育、体育","语言、文字","文学","艺术",
		"历史、地理","自然科学总论","数理科学和化学","天文学、地球科学","生物科学","医药、卫生","农业科学","工业技术","交通运输",
		"航空、航天","环境科学、劳动保护科学（安全科学)","综合性图书");


	// Event Listener on Button[#OK].onAction
	@FXML
	public void OKAction(ActionEvent event) {
		Book b = new Book();
		b.setBookName(myUtil.getString(tx_BookName.getText()));
		b.setBookSoft(myUtil.getString((String) cb_BookSoft.getValue()));
		b.setPrice(myUtil.String2Double(tx_Price.getText()));
		b.setAuthor(myUtil.getString(tx_Author.getText()));
		b.setPress(myUtil.getString(tx_Press.getText()));
		b.setPdate(myUtil.LocalDate2Date(dp_Pdate.getValue()));
		b.setMaxNumber(myUtil.String2Integer(tx_MaxNumber.getText()));
		if(!Bookcheck(b)) {
			return;
		}
		if(!DaoFactory.getBook().insert(b)) {
			Dialog.getinformationDialog("添加失败！");
			return;
		}
		CancelAction(null);
	}
	// Event Listener on Button[#Cancel].onAction
	@FXML
	public void CancelAction(ActionEvent event) {
		Stage stage = (Stage)tx_Author.getScene().getWindow();
		stage.close();
	}
	
	
	
	
	
	
	public boolean Bookcheck(Book b) {
		MyValidate myValidate = new MyValidate();
		myValidate.isEmpty(tx_BookName, b.getBookName(), "图书名称不能为空!");
		myValidate.isEmpty(tx_MaxNumber, myUtil.Number2String(b.getMaxNumber()),"库存量不能为空!");
		myValidate.isEmpty(tx_Price, myUtil.Double2String(b.getPrice()),"图书价格不能为空!");
		myValidate.isSelect(cb_BookSoft, "请选择一项！");
		
		if(myValidate.getResult()) {
			return true;
		}
		
		return false;
	}
	
	
	@Override
	public void setValue(Object object) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cb_BookSoft.getItems().addAll(choicelist);
		addFormatter(tx_MaxNumber, MyController1.INTEGER);
		addFormatter(tx_Price, MyController1.DOUBLE);
	}
}
