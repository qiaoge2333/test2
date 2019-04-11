package Book.entity;

import java.util.Date;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book implements myEntity{
	private final SimpleLongProperty BookNo = new SimpleLongProperty();//书的编号
	private final SimpleStringProperty BookName = new SimpleStringProperty();//书的名称
	private final SimpleStringProperty BookSoft = new SimpleStringProperty();//书的分类
	private final SimpleStringProperty Press = new SimpleStringProperty();//书的发行商
	private final SimpleObjectProperty<Date> Adate = new SimpleObjectProperty<Date>();//入库日期
	private final SimpleObjectProperty<Date> Pdate = new SimpleObjectProperty<Date>();//发行日期
	private final SimpleIntegerProperty MaxNumber = new SimpleIntegerProperty();//图书库存
	private final SimpleIntegerProperty Number = new SimpleIntegerProperty();//图书现存量
	private final SimpleStringProperty Author = new SimpleStringProperty();//作者
	private final SimpleDoubleProperty Price = new SimpleDoubleProperty();//价格
	//各元素的名称
	public static final String [] elementNames = {"BookNo","BookName","BookSoft",
			"Price","Author","Press","Pdate","Adate","MaxNumber","Number"};
	
	public long getBookNo() {
		return BookNo.get();
	}
	
	public void setBookNo(long BookNo) {
		this.BookNo.set(BookNo);
	}
	
	public SimpleLongProperty BookNoProperty() {
		return this.BookNo;
	}
	
	
	
	public String getBookName() {
		return BookName.get();
	}
	
	public void setBookName(String BookName) {
		this.BookName.set(BookName);
	}
	
	public SimpleStringProperty BookNameProperty() {
		return this.BookName;
	}
	


	
	public String getAuthor() {
		return Author.get();
	}
	
	public void setAuthor(String Author) {
		this.Author.set(Author);
	}
	
	public SimpleStringProperty AuthorProperty() {
		return this.Author;
	}
	
	
	
	
	public String getBookSoft() {
		return BookSoft.get();
	}
	
	public void setBookSoft(String BookSoft) {
		this.BookSoft.set(BookSoft);
	}
	
	public SimpleStringProperty BookSoftProperty() {
		return this.BookSoft;
	}
	
	
	
	
	public String getPress() {
		return Press.get();
	}
	
	public void setPress(String Press) {
		this.Press.set(Press);
	}
	
	public SimpleStringProperty PressProperty() {
		return this.Press;
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
	
	
	
	
	public Date getPdate() {
		return Pdate.get();
	}
	
	public void setPdate(Date Pdate) {
		this.Pdate.set(Pdate);
	}
	
	public SimpleObjectProperty<Date> PdateProperty() {
		return this.Pdate;
	}
	
	
	
	public int getMaxNumber() {
		return MaxNumber.get();
	}
	
	public void setMaxNumber(int MaxNumber) {
		this.MaxNumber.set(MaxNumber);
	}
	
	public SimpleIntegerProperty MaxNumberProperty() {
		return this.MaxNumber;
	}
	
	
	
	public int getNumber() {
		return Number.get();
	}
	
	public void setNumber(int Number) {
		this.Number.set(Number);
	}
	
	public SimpleIntegerProperty NumberProperty() {
		return this.Number;
	}
	
	
	public double getPrice() {
		return Price.get();
	}
	
	public void setPrice(double Price) {
		this.Price.set(Price);
	}
	
	public SimpleDoubleProperty PriceProperty() {
		return this.Price;
	}

	@Override
	public long getValue() {
		return BookNo.get();
	}
}
