package GUI.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import Book.entity.Admin;
import Factory.DaoFactory;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class LoadController implements Initializable{
	@FXML
	private TextField UserName;
	@FXML
	private PasswordField Password;
	@FXML
	private Button Login;
	@FXML
	private Button Cancel;
	
	
	// Event Listener on GridPane.onKeyPressed
	@FXML
	public void Enter(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER) {
			Login();
		}
		
	}
	// Event Listener on Button[#Login].onAction
	@FXML
	public void LoginAction(ActionEvent event) {
		Login();
	}
	// Event Listener on Button[#Cancel].onAction
	@FXML
	public void CancelAction(ActionEvent event) {
		Stage stage = (Stage)UserName.getScene().getWindow();
		stage.close();
	}
	
	
	private void Login() {
		Admin admin = new Admin();
		admin.setUserName(UserName.getText());
		admin.setPassword(Password.getText());
		String s = DaoFactory.getAdmin().Login(admin);
		if((s.equals("密码错误")||s.equals("用户名不存在"))) {
			Dialog.getinformationDialog(s);
			return;
		}
		
		Stage stage = (Stage) UserName.getScene().getWindow();
		
		StageController.StageShow("Main",stage.getX(),stage.getY());
		
		stage.close();
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
}
