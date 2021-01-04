package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
    JDBC事务机制:
        1.JDBC中的事务是自动提交的,什么是自动提交
            只要执行任意一条DML语句,则自动提交一次
*/
public class JDBCTesr10 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获取连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base?serverTimezone=gmt", "root", "333");
            //3.获取预编译的数据库操作对象
            String sql = "update dept set dname= ? where deptno= ?";
            preparedStatement = connection.prepareStatement(sql);

            //第一次给占位符传值
            preparedStatement.setString(1, "x部门");
            preparedStatement.setInt(2, 10);
            int count = preparedStatement.executeUpdate();    //执行第一条update语句
            System.out.println(count);

            //重新给占位符传值
            preparedStatement.setString(1, "y部门");
            preparedStatement.setInt(2, 20);
            count = preparedStatement.executeUpdate();        //执行第二条update语句
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
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
}
