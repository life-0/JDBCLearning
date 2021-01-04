package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

//?????????????????????????????????
/*
 *   ?????????§Ó???????????????????§Õ????java??????
 */
public class JDBCTest04 {
    public static void main(String[] args) {
        //???????????????????????
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");
        Connection connection = null;
        Statement statement = null;
        try {
            //1.???????
            Class.forName(driver);
            //2.???????
            connection = DriverManager.getConnection(url, user, password);
            //3.???????????????
            statement = connection.createStatement();
            String sql = "update dept set dname='?????02',loc='???02' where deptno=20";
            ////4.???sql???
            int count = statement.executeUpdate(sql);
            System.out.println(count == 1 ? "?????" : "??????");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //6.??????
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }


}
