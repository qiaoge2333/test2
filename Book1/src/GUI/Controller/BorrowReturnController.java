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
import q.util.myUtil;

public class BorrowReturnController extends MyController2 implements MyController1, Initializable {
	@FXML
	private TextField BookNo;
	@FXML
	private Tooltip BookNoTip;
	@FXML
	private TextField CardNo;
	@FXML
	private Tooltip CardNoTip;
	@FXML
	private TextField ID;
	@FXML
	private Tooltip BookNoTip1;
	
	private Borrowbooks oldBorrow = null;

	// Event Listener on Button.onAction
	@FXML
	public void Submit(ActionEvent event) {
		Borrowbooks borrow = new Borrowbooks();
		borrow.setId(oldBorrow.getId());
		borrow.setBookNo(myUtil.String2Long(BookNo.getText()));
		borrow.setCardNo(myUtil.String2Long(CardNo.getText()));
		borrow.setRdate(myUtil.getDate());
		if(!DaoFactory.getBorrowbooks().returnbook(borrow)) {
			Dialog.getinformationDialog("还书失败!");
			return;
		}
		
		Dialog.getinformationDialog("还书成功!");
		Exit(null);
	}
	// Event Listener on Button.onAction
	@FXML
	public void Exit(ActionEvent event) {
		Stage stage = (Stage)BookNo.getScene().getWindow();
		stage.close();
	}
	@Override
	public void setValue(Object object) {
		oldBorrow = (Borrowbooks)object;
		ID.setText(myUtil.Number2String(oldBorrow.getId()));
		BookNo.setText(myUtil.Number2String(oldBorrow.getBookNo()));
		CardNo.setText(myUtil.Number2String(oldBorrow.getCardNo()));
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
