import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DConnectionMaker implements ConnectionMaker {

	public Connection makeNewConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/testdb?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&user=root&password=mysql");
		
		return connection;
	}

	
	
}
