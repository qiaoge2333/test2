package GUI.Controller;


import java.util.HashMap;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class StageController {
	//存储Stage对象的map集合
	private static HashMap<String, Stage> stages = new HashMap<String, Stage>();
	//存储Stage的Controller对象
	private static HashMap<String, Controller1> controllers = new HashMap<String, Controller1>();
	//窗口坐标
	private static double x;
	private static double y;
	
	//Stage名称
	public static final String MAIN = "Main";
	public static final String BOOK = "BookGui";
	public static final String CARD = "CardGui";
	public static final String BORROW = "BorrowbookGui";
	public static final String ADMIN = "AdminGui";
	
	
	//设置窗口的坐标
	private static void setPostion(Stage stage) {
		x = stage.getX();
		y = stage.getY();
	}
	
	
	//往map中添加Stage
	public static void addStage(String key,Stage stage) {
		stages.put(key, stage);
	}
	//往map中添加controller
	public static void addController(String key, Controller1 value) {
		controllers.put(key, value);
	}
	
	
	//从map中获取Stage
	public static Stage getStage(String key) {
		return stages.get(key);
	}
	
	
	//加载窗口并且加入map中
	public static void LoadStage(String name,String resource) {
		try {
			Stage stage = new Stage();
			Parent root;
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(StageController.class.getResource(resource));
			root = fxmlLoader.load();
			stage.initStyle(StageStyle.DECORATED);
			stage.setResizable(false);
			stage.setScene(new Scene(root));
			stage.setX(x);
			stage.setY(y);
			addController(name, fxmlLoader.getController());
			addStage(name, stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	//窗口隐藏
	public static void StageHide(String name) {
		Stage stage = getStage(name);
		setPostion(stage);
		stage.hide();
	}
	
	//窗口显示
	public static boolean StageShow(String name,double X, double Y) {
		try {
			Stage stage = getStage(name);
			stage.setX(X);
			stage.setY(Y);
			stage.show();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("不存在该界面或名字错误");
			return false;
		}
		return true;
	}
	
	//Stage切换
	public static void StageToggle(String oldStageName, String newStageName) {
		Stage newStage = stages.get(newStageName);
		Stage oldStage = stages.get(oldStageName);
		
		//记录oldStage的位置
		setPostion(oldStage);
		oldStage.hide();
		
		//显示新的Stage
		newStage.setX(x);
		newStage.setY(y);
		newStage.show();
	}
	
	//Controller表单刷新
	public static void ControllerReflash(String name) {
		Controller1 c = controllers.get(name);
		System.out.println("stageController刷新");
		c.reflash();
	}
	
		

}
