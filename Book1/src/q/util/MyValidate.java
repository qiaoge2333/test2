package q.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.WindowEvent;

public class MyValidate {
	
	public static final String SUCCESS = "green";
	public static final String FAIL = "red";
	
	//判断表单是否正确的结果
	private boolean result = true;
	
	//表单是否大于某个数
	public void greaterThan(Node node, int num1, int num2, String tip) {
		if(num1 >= num2) {
			setColor(node, SUCCESS);
		}else {
			addTooltip(node, tip);
			setColor(node, FAIL);
			result = false;
		}
	}
	
	//表单数据长度检查
	public void checkLength(Node node,String str ,int min, int max, String tip) {
		if(str.length()>= min && str.length() <= max) {
			setColor(node, SUCCESS);
		}else {
			addTooltip(node, tip);
			setColor(node, FAIL);
			result = false;
		}
	}
	
	//选项是否被选
	public void isSelect(ChoiceBox cb,String tip) {
		if(!cb.getSelectionModel().isEmpty()) {
			setColor(cb, SUCCESS);
		}else {
			addTooltip(cb, tip);
			setColor(cb, FAIL);
			result = false;
		}
		
	}
	
	//表单是否为空
	public void isEmpty(Node node,String s,String tip) {
		if(s.equals("")||s == null) {
			addTooltip(node, tip);
			setColor(node, FAIL);
			result = false;
		}else {
			setColor(node, SUCCESS);
		}
	}
	
	//正则表达式
	public void Reg(Node node, String text, String Regx, String tip) {
		Pattern pattern = Pattern.compile(Regx);
		Matcher matcher =  pattern.matcher(text);
		if(matcher.find()) {
			setColor(node, SUCCESS);
		}else {
			addTooltip(node, tip);
			setColor(node, FAIL);
			result = false;
		}
	}
	
	
	public void setColor(Node node, String color) {
		node.setStyle("-fx-border-color: "+ color );
	}
	
	//提示
	public void addTooltip(Node node,String text) {
		Tooltip tooltip = new Tooltip(text);
		Tooltip.install(node, tooltip);
		tooltip.setOnHidden(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent arg0) {
				Tooltip tooltip = (Tooltip)arg0.getSource();
				Tooltip.uninstall(node, tooltip);
			}
		});
		
	}
	
	
	//获取结果
	public boolean getResult() {
		return result;
	}
	
	
}
