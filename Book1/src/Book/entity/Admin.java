package Book.entity;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class Admin implements myEntity{
	private final SimpleLongProperty No = new SimpleLongProperty();//编号
	private final SimpleStringProperty UserName = new SimpleStringProperty();//用户名
	private final SimpleStringProperty Password = new SimpleStringProperty();//密码
	//各元素的名称
	public static final String [] elementNames = {"No","UserName","Password"};
	
	public Admin() {
		
	}
	
	public Admin(long userNo,String userName,String password) {
		No.set(userNo);
		UserName.set(userName);
		Password.set(password);
	}
	
	
	public long getNo() {
		return No.get();
	}
	
	public void setNo(long No) {
		this.No.set(No);
	}
	
	public SimpleLongProperty NoProperty() {
		return this.No;
	}
	
	
	
	public String getUserName() {
		return UserName.get();
	}
	
	public void setUserName(String UserName) {
		this.UserName.set(UserName);
	}
	
	public SimpleStringProperty UserNameProperty() {
		return this.UserName;
	}
	
	
	
	public String getPassword() {
		return Password.get();
	}
	
	public void setPassword(String Password) {
		this.Password.set(Password);
	}
	
	public SimpleStringProperty PasswordProperty() {
		return this.Password;
	}

	@Override
	public long getValue() {
		return No.get();
	}
}
