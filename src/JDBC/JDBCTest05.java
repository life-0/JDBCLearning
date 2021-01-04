package JDBC;

import java.sql.*;

//查询结果集
public class JDBCTest05 {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获取连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base?serverTimezone=gmt", "root", "333");
            //3.获取数据库操作对象
            statement = connection.createStatement();
            //4.执行sql语句
            String sql = "select empno,ename as name,sal from emp";
            resultSet = statement.executeQuery(sql);  //专门执行DQL语句
            //int   executeUpdate(insert/delete/update)
            //ResultSet   executeQuery(select)

            //5.查询结果集
            boolean flag = resultSet.next();
//            System.out.println(flag);   //true
            /*if(flag){
                //为真:光标指向的行有数据
                //取数据
                //getString()方法的特点是:不管数据类型是什么,都以String的形式取出
                //以下程序的 1,2,3说的是第几列
                String empno=resultSet.getString(1); //JDBC所有的下标从1开始,不是从0开始
                String ename=resultSet.getString(2);
                String sal=resultSet.getString(3);
                System.out.println(empno+","+ename+","+sal);
            }*/
            while (resultSet.next()) {
                //不健壮
                /*String empno=resultSet.getString(1);
                String ename=resultSet.getString(2);
                String sal=resultSet.getString(3);
                System.out.println(empno+","+ename+","+sal);*/

                //这个不是以列的下标获取,以列的名字获取
               /* String empno=resultSet.getString("empno");
                String ename=resultSet.getString("name");//重点注意:列名称不是表中的列名称,是查询结果集的列名称
                String sal=resultSet.getString("sal");
                System.out.println(empno+","+ename+","+sal);*/

                //除了可以以String类型取出之外,还可以以特定的类型取出
                int empno = resultSet.getInt("empno");
                String ename = resultSet.getString("name");
                Double sal = resultSet.getDouble("sal");
                System.out.println(empno + "," + ename + "," + sal * 10);

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        } finally {
            //6.释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
