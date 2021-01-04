package JDBC.DButil;
/*
 *  @Author life_1
 *  @Date 2019/8/21 11:24
 * JDBC������,��JDBC���
 */

import java.sql.*;

public class DBUtil {
    /*  �������еķ�������˽�е�
     *   ��Ϊ�����൱�еķ������Ǿ�̬��,����Ҫnew����,ֱ�Ӳ�����������
     */

    private DBUtil() {
    }

    //��̬������������ʱִ��,����ִֻ��һ��
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /*
     * ��ȡ���ݿ����Ӷ���
     * @return   ���Ӷ���
     * */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/base?serverTimezone=gmt", "root", "333");

    }

    /*
     * @connection   ���Ӷ���
     * @statement    ���ݿ��������
     * @resultSet    �����
     * */
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
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


}
