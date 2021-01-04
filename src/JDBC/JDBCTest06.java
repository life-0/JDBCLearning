package JDBC;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*实现功能:
        1.需求:模拟用户登录功能的实现
        2.业务描述:
            程序运行的时候,提供了一个输入的入口,可以让用户输入用户名和密码
            用户输入用户名和密码之后,提交信息,java程序收集到用户信息
            java程序连接数据库验证用户名和密码是否合法
            合法: 显示登录成功
            不合法:  显示登录失败
        3.数据库的准备:
             在实际开发中,表的设计使用专业的建模工具,在此程序中,使用了PowerDesigner
        4.当前代码存在的问题
            用户名:
                fdsa
            密码:
                fdsa' or '1'='1
            登录成功
            这种现象被称为SQL注入(安全隐患).(黑客经常使用)
         5.导致SQL注入的根本原因是什么?
                用户输入的信息中含有sql语句的关键字,并且这些关键字参与sql语句的编译过程
                导致sql语句的原意被扭曲,进而达到sql注入

 */
public class JDBCTest06 {
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
        Statement statement = null;
        ResultSet resultSet = null;

        //单独定义变量,得到Map存储的数值
        String userName = userLoginInfo.get("userName");
        String userPassword = userLoginInfo.get("userPassword");
        try {
            //1.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获取连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base?serverTimezone=gmt", "root", "333");
            //3.获取数据库操作对象
            statement = connection.createStatement();
            //4.执行sql语句
            //这是一种方式:
            /*String sql="select * from t_user where userName='"+userLoginInfo.get("userName")+"' and userPassword" +
                   "='"+userLoginInfo.get("userPassword")+"'";*/
            //另一种
            String sql = "select * from t_user where loginName='" + userName + "' and loginPassword ='" + userPassword + "'";
            //以上正好完成了sql语句的拼接,以下代码的含义是,发送sql语句给DBMS,DBMS进行sql编译
            //正好将用户提供的"非法信息"编译进去,导致了原sql语句的含义被扭曲了

            resultSet = statement.executeQuery(sql);
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
            if (statement != null) {
                try {
                    statement.close();
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
