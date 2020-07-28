package com.pss.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DBUtils {
	public static String URL;//数据库连接信息
	public static String USERNAME;//用户名
	public static String PASSWORD;//密码
	public static String DRIVER;//mysql的驱动类
	private static ResourceBundle rb = ResourceBundle.getBundle("com.pss.util.db-config");
	private DBUtils() {}
	//使用静态代码块加载驱动程序
	static {
		URL = rb.getString("jdbc.url");
		USERNAME =  rb.getString("jdbc.username");
		PASSWORD = rb.getString("jdbc.password");
		DRIVER = rb.getString("jdbc.driver");
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	//定义一个获取数据库连接的方法
	public  static Connection getConnection() {
		Connection conn = null;
			try {
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("获取数据库连接失败，请检查URL，USERNAME，PASSWORD和DRIVER是否正确？");
			}
		return conn;
	}
	//关闭数据库的连接
	public static void close(ResultSet rs,Statement stat,Connection conn) {
		try {
			if(null!=rs) {
				rs.close();
			}
			if(null!=stat) {
				stat.close();
			}
			if(null!=conn) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		System.out.println(DBUtils.getConnection());
	}
}
