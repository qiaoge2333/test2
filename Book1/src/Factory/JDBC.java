package Factory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * 使用配置文件来配置JDBC连接数据库 该类用来管理数据库的连接
 */
public class JDBC {
    // 连接数据库的路径
    public static String url;
    // 连接数据库的用户名
    public static String user;
    // 连接数据库的密码
    public static String pwd;

    public static String driver;

    // 静态块
    static {
        try {
            // 读取配置文件
            Properties prop = new Properties();
            /*
             * 这种写法是将来更加推荐的相对路径 写法。
             */
            InputStream is = JDBC.class.getClassLoader().getResourceAsStream("Factory/db.properties");

            prop.load(is);
            is.close();
            // 获取驱动
            driver = prop.getProperty("jdbc.driver");
            // 获取地址
            url = prop.getProperty("jdbc.url");
            // 获取用户名
            user = prop.getProperty("jdbc.user");
            // 获取密码
            pwd = prop.getProperty("jdbc.password");

            // 注册驱动
            Class.forName(driver);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
        try {
            Connection conn = DriverManager.getConnection(url, user, pwd);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            // 通知调用者，创建连接出错
            throw e;
        }
    }

    /**
     * 关闭给定的连接
     */
    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void test() {
    	try {
			getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


}