package JDBC.DButil;
/*
 *  @Author life_1
 *  @Date 2019/8/21 11:24
 * JDBC工具类,简化JDBC编程
 */

import java.sql.*;

public class DBUtil {
    /*  工具类中的方法都是私有的
     *   因为工具类当中的方法都是静态的,不需要new对象,直接采用类名调用
     */

    private DBUtil() {
    }

    //静态代码块在类加载时执行,并且只执行一次
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /*
     * 获取数据库连接对象
     * @return   连接对象
     * */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/base?serverTimezone=gmt", "root", "333");

    }

    /*
     * @connection   连接对象
     * @statement    数据库操作对象
     * @resultSet    结果集
     * */
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
