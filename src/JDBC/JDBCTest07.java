package JDBC;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/*
    1.解决SQL注入问题?
        只要用户提供的信息不参与SQL语句的编译过程,问题就解决了
        即使用户提供的信息中含有SQL语句的关键字,但没有参与编译,不起作用
        要想用户信息不参与SQL语句的编译,那么必须使用java.sql.PrepareStatement
        PrepareStatement接口继承了java.sql.Statement
        PrepareStatement是属于数据库操作对象
        PrepareStatement原理是:预先对SQL语句的框架进行编译,然后再给SQL语句传"值".
    2.测试结果:
        用户名:
            fdsa
       密码:
            fdsa' or '1'='1
       登录失败
    3.解决SQL注入的关键是什么?
        用户提供的信息即使含有sql语句的关键字,但是这些关键字并没有参与编译.不起作用
    4.对比一下Statement和PreparedStatement?
            Statement存在sql注入问题,PreparedStatement解决了sql注入问题
            Statement是编译一次执行一次,PreparedStatement是编译一次,执行N次,PreparedStatement效率较高一些
            PreparedStatement在编译阶段做类型的安全检查
    5.什么情况下必须使用Statement呢?
            业务方面必须支持sql语句注入的时候
            Statement支持SQL注入,凡事业务方面要求是需要进行sql语句拼接的必须用Statement

*/

public class JDBCTest07 {
    public static void main(String[] args) {
        //初始化一个界面
        Map<String, String> userLoginInfo = initUI();

        //登录,验证用户名和密码
        boolean siginInSuccess = siginIn(userLoginInfo);
        System.out.println(siginInSuccess == true ? "登录成功" : "登录失败");

    }

    /*
     *   用户登录:
     *               userLoginInfo  用户登录信息
     *   @return     false表示失败,true表示成功
     * */
    private static boolean siginIn(Map<String, String> userLoginInfo) {
        //编写JDBC代码
        boolean userSuccess = false;//登录是否成功的标记
        Connection connection = null;
        PreparedStatement preparedStatement = null;//这里使用PreparedStatement(预编译的数据库操作对象)
        ResultSet resultSet = null;

        //单独定义变量,得到Map存储的数值
        String userName = userLoginInfo.get("userName");
        String userPassword = userLoginInfo.get("userPassword");
        try {
            //1.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获取连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base?serverTimezone=gmt", "root", "333");
            //3.获取预编译的数据库操作对象
            //SQL语句的框子.其中一个 ? ,表示一个占位符,一个?将来接收一个"值",注意:占位符不能使用单引号括起来
            String sql = "select * from t_user where loginName=? and loginPassword =?";
            //程序执行到此处,会发送sql语句框子给DBMS然后DBMS进行sql语句的预先编译
            preparedStatement = connection.prepareStatement(sql);
            //给占位符?传值(第一个问号下标是1,第二个问号下标是2,JDBC所有的下标从1开始.)
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userPassword);

            //4.执行sql语句
            //以上正好完成了sql语句的拼接,以下代码的含义是,发送sql语句给DBMS,DBMS进行sql编译
            //正好将用户提供的"非法信息"编译进去,导致了原sql语句的含义被扭曲了

            resultSet = preparedStatement.executeQuery();
            //5.处理结果集
            if (resultSet.next()) {
                userSuccess = true;//表示登录成功
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //6.释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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


        return userSuccess;
    }

    //得到输入值,并且转为Map储存
    private static Map<String, String> initUI() {
        Scanner s = new Scanner(System.in);
        System.out.println("用户名:");
        String userName = s.nextLine();
        System.out.println("密码:");
        String userPassword = s.nextLine();
        Map<String, String> userLoginInfo = new HashMap<>();
        userLoginInfo.put("userName", userName);
        userLoginInfo.put("userPassword", userPassword);
        return userLoginInfo;
    }
}
