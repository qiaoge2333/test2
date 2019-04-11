package GUI.Controller;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Modality;

public class Dialog {
	
	
	public static void getinformationDialog(String contentText) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.setContentText(contentText);
		alert.show();
	}
	
	public static boolean getconfirmationDialog(String contentText) {
		boolean result = false;
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.setContentText(contentText);
		Optional<ButtonType> btnType = alert.showAndWait();
		result = btnType.get().equals(ButtonType.OK);
		
		return result;
		
	}
	
}
