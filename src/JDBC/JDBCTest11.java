package JDBC;/*
 *  @Author life_1
 *  @Date 2019/8/20 10:50

    sql�ű�:
           drop table if exists t_act;
            create table t_act(
                actno int primary key auto_increment,
                balance double(7,2)
            );
            insert into t_act(balance) values(2000);
            insert into t_act(balance) values(0);
            select * from t_act;
 */
/*      ��Ϊ��Ҫ�����д���:
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
            //1.ע������
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.��ȡ����
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base?serverTimezone=gmt", "root", "333");
            //���Զ��ύ�����޸�Ϊ�ֶ��ύ
            connection.setAutoCommit(false);
            //3.��ȡԤ��������ݿ��������
            String sql = "update t_act set balance=? where actno=?";
            preparedStatement = connection.prepareStatement(sql);

            //��?�Ŵ�ֵ
            preparedStatement.setDouble(1, 10000);
            preparedStatement.setInt(2, 1);
            int count = preparedStatement.executeUpdate();

          /*  String s=null;
            s.toString();*/

            //�ٸ�?�Ŵ�ֵ
            preparedStatement.setDouble(1, 10000);
            preparedStatement.setInt(2, 2);
            count += preparedStatement.executeUpdate();

            System.out.println(count == 0 ? "�޸�ʧ��" : "�޸ĳɹ�");

            //�����ܹ��ߵ�����˵�����ϳ���û���쳣,�������,�ֶ��ύ����
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //�ع�����
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
