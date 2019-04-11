package GUI.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import Factory.DaoFactory;
import javafx.event.ActionEvent;

public class MainController implements Controller1, Initializable{
	@FXML
	private Button BookControll;
	@FXML
	private Button CardControll;
	@FXML
	private Button BorrowControll;
	@FXML
	private Button UserControll;
	

	// Event Listener on Button[#BookControll].onAction
	@FXML
	public void bookControll(ActionEvent event) {
		stageToggle(StageController.BOOK);
	}
	// Event Listener on Button[#CardControll].onAction
	@FXML
	public void cardControll(ActionEvent event) {
		stageToggle(StageController.CARD);
	}
	// Event Listener on Button[#BorrowControll].onAction
	@FXML
	public void borrowControll(ActionEvent event) {
		stageToggle(StageController.BORROW);
	}
	// Event Listener on Button[#UserControll].onAction
	@FXML
	public void userControll(ActionEvent event) {
		if(UserPassword())
			stageToggle(StageController.ADMIN);
	}
	// Event Listener on Button.onAction
	@FXML
	public void Exit(ActionEvent event) {
		Stage stage = (Stage) UserControll.getScene().getWindow();
		stage.close();
	}
	
	//密码输入Dialog
	public boolean UserPassword() {
		Dialog<Boolean> dialog = new Dialog<>();
		dialog.setTitle("账号修改权限");
		dialog.setHeaderText("请输入密码");
		

		ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
		
		PasswordField password = new PasswordField();
		password.setPromptText("请输入密码");
		dialog.getDialogPane().getChildren().add(password);
		
		dialog.getDialogPane().setContent(password);

		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == loginButtonType) {
		        return true;
		    }
		    return false;
		});
		
		Optional<Boolean> optional =  dialog.showAndWait();
		
		if(optional.get()) {
			boolean result = DaoFactory.getAdmin().getController(password.getText());
			if(result == false) GUI.Controller.Dialog.getinformationDialog("密码错误!");
			return result;
		}
		
		
		
		return false;
		
	}
	
	
	
	private void stageToggle(String s) {
		StageController.StageToggle(StageController.MAIN, s);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	@Override
	public void reflash() {
		// TODO Auto-generated method stub
		
	}
}
