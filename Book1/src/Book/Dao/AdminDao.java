package Book.Dao;

import java.util.List;

import Book.entity.Admin;

public interface AdminDao {
	List<Admin> Search(String sql);
	boolean insert(Admin admin);
	boolean delete(long id);
	boolean update(Admin admin);
	long getCount(String s);
	String Login(Admin admin);
	boolean checkUserName(String UserName);//检查用户名
	boolean getController(String password);//获取账号管理
}
