package Book.Dao;

import java.util.List;

import Book.entity.Borrowbooks;

public interface BorrowbooksDao {
	List<Borrowbooks> Search(String sql);
	boolean insert(Borrowbooks b);
//	boolean update(Borrowbooks b);
	boolean delete(long id);
	long getCount(String s);
	boolean returnbook(Borrowbooks b);
	boolean borrowbook(Borrowbooks b);
	double lostbook(Borrowbooks b);
}
