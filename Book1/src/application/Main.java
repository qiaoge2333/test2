package application;

import GUI.Controller.StageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		String [] names = {"Main","BorrowbookGui","CardGui","BookGui","AdminGui"};
		String [] s  = {"/GUI/Main.fxml","/GUI/BorrowbookGui.fxml",
				"/GUI/CardGui.fxml","/GUI/BookGui.fxml","/GUI/AdminGui.fxml"};
		
		for(int i = 0; i < s.length; i++) {
			StageController.LoadStage(names[i], s[i]);
		}
		
		
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/LoadGui.fxml"));
			Parent root = fxmlLoader.load();
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
