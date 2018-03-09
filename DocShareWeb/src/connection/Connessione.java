package connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;

public class Connessione {
	

public static Connection getConnection() throws ClassNotFoundException, SQLException {
	
	String url = "jdbc:mysql://localhost:3306/docshare";
	String driverPath = "com.mysql.jdbc.Driver";
	String username = "root";
    String password = "";
	Connection conn = null;
	try {
		Class driver_class = Class.forName(driverPath);
        Driver driver = (Driver) driver_class.newInstance();
        DriverManager.registerDriver(driver);
		conn = (Connection) DriverManager.getConnection(url,username,password);
		System.out.println("<--- "+conn.toString()+" --->");
	} catch (Exception e) {
		e.printStackTrace();
	}
	return conn;
}
}