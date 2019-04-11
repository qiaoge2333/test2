package Factory;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class Contextmenu extends ContextMenu{
	
	
	private static String select = "";
	
	public Contextmenu() {
		
	}
	
	//右键菜单构造
	public Contextmenu(String [] arr) {
		EventHandler<ActionEvent> MenuSelectAction = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				MenuItem temp = (MenuItem)event.getTarget();
				select = temp.getText();
			}
		};
		for (String s : arr) {
			MenuItem t = new MenuItem(s);
			t.setOnAction(MenuSelectAction);
			getItems().add(t);
		}
		
	}
	
	public void setDisable(boolean brr []) {
		for(int i = 0; i < brr.length; i++) {
			getItems().get(i).setDisable(brr[i]);
		}
		
	}
	
	public static String getSelect() {
		String s = new String(select);
		select = "";
		return s;
	}
	
	public static ContextMenu getInstance(String [] arr) {
		Contextmenu instance = new Contextmenu(arr);
		return instance;
	}
	
}
