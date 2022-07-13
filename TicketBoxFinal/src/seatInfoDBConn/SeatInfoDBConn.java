package seatInfoDBConn;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class SeatInfoDBConn {
	
	private Connection con;
	public Connection getConnection() {
		return con;
	}
	
	public SeatInfoDBConn() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ticketbox", "1234");
	}
}
