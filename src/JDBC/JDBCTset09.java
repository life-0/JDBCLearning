package JDBC;

import java.sql.*;

/*
    PreparedStatement���insert   delete  update
*/
public class JDBCTset09 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //1.ע������
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.��ȡ����
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base?serverTimezone=gmt", "root", "333");
            //3.��ȡԤ��������ݿ��������
          /*  String  sql="insert into dept(deptno,dname,loc) values(?,?,?)";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,70);
            preparedStatement.setString(2,"�з���");
            preparedStatement.setString(3,"����");*/

           /*String   sql="update dept set loc = ?,DNAME= ? where DEPTNO = ?";
           preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,"����");
            preparedStatement.setString(2,"�ͼ첿");
            preparedStatement.setInt(3,60);*/
            String sql = "delete from dept where deptno=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 60);
            //4.ִ��sql���
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
