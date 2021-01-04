package JDBC;/*
 *  @Author life_1
 *  @Date 2019/8/21 11:21

    �˳�������Ŀ��:
        ��һ: ����DBUtil�Ƿ����
        �ڶ�: ģ����ѯ��ôд
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
            //��ȡ����
            connection = DBUtil.getConnection();
            //��ȡԤ��������ݿ��������
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
            //�ͷ���Դ
            DBUtil.close(connection, preparedStatement, resultSet);
        }
    }
}
