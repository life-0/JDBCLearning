package JDBC;/*
 *  @Author life_1
 *  @Date 2019/8/20 10:50

    sql脚本:
           drop table if exists t_act;
            create table t_act(
                actno int primary key auto_increment,
                balance double(7,2)
            );
            insert into t_act(balance) values(2000);
            insert into t_act(balance) values(0);
            select * from t_act;
 */
/*      极为重要的三行代码:
            connection.setAutoCommit(false);
            connection.commit();
            connection.rollback;
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCTest11 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获取连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base?serverTimezone=gmt", "root", "333");
            //将自动提交机制修改为手动提交
            connection.setAutoCommit(false);
            //3.获取预编译的数据库操作对象
            String sql = "update t_act set balance=? where actno=?";
            preparedStatement = connection.prepareStatement(sql);

            //给?号传值
            preparedStatement.setDouble(1, 10000);
            preparedStatement.setInt(2, 1);
            int count = preparedStatement.executeUpdate();

          /*  String s=null;
            s.toString();*/

            //再给?号传值
            preparedStatement.setDouble(1, 10000);
            preparedStatement.setInt(2, 2);
            count += preparedStatement.executeUpdate();

            System.out.println(count == 0 ? "修改失败" : "修改成功");

            //程序能够走到这里说明以上程序没有异常,事务结束,手动提交数据
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //回滚事务
            if (connection != null) {
                try {
                    connection.rollback();
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

    }
}
