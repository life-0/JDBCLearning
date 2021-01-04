package JDBC;

import JDBC.DButil.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 *  @Author life_1
 *  @Date 2019/8/21 12:06
 * 演示行级锁:
 * 这个程序开启一个事务,这个事务专门进行查询,并且使用行级锁/悲观锁,锁住相关的记录
 *
 */public class JDBCTest13 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            //开启事务
            connection.setAutoCommit(false);
            String sql = "select ename ,job,sal from emp where job=? for update ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "MANAGER");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("ename") + "," + resultSet.getString("job")
                        + "," + resultSet.getString("sal"));
            }

            //提交事务(事务结束)
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    //事务回滚
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }
    }
}
