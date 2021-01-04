package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
    JDBC�������:
        1.JDBC�е��������Զ��ύ��,ʲô���Զ��ύ
            ֻҪִ������һ��DML���,���Զ��ύһ��
*/
public class JDBCTesr10 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //1.ע������
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.��ȡ����
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base?serverTimezone=gmt", "root", "333");
            //3.��ȡԤ��������ݿ��������
            String sql = "update dept set dname= ? where deptno= ?";
            preparedStatement = connection.prepareStatement(sql);

            //��һ�θ�ռλ����ֵ
            preparedStatement.setString(1, "x����");
            preparedStatement.setInt(2, 10);
            int count = preparedStatement.executeUpdate();    //ִ�е�һ��update���
            System.out.println(count);

            //���¸�ռλ����ֵ
            preparedStatement.setString(1, "y����");
            preparedStatement.setInt(2, 20);
            count = preparedStatement.executeUpdate();        //ִ�еڶ���update���
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
