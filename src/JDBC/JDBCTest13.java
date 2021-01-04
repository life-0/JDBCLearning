package JDBC;

import JDBC.DButil.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 *  @Author life_1
 *  @Date 2019/8/21 12:06
 * ��ʾ�м���:
 * ���������һ������,�������ר�Ž��в�ѯ,����ʹ���м���/������,��ס��صļ�¼
 *
 */public class JDBCTest13 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            //��������
            connection.setAutoCommit(false);
            String sql = "select ename ,job,sal from emp where job=? for update ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "MANAGER");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("ename") + "," + resultSet.getString("job")
                        + "," + resultSet.getString("sal"));
            }

            //�ύ����(�������)
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    //����ع�
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
