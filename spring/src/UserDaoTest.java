import java.sql.SQLException;



public class UserDaoTest {

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

        // 템플릿 메소드 패턴
        UserDao dao = new DaoFactory().userDao();

        User user = new User();
        user.setId("kim");
        user.setName("백기선");
        user.setPassword("married");

        dao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());

        System.out.println(user2.getId() + " 조회 성공");


    }
}
