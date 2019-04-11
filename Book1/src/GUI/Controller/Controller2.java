package GUI.Controller;

import java.io.IOException;
import java.net.URL;

import Book.entity.Admin;
import Book.entity.Borrowbooks;
import Book.entity.myEntity;
import Factory.Contextmenu;
import Factory.DaoFactory;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class Controller2 {
	
	private static String [] selectname = null;
	private String type = null;
	//最大显示页数
	public static int MaxPage = 10;
	
	public Controller2(String Type, String [] sN) {
		type = Type;
		selectname = sN;
	}
	
	//右键菜单初始化
	public void menuInit(TableView tableView,ContextMenu c) {
		tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent event) {
				Object object = tableView.getSelectionModel().getSelectedItem();
				if(event.getButton()==MouseButton.SECONDARY) {
					Window window = tableView.getScene().getWindow();
					double x = event.getSceneX() + window.getX() + 20;
					double y = event.getSceneY() + window.getY();
					c.show(tableView.getScene().getWindow(), x, y);
					if(object == null) {
						((Contextmenu)c).setDisable(new boolean [] {false,true,true});
					}else {
						((Contextmenu)c).setDisable(new boolean [] {false,false,false});
					}
					c.setOnHidden(new EventHandler<WindowEvent>() {
						public void handle(WindowEvent arg0) {
							String select = Contextmenu.getSelect();
							if(select == null)return;
							
							switch (select) {
								case "添加":{
									add(type, object);
								}break;
								case "修改":{
									update(type, object);
								}break;
								case "删除":{
									myEntity entity = (myEntity)object;
									delete(type, entity.getValue());
								}break;
								case "借书":{
									ShowGui(object, BorrowbookController.BORROWADD);
								}break;
								case "还书":{
									Borrowbooks b = (Borrowbooks)object;
									if(b.getCd()) {
										return;
									}
									ShowGui(object, BorrowbookController.BORROWRETURN);
								}break;
								case "报失":{
									Borrowbooks b = (Borrowbooks)object;
									if(b.getCd()) {
										return;
									}
									ShowGui(object, BorrowbookController.BORROWLOST);
								}
							}
						}
					});
					tableView.getSelectionModel().clearSelection();
					
				}
			}
		});
	}
	
	
	//删除
	public void delete(String s,long no) {
		//判断是否执行是否成功
		boolean result = false;
		String S = "";
		switch (s) {
			case StageController.BOOK:{
				result = DaoFactory.getBook().delete(no);
				S = "图书删除失败!";
			}break;
			case StageController.CARD:{
				result = DaoFactory.getCard().delete(no);
				S = "借书卡删除失败!";
			}break;
			case StageController.ADMIN:{
				if(no == 0)return;
				result = DaoFactory.getAdmin().delete(no);
				S = "账号删除失败!";
			}break;
		}
		if(!result) {
			Dialog.getinformationDialog(S);
		}
		
		StageController.ControllerReflash(type);
		
	}
	
	//增加
	public void add(String s, Object object) {
		switch (s) {
			case StageController.BOOK:{
				ShowGui(object, BookController.BOOKADD);
			}break;
			case StageController.CARD:{
				ShowGui(object, CardController.BOOKADD);
			}break;
			case StageController.ADMIN:{
				ShowGui(object, AdminController.ADMINADD);
			}break;
		}
	}
	
	//修改
	public void update(String s, Object object) {
		switch (s) {
			case StageController.BOOK:{
				ShowGui(object, BookController.BOOKUPDATE);
			}break;
			case StageController.CARD:{
				ShowGui(object, CardController.BOOKUPDATE);
			}break;
			case StageController.ADMIN:{
				Admin admin = (Admin)object;
				if(admin.getNo()==0)return;
				ShowGui(object, AdminController.ADMINUPDATE);
			}break;
		}
	}
	
	
	
	//表格初始化
	public void tableInit(TableView tableview, ObservableList arraylist,String [] elementNames) {
		ObservableList<TableColumn> cols = tableview.getColumns();
		for(int i = 0; i < elementNames.length; i++) {
			cols.get(i).setCellValueFactory(new PropertyValueFactory(elementNames[i]));
		}
		tableview.setItems(arraylist);
	}
	//显示其他窗口
	private void ShowGui(Object object,URL url) {
		Stage stage = new Stage();
		Parent root = null;
		FXMLLoader fxmlLoader = new FXMLLoader();
		try {
			fxmlLoader.setLocation(url);
			root = fxmlLoader.load();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.initOwner(StageController.getStage(StageController.CARD));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			MyController1 myController = fxmlLoader.getController();
			myController.setValue(object);
			stage.showAndWait();
			StageController.ControllerReflash(type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
