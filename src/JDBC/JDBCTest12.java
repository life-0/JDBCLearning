package JDBC;/*
 *  @Author life_1
 *  @Date 2019/8/21 11:21

    此程序两个目的:
        第一: 测试DBUtil是否好用
        第二: 模糊查询怎么写
 */

import JDBC.DButil.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTest12 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //获取链接
            connection = DBUtil.getConnection();
            //获取预编译的数据库操作对象
            String sql = "select ename from emp where ename like ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "_a%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("ename"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            DBUtil.close(connection, preparedStatement, resultSet);
        }
    }
}
