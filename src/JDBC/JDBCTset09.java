package JDBC;

import java.sql.*;

/*
    PreparedStatement完成insert   delete  update
*/
public class JDBCTset09 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获取连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base?serverTimezone=gmt", "root", "333");
            //3.获取预编译的数据库操作对象
          /*  String  sql="insert into dept(deptno,dname,loc) values(?,?,?)";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,70);
            preparedStatement.setString(2,"研发部");
            preparedStatement.setString(3,"广西");*/

           /*String   sql="update dept set loc = ?,DNAME= ? where DEPTNO = ?";
           preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,"广西");
            preparedStatement.setString(2,"纪检部");
            preparedStatement.setInt(3,60);*/
            String sql = "delete from dept where deptno=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 60);
            //4.执行sql语句
            int count = preparedStatement.executeUpdate();
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
