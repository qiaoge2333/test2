package Book.Impl;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;


import Book.Dao.BookDao;
import Book.entity.Book;
import Factory.JDBC;

public class BookImpl implements BookDao {
	private static QueryRunner queryRunner = new QueryRunner();
	@Override
	public List<Book> Search(String sql) {
		List<Book> books = null;
		Connection conn = null;
		try {
			conn = JDBC.getConnection();
			books = queryRunner.query(conn,sql, new BeanListHandler<Book>(Book.class));
			JDBC.closeConnection(conn);
		}catch (Exception e) {
			e.printStackTrace();
			JDBC.closeConnection(conn);
			return null;
		}
		return books;
	}


	@Override
	public boolean insert(Book b) {
		String sql = "insert into book(BookName,BookSoft,Price,Author,Press,Pdate,Adate,MaxNumber,Number) values(?,?,?,?,?,?,?,?,?);";
		Object [] params = {b.getBookName(),b.getBookSoft(),b.getPrice(),b.getAuthor(),b.getPress(),b.getPdate(),new Date(),b.getMaxNumber(),b.getMaxNumber()};
		int result = 0;
		Connection conn = null;
		try {
			conn = JDBC.getConnection();
			result = queryRunner.update(conn, sql, params);
			JDBC.closeConnection(conn);
		}catch (Exception e) {
			e.printStackTrace();
			JDBC.closeConnection(conn);
			return false;
		}
		if(result > 0)return true;
		else return false;
	}

	@Override
	public boolean update(Book b) {
		String sql = "update book set BookName = ?,BookSoft = ?,Price = ?, Author = ?, Press = ?,Pdate = ?, MaxNumber = ?, Number = ? where BookNo = ?;";
		Object [] params = {b.getBookName(),b.getBookSoft(),b.getPrice(),b.getAuthor(),b.getPress(),b.getPdate(),b.getMaxNumber(),b.getNumber(),b.getBookNo()};
		int result = 0;
		Connection conn = null;
		try {
			conn = JDBC.getConnection();
			result = queryRunner.update(conn,sql,params);
			JDBC.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
			JDBC.closeConnection(conn);
			return false;
		}
		if(result > 0)return true;
		else return false;
		
	}

	@Override
	public boolean delete(long BookNo) {
		String sql = "delete from book where BookNo = " + BookNo + ";";
		int result = 0;
		Connection conn = null;
		try {
			conn = JDBC.getConnection();
			result = queryRunner.update(conn,sql);
			JDBC.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
			JDBC.closeConnection(conn);
			return false;
		}
		if(result > 0)return true;
		else return false;
	}

	public void test() {
		System.out.println(getCount(""));
	}

	@Override
	public long getCount(String s) {
		String sql = "select count(*) from book " + s + " ;";
		long result = 0;
		Connection conn = null;
		try {
			conn = JDBC.getConnection();
			Object[] r = queryRunner.query(conn,sql,new ArrayHandler());
			result = (long)r[0];
			JDBC.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
			JDBC.closeConnection(conn);
			return -1;
		}
		return result;
	}

}
