package JDBC;
/*
 *  @Author life_1
 *  @Date 2019/8/21 12:03
 * 这个程序负责修改被锁定的记录
 */

import JDBC.DButil.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTest14 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(false);
            String sql = "update  emp set sal=sal*10 where job=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "MANAGER");
            int count = preparedStatement.executeUpdate();
            System.out.println(count);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
