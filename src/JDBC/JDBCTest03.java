package JDBC;
/*
    ע����������һ�ַ�ʽ(���ַ�ʽ����)
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest03 {
    public static void main(String[] args) {
        try {
            //1.ע������
            //ע�������ĵ�һ��д��
            // DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            //ע�������ĵڶ���д��
            //Ϊʲô���ַ�ʽ����?��Ϊ������һ���ַ���,�ַ�������д��xxx.properties�ļ���
            //���·�������Ҫ���շ���ֵ,��Ϊ����ֻ������������ض���
            Class.forName("com.mysql.cj.jdbc.Driver()");
            //2.��ȡ����
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base?serverTimezone=gmt", "root", "333");
            System.out.println(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }


    }
}
