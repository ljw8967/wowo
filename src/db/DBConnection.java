package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static void initConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			System.out.println("Driver Loading Success");
			
		} catch (ClassNotFoundException e) {
			System.out.println("Driver가 없습니다");
		}
	}
	
	public static Connection getConnection() {
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.219:1521:xe", "scott", "tiger");
		
			System.out.println("Oracle Connection Success");
			
		} catch (SQLException e) {
			System.out.println("DB를 연결하지 못했습니다");
		}
		
		return conn;		
	}
}
