import java.sql.Connection;
import java.sql.SQLException;





public interface ConnectionMaker {
	
	/*Class.forName("com.mysql.cj.jdbc.Driver");
	Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/testdb?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&user=root&password=mysql");
	
	return connection;*/
	public Connection makeNewConnection() throws ClassNotFoundException, SQLException;
	
	
	
	
	
}




