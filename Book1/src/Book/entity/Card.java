package Book.entity;

import java.util.Date;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Card implements myEntity{
	private final SimpleLongProperty CardNo = new SimpleLongProperty();//借书卡编号
	private final SimpleStringProperty CardName = new SimpleStringProperty();//借书卡用户名
	private final SimpleIntegerProperty BorrowNumber = new SimpleIntegerProperty();//借书卡的借书数量
	private final SimpleObjectProperty<Date> Adate = new SimpleObjectProperty<Date>();//借书卡的创建时间
	private final SimpleStringProperty Phone = new SimpleStringProperty();//手机号
	private final SimpleStringProperty Mail = new SimpleStringProperty();//邮箱号
	
	//各元素的名称
	public static final String [] elementNames = {"CardNo","CardName","BorrowNumber",
			"Adate","Mail","Phone"};
	
	
	public long getCardNo() {
		return CardNo.get();
	}
	
	public void setCardNo(long CardNo) {
		this.CardNo.set(CardNo);
	}
	
	public SimpleLongProperty CardNoProperty() {
		return this.CardNo;
	}
	
	


	
	public String getCardName() {
		return CardName.get();
	}
	
	public void setCardName(String CardName) {
		this.CardName.set(CardName);
	}
	
	public SimpleStringProperty CardNameProperty() {
		return this.CardName;
	}
	
	
	
	
	public Date getAdate() {
		return Adate.get();
	}
	
	public void setAdate(Date Adate) {
		this.Adate.set(Adate);
	}
	
	public SimpleObjectProperty<Date> AdateProperty() {
		return this.Adate;
	}
	
	
	
	
	
	public int getBorrowNumber() {
		return BorrowNumber.get();
	}
	
	public void setBorrowNumber(int BorrowNumber) {
		this.BorrowNumber.set(BorrowNumber);
	}
	
	public SimpleIntegerProperty BorrowNumberProperty() {
		return this.BorrowNumber;
	}
	
	
	

	public String getPhone() {
		return Phone.get();
	}
	
	public void setPhone(String Phone) {
		this.Phone.set(Phone);
	}
	
	public SimpleStringProperty PhoneProperty() {
		return this.Phone;
	}
	
	
	
	

	public String getMail() {
		return Mail.get();
	}
	
	public void setMail(String Mail) {
		this.Mail.set(Mail);
	}
	
	public SimpleStringProperty MailProperty() {
		return this.Mail;
	}

	@Override
	public long getValue() {
		return CardNo.get();
	}
	
}
