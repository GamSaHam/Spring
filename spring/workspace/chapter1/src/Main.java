import java.sql.SQLException;



public class Main {

	/*String url = "jdbc:mysql://127.0.0.1/testdb?serverTimezone=UTC&user=root&password=mysql";
	Connection conn = null;
	
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	
	
	try {
		conn = DriverManager.getConnection(url);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	*/
		public static void main(String[] args) throws ClassNotFoundException, SQLException {
			
			// ���ø� �޼ҵ� ����
			UserDao dao = new UserDao(new DConnectionMaker());
			
			User user = new User();
			user.setId("kim");
			user.setName("��⼱");
			user.setPassword("married");
			
			dao.add(user);
			
			System.out.println(user.getId() + " ��� ����");
		
			User user2 = dao.get(user.getId());
			System.out.println(user2.getName());
			
			System.out.println(user2.getId() + " ��ȸ ����");
			
			
		}
}