package JDBC;

import java.sql.*;
import java.util.Scanner;

/*例子:
        必须使用Statement
*/
public class JDBCTest08 {
    public static void main(String[] args) {
        /*//用户在控制台输入desc就是降序,输入asc就是升序
        Scanner s = new Scanner(System.in);
        System.out.println("请输入desc或者asc,desc表示降序,asc表示升序");
        System.out.print("请输入:");
        String keyword = s.nextLine();

        //执行sql
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取链接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base?serverTimezone=gmt", "root", "333");
            //获取预编译的数据库操作对象
            String  sql="select ename from emp order by ENAME ?";
            ps=connection.prepareStatement(sql);
            ps.setString(1,keyword);
            //执行sql
            rs=ps.executeQuery();
            //遍历结果集
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
        //用户在控制台输入desc就是降序,输入asc就是升序
        Scanner s = new Scanner(System.in);
        System.out.println("请输入desc或者asc,desc表示降序,asc表示升序");
        System.out.print("请输入:");
        String keyword = s.nextLine();

        //执行sql
        Connection connection = null;
        Statement ps = null;
        ResultSet rs = null;
        try {
            //注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取链接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base?serverTimezone=gmt", "root", "333");
            //获取预编译的数据库操作对象
            ps = connection.createStatement();
            //执行sql
            String sql = "select ename from emp order by ENAME " + keyword;
            rs = ps.executeQuery(sql);
            //遍历结果集
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
