package GUI.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import Book.entity.Borrowbooks;
import Factory.DaoFactory;
import javafx.event.ActionEvent;

import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import q.util.MyValidate;
import q.util.myUtil;

public class BorrowAddController extends MyController2 implements MyController1, Initializable{
	@FXML
	private TextField BookNo;
	@FXML
	private TextField CardNo;

	// Event Listener on Button.onAction
	@FXML
	public void Submit(ActionEvent event) {
		Borrowbooks borrow = new Borrowbooks();
		borrow.setBookNo(myUtil.String2Long(BookNo.getText()));
		borrow.setCardNo(myUtil.String2Long(CardNo.getText()));
		borrow.setBdate(myUtil.getDate());
		if(!BorrowCheck(borrow))return;
		
		if(!DaoFactory.getBorrowbooks().borrowbook(borrow)) {
			Dialog.getinformationDialog("添加失败!");
			return;
		}
		
		Dialog.getinformationDialog("添加成功");
		
		Exit(null);
	}
	
	public boolean BorrowCheck(Borrowbooks b) {
		MyValidate myValidate = new MyValidate();
		myValidate.isEmpty(BookNo, myUtil.Number2String(b.getBookNo()), "租借图书编号不能为空!");
		myValidate.isEmpty(CardNo, myUtil.Number2String(b.getCardNo()), "借书卡编号不能为空!");
		return myValidate.getResult();
	}
	
	// Event Listener on Button.onAction
	@FXML
	public void Exit(ActionEvent event) {
		Stage stage = (Stage)BookNo.getScene().getWindow();
		stage.close();
	}
	@Override
	public void setValue(Object object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		addFormatter(BookNo, MyController1.INTEGER);
		addFormatter(CardNo, MyController1.INTEGER);
		
	}
}
