package Book.Impl;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import Book.Dao.CardDao;
import Book.entity.Card;
import Factory.JDBC;
import q.util.myUtil;


public class CardImpl implements CardDao {
	QueryRunner queryRunner = new QueryRunner();
	@Override
	public List<Card> Search(String sql) {
		List<Card> cards = null;
		Connection conn = null;
		try {
			conn = JDBC.getConnection();
			cards = queryRunner.query(conn, sql, new BeanListHandler<Card>(Card.class));
			JDBC.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
			JDBC.closeConnection(conn);
			return null;
		}
		
		return cards;
	}

	@Override
	public boolean insert(Card card) {
		String sql = "insert into card(CardName,BorrowNumber,Adate,Mail,Phone) values(?,?,?,?,?);";
		Object [] params = {card.getCardName(),card.getBorrowNumber(),myUtil.getDate(),card.getMail(),card.getPhone()};
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
	public boolean update(Card card) {
		String sql = "update card set CardName = ?, Mail = ?, Phone = ? where CardNo = ?;";
		Object [] params = {card.getCardName(),card.getMail(),card.getPhone(),card.getCardNo()};
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
	public boolean delete(long cardNo) {
		String sql = "delete from card where CardNo = " + cardNo + ";";
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

	@Override
	public long getCount(String s) {
		String sql = "select count(*) from card " + s + " ;";
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
