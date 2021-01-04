package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest02 {
    public static void main(String[] args) {
        Statement statement = null;
        Connection connection = null;
        try {
            //1.注册驱动
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            //2.获取连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base?serverTimezone=gmt", "root", "333");
            //3.获得数据库操作对象
            statement = connection.createStatement();
            String sql = "update dept set dname='营销部',loc='天津' where deptno=20";
            ////4.执行sql语句
            int count = statement.executeUpdate(sql);
            System.out.println(count == 1 ? "保存成功" : "保存失败");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //6.释放资源
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
