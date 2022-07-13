package payDBConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PayDBConn {

	private Connection con;

	public PayDBConn() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");	
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ticketbox", "1234");
	}

	public Connection getConnection() {
		return con;
	}
}
