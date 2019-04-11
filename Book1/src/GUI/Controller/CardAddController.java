package GUI.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import Book.entity.Card;
import Factory.DaoFactory;
import javafx.event.ActionEvent;

import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import q.util.MyValidate;
import q.util.myUtil;

public class CardAddController extends MyController2 implements MyController1, Initializable{
	@FXML
	private Button submit;
	@FXML
	private TextField CardName;
	@FXML
	private TextField Mail;
	@FXML
	private TextField Phone;

	// Event Listener on Button[#submit].onAction
	@FXML
	public void Submit(ActionEvent event) {
		Card card = new Card();
		card.setCardName(myUtil.getString(CardName.getText()));
		card.setMail(myUtil.getString(Mail.getText()));
		card.setPhone(myUtil.getString(Phone.getText()));
		
		if(!CardCheck(card))return ;
		if(!DaoFactory.getCard().insert(card)) {
			Dialog.getinformationDialog("修改失败！");
			return;
		}
		
		Exit(null);
	}
	
	public boolean CardCheck(Card card) {
		MyValidate myValidate = new MyValidate();
		myValidate.isEmpty(Phone, card.getPhone(), "手机号码不能为空!");
		myValidate.isEmpty(CardName, card.getCardName(), "借书卡用户名不能为空!");
		myValidate.checkLength(Phone, card.getPhone(), 11, 11, "手机号码的长度为11位数字!");
		myValidate.Reg(Mail, card.getMail(), "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", "邮箱格式不对!");
		return myValidate.getResult();
	}
	
	
	// Event Listener on Button.onAction
	@FXML
	public void Exit(ActionEvent event) {
		Stage stage = (Stage)Mail.getScene().getWindow();
		stage.close();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		addFormatter(Phone, MyController1.INTEGER);
	}
	@Override
	public void setValue(Object object) {
		// TODO Auto-generated method stub
		
	}
}
