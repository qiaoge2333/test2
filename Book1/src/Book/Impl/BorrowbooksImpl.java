package Book.Impl;

import java.sql.Connection;
import java.sql.Types;
import java.util.List;

import org.apache.commons.dbutils.OutParameter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import Book.Dao.BorrowbooksDao;
import Book.entity.Borrowbooks;
import Factory.JDBC;
import q.util.myUtil;

public class BorrowbooksImpl implements BorrowbooksDao {
	private static QueryRunner queryRunner = new QueryRunner();
	@Override
	public List<Borrowbooks> Search(String sql) {
		List<Borrowbooks> bbooks = null;
		Connection conn = null;
		try {
			conn = JDBC.getConnection();
			bbooks = queryRunner.query(conn, sql, new BeanListHandler<Borrowbooks>(Borrowbooks.class));
			JDBC.closeConnection(conn);
		}catch (Exception e) {
			e.printStackTrace();
			JDBC.closeConnection(conn);
			return null;
		}
		return bbooks;
	}


	@Override
	public boolean insert(Borrowbooks b) {
		String sql = "insert into borrowbooks(BookNo,CardNo,Bdate) values(?,?,?) ;";
		Object [] params = {b.getBookNo(),b.getCardNo(),b.getBdate()};
		Connection conn = null;
		int result = 0;
		try {
			conn = JDBC.getConnection();
			result = queryRunner.update(conn, sql, params);
			JDBC.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
			JDBC.closeConnection(conn);
			return false;
		}
		if(result > 0)return true;
		else  return false;
	}

//	@Override
//	public boolean update(Borrowbooks b) {
//		String sql = "update borrowbooks set "
//		return false;
//	}

	@Override
	public boolean delete(long id) {
		String sql = "delete from borrowbooks where id = " + id + " ;";
		Connection conn = null;
		int result = 0;
		try {
			conn = JDBC.getConnection();
			result = queryRunner.update(conn, sql);
			JDBC.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
			JDBC.closeConnection(conn);
			return false;
		}
		if(result > 0) return true;
		else return false;
	}

	@Override
	public long getCount(String s) {
		String sql = "select count(*) from borrowbooks " + s + " ;";
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
	
	public boolean borrowbook(Borrowbooks b) {
		String sql = "{call borrowBook(?,?,?,?)}";
		System.out.println(myUtil.getDate().toString());
		OutParameter<Long> result = new OutParameter<Long>(Types.BIGINT, Long.class);
		Object [] params = {b.getBookNo(),b.getCardNo(),myUtil.getDate(),result};
		Connection conn = null;
		try {
			conn = JDBC.getConnection();
			queryRunner.execute(conn, sql, params);
		}catch (Exception e) {
			return false;
		}
		if (result.getValue() == 1) {
			return true;
		}else {
			return false;
		}
	}


	@Override
	public boolean returnbook(Borrowbooks b) {
		String sql = "{call returnBook(?,?,?,?,?)}";
		OutParameter<Long> result = new OutParameter<Long>(Types.BIGINT, Long.class);
		Object [] params = {b.getId(),b.getBookNo(),b.getCardNo(),myUtil.getDate(),result};
		Connection conn = null;
		try {
			conn = JDBC.getConnection();
			queryRunner.execute(conn, sql, params);
		}catch (Exception e) {
			return false;
		}
		if (result.getValue() == 1) {
			return true;
		}else {
			return false;
		}
	}


	@Override
	public double lostbook(Borrowbooks b) {
		String sql = "{call lostBook(?,?,?,?,?)}";
		OutParameter<Double> money = new OutParameter<Double>(Types.DOUBLE, Double.class);
		OutParameter<Long> result = new OutParameter<Long>(Types.BIGINT, Long.class);
		Object [] params = {b.getId(),b.getBookNo(),b.getCardNo(),result,money};
		Connection conn = null;
		try {
			conn = JDBC.getConnection();
			queryRunner.execute(conn, sql, params );
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
		if(result.getValue() != 1) {
			return -1;
		}else {
			return money.getValue();
		}
	}
	

}
