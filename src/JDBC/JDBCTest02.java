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
            //1.ע������
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            //2.��ȡ����
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base?serverTimezone=gmt", "root", "333");
            //3.������ݿ��������
            statement = connection.createStatement();
            String sql = "update dept set dname='Ӫ����',loc='���' where deptno=20";
            ////4.ִ��sql���
            int count = statement.executeUpdate(sql);
            System.out.println(count == 1 ? "����ɹ�" : "����ʧ��");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //6.�ͷ���Դ
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
