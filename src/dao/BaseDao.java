package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Properties;

public class BaseDao {
	private String driver;
	private String url;
	private String user;
	private String password;
	Connection conn;
	
	public BaseDao(){
		super();
		init();
	}
	
	private void init(){
		String configFile = "MySQLDB.properties";
		Properties params = new Properties();
		InputStream is = BaseDao.class.getClassLoader().getResourceAsStream(configFile);
		try {
			params.load(is);
			driver = params.getProperty("driver");
			url = params.getProperty("url");
			user = params.getProperty("user");
			password = params.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection(){
		if(conn==null){
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	public void closeAll(Connection conn, Statement stmt, ResultSet rs){
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(conn!=null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int update(String sql, Object[] param){
		int num = 0;
		conn = getConnection();
		PreparedStatement pstmt = null;
		try {
			 pstmt = conn.prepareStatement(sql);
			 for (int i = 0; i < param.length; i++) {
				pstmt.setObject(i+1, param[i]);
			 }
			 //System.out.println(pstmt);
			 num = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeAll(conn, pstmt, null);
		}
		return num;
	}
//	public static void main(String[] args) {
//		BaseDao dao = new BaseDao();
//		System.out.println(dao.getConnection());
//	}
}
