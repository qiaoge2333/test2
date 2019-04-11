package Book.Impl;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import Book.Dao.AdminDao;
import Book.entity.Admin;
import Factory.JDBC;

public class AdminImpl implements AdminDao {
	private static QueryRunner queryRunner = new QueryRunner();
	@Override
	public List<Admin> Search(String sql) {
		List<Admin> admins = null;
		Connection conn = null;
		try {
			conn = JDBC.getConnection();
			admins = queryRunner.query(conn, sql, new BeanListHandler<Admin>(Admin.class));
			JDBC.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
			JDBC.closeConnection(conn);
			return null;
		}
		return admins;
	}

	@Override
	public boolean insert(Admin admin) {
		String sql = "insert into admin(UserName,Password) values(?,?)";
		Object [] params = {admin.getUserName(),admin.getPassword()};
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
	public boolean delete(long id) {
		String sql = "delete from admin where No = " + id + ";";
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
	public boolean update(Admin admin) {
		String sql = "update admin set UserName = ?, Password = ? where No = ?;";
		Object [] params = {admin.getUserName(),admin.getPassword(),admin.getNo()};
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
	public long getCount(String s) {
		String sql = "select count(*) from admin " + s + " ;";
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

	@Override
	public String Login(Admin admin) {
		String sql = "select * from admin where UserName = ? and No != 0;";
		Object [] params = {admin.getUserName()};
		Connection conn = null;
		Admin a = null;
		try {
			conn = JDBC.getConnection();
			a = queryRunner.query(conn, sql, new BeanHandler<Admin>(Admin.class), params);
			if(a == null) {
				return "用户名不存在";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		if(!a.getPassword().equals(admin.getPassword())) {
			
			return "密码错误";
		}
		
		return "登录成功";
	}

	@Override
	public boolean checkUserName(String UserName) {
		String sql = "select * from admin where UserName = '" + UserName + "';";
		Connection conn = null;
		Admin admin = null;
		try {
			conn = JDBC.getConnection();
			admin = queryRunner.query(conn, sql, new BeanHandler<Admin>(Admin.class));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if(admin != null) {
			return false;
		}
		
		return true;
	}

	@Override
	public boolean getController(String password) {
		String sql = "select * from admin where No = 0 and Password = " + password + ";";
		Connection conn = null;
		Admin admin = null;
		try {
			conn =JDBC.getConnection();
			admin = queryRunner.query(conn, sql, new BeanHandler<Admin>(Admin.class));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if(admin == null)
			return false;
		else
			return true;
	}
	
	

}
