package Book.Dao;

import java.util.List;

import Book.entity.Card;

public interface CardDao {
	List<Card> Search(String sql);
	boolean insert(Card card);
	boolean update(Card card);
	boolean delete(long cardNo);
	long getCount(String s);
}
