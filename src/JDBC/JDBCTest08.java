package JDBC;

import java.sql.*;
import java.util.Scanner;

/*����:
        ����ʹ��Statement
*/
public class JDBCTest08 {
    public static void main(String[] args) {
        /*//�û��ڿ���̨����desc���ǽ���,����asc��������
        Scanner s = new Scanner(System.in);
        System.out.println("������desc����asc,desc��ʾ����,asc��ʾ����");
        System.out.print("������:");
        String keyword = s.nextLine();

        //ִ��sql
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //ע������
            Class.forName("com.mysql.cj.jdbc.Driver");
            //��ȡ����
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base?serverTimezone=gmt", "root", "333");
            //��ȡԤ��������ݿ��������
            String  sql="select ename from emp order by ENAME ?";
            ps=connection.prepareStatement(sql);
            ps.setString(1,keyword);
            //ִ��sql
            rs=ps.executeQuery();
            //���������
            while(rs.next()){
                System.out.println(rs.getString("ename"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
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

        }*/
        //�û��ڿ���̨����desc���ǽ���,����asc��������
        Scanner s = new Scanner(System.in);
        System.out.println("������desc����asc,desc��ʾ����,asc��ʾ����");
        System.out.print("������:");
        String keyword = s.nextLine();

        //ִ��sql
        Connection connection = null;
        Statement ps = null;
        ResultSet rs = null;
        try {
            //ע������
            Class.forName("com.mysql.cj.jdbc.Driver");
            //��ȡ����
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base?serverTimezone=gmt", "root", "333");
            //��ȡԤ��������ݿ��������
            ps = connection.createStatement();
            //ִ��sql
            String sql = "select ename from emp order by ENAME " + keyword;
            rs = ps.executeQuery(sql);
            //���������
            while (rs.next()) {
                System.out.println(rs.getString("ename"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
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
