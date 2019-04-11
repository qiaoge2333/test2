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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class AdminUpdateController implements MyController1, Initializable{
	@FXML
	private TextField UserName;
	@FXML
	private TextField UserNo;
	@FXML
	private TextField Password;
	
	private Admin oldAdmin = null;

	// Event Listener on Button.onAction
	@FXML
	public void Submit(ActionEvent event) {
		Admin admin = new Admin();
		admin.setNo(oldAdmin.getNo());
		admin.setPassword(myUtil.getString(Password.getText()));
		admin.setUserName(myUtil.getString(UserName.getText()));
		if(!AdminCheck(admin)) {
			return;
		}
		if(!DaoFactory.getAdmin().update(admin)) {
			Dialog.getinformationDialog("修改失败");
			return;
		}
		
		Cancel(null);
	
	}
	
	public boolean AdminCheck(Admin admin) {
		MyValidate myValidate = new MyValidate();
		myValidate.isEmpty(UserName, admin.getUserName(),"用户名不能为空!");
		myValidate.isEmpty(Password, admin.getPassword(),"密码不能为空!");
		return myValidate.getResult();
	}
	// Event Listener on Button.onAction
	@FXML
	public void Cancel(ActionEvent event) {
		Stage stage = (Stage)UserName.getScene().getWindow();
		stage.close();
	}
	@Override
	public void setValue(Object object) {
		oldAdmin = (Admin)object;
		UserName.setText(oldAdmin.getUserName());
		Password.setText(oldAdmin.getPassword());
		UserNo.setText(myUtil.Number2String(oldAdmin.getNo()));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
