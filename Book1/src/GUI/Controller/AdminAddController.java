package GUI.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import q.util.MyValidate;
import q.util.myUtil;

import java.net.URL;
import java.util.ResourceBundle;

import Book.entity.Admin;
import Factory.DaoFactory;
import javafx.event.ActionEvent;

import javafx.scene.control.PasswordField;

public class AdminAddController extends MyController2 implements MyController1, Initializable{
	@FXML
	private TextField UserName;
	@FXML
	private PasswordField Password;

	// Event Listener on Button.onAction
	@FXML
	public void Submit(ActionEvent event) {
		Admin admin = new Admin();
		String userName = UserName.getText();
		String password = Password.getText();
		
		admin.setUserName(myUtil.getString(userName));
		admin.setPassword(myUtil.getString(password));
		if(!AdminCheck(admin)) {
			return;
		}
		
		if(!DaoFactory.getAdmin().insert(admin)) {
			Dialog.getinformationDialog("账号添加失败！");
		}
		
		Cancel(null);
	}
	// Event Listener on Button.onAction
	@FXML
	public void Cancel(ActionEvent event) {
		Stage stage = (Stage)UserName.getScene().getWindow();
		stage.close();
	}
	
	
	public boolean AdminCheck(Admin admin) {
		MyValidate myValidate = new MyValidate();
		myValidate.isEmpty(UserName, admin.getUserName(),"用户名不能为空!");
		myValidate.isEmpty(Password, admin.getPassword(), "密码不能为空!");
		return myValidate.getResult();
	}
	
	@Override
	public void setValue(Object object) {
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
