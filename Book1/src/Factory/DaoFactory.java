package Factory;
import Book.Dao.*;
import Book.Impl.*;
public class DaoFactory {
	public static BookDao getBook() {
		return new BookImpl();
	}
	
	public static CardDao getCard() {
		return new CardImpl();
	}
	
	public static AdminDao getAdmin() {
		return new AdminImpl();
	}
	
	public static BorrowbooksDao getBorrowbooks() {
		return new BorrowbooksImpl();
	}
}
