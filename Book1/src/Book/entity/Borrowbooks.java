package Book.entity;

import java.util.Date;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Borrowbooks implements myEntity{
	private final SimpleLongProperty Id = new SimpleLongProperty();//历史id
	private final SimpleLongProperty BookNo = new SimpleLongProperty();//书的编号
	private final SimpleLongProperty CardNo = new SimpleLongProperty();//借书卡编号
	private final SimpleObjectProperty<Date> Bdate = new SimpleObjectProperty<Date>();//租借日期
	private final SimpleObjectProperty<Date> Rdate = new SimpleObjectProperty<Date>();//归还日期
	private final SimpleBooleanProperty Cd = new SimpleBooleanProperty();//是否归还
	//各元素的名称
	public static final String [] elementNames = {"Id","BookNo","CardNo",
			"Bdate","Rdate","Cd"};
	
	
	public long getId() {
		return Id.get();
	}
	
	public void setId(long Id) {
		this.Id.set(Id);
	}
	
	public SimpleLongProperty IdProperty() {
		return this.Id;
	}
	
	
	
	
	public long getBookNo() {
		return BookNo.get();
	}
	
	public void setBookNo(long BookNo) {
		this.BookNo.set(BookNo);
	}
	
	public SimpleLongProperty BookNoProperty() {
		return this.BookNo;
	}
	
	
	
	
	public long getCardNo() {
		return CardNo.get();
	}
	
	public void setCardNo(long CardNo) {
		this.CardNo.set(CardNo);
	}
	
	public SimpleLongProperty CardNoProperty() {
		return this.CardNo;
	}
	
	
	
	
	public Date getBdate() {
		return Bdate.get();
	}
	
	public void setBdate(Date Bdate) {
		this.Bdate.set(Bdate);
	}
	
	public SimpleObjectProperty<Date> BdateProperty() {
		return this.Bdate;
	}
	
	
	
	
	
	public Date getRdate() {
		return Rdate.get();
	}
	
	public void setRdate(Date Rdate) {
		this.Rdate.set(Rdate);
	}
	
	public SimpleObjectProperty<Date> RdateProperty() {
		return this.Rdate;
	}
	
	public boolean getCd() {
		return Cd.get();
	}
	
	public void setCd(boolean Cd) {
		this.Cd.set(Cd);
	}
	
	public SimpleBooleanProperty CdProperty() {
		return this.Cd;
	}

	@Override
	public long getValue() {
		return Id.get();
	}
	
}
