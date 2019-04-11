package GUI.Controller;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Tooltip;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;

public class MyController2 {
	
	//0:浮点数   1:整数
	
	//表单输入框格式化
	static StringConverter<Double> doubleSc = new StringConverter<Double>() {
		
		@Override
		public String toString(Double num) {
			if(num==null)return "";
			return num.toString();
		}
		
		@Override
		public Double fromString(String str) {
			if(str==null)return (double) 0;
			return Double.valueOf(str);
		}
	};
	
	static StringConverter<Long> intSc = new StringConverter<Long>() {

		@Override
		public Long fromString(String str) {
			if(str == null) return (long) 0;
			return Long.parseLong(str);
		}

		@Override
		public String toString(Long num) {
			if( num == null) return "";
			return num.toString();
		}
	};
	
	
	//0:浮点数   1:整数
	public void addFormatter(TextField text,int select){
		if(select == 1) {
			text.setTextFormatter(new TextFormatter<Long>(intSc));
		}else {
			text.setTextFormatter(new TextFormatter<Double>(doubleSc));
		}
	}


}
