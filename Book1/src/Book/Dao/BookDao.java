package Book.Dao;

import java.util.List;

import Book.entity.Book;

public interface BookDao {
	List<Book> Search(String sql);//搜索
	boolean insert(Book b);
	boolean update(Book b);
	boolean delete(long BookNo);
	long getCount(String s);//获得页数
}
