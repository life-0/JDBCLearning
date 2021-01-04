package JDBC;

import java.sql.*;

//��ѯ�����
public class JDBCTest05 {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            //1.ע������
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.��ȡ����
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base?serverTimezone=gmt", "root", "333");
            //3.��ȡ���ݿ��������
            statement = connection.createStatement();
            //4.ִ��sql���
            String sql = "select empno,ename as name,sal from emp";
            resultSet = statement.executeQuery(sql);  //ר��ִ��DQL���
            //int   executeUpdate(insert/delete/update)
            //ResultSet   executeQuery(select)

            //5.��ѯ�����
            boolean flag = resultSet.next();
//            System.out.println(flag);   //true
            /*if(flag){
                //Ϊ��:���ָ�����������
                //ȡ����
                //getString()�������ص���:��������������ʲô,����String����ʽȡ��
                //���³���� 1,2,3˵���ǵڼ���
                String empno=resultSet.getString(1); //JDBC���е��±��1��ʼ,���Ǵ�0��ʼ
                String ename=resultSet.getString(2);
                String sal=resultSet.getString(3);
                System.out.println(empno+","+ename+","+sal);
            }*/
            while (resultSet.next()) {
                //����׳
                /*String empno=resultSet.getString(1);
                String ename=resultSet.getString(2);
                String sal=resultSet.getString(3);
                System.out.println(empno+","+ename+","+sal);*/

                //����������е��±��ȡ,���е����ֻ�ȡ
               /* String empno=resultSet.getString("empno");
                String ename=resultSet.getString("name");//�ص�ע��:�����Ʋ��Ǳ��е�������,�ǲ�ѯ�������������
                String sal=resultSet.getString("sal");
                System.out.println(empno+","+ename+","+sal);*/

                //���˿�����String����ȡ��֮��,���������ض�������ȡ��
                int empno = resultSet.getInt("empno");
                String ename = resultSet.getString("name");
                Double sal = resultSet.getDouble("sal");
                System.out.println(empno + "," + ename + "," + sal * 10);

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        } finally {
            //6.�ͷ���Դ
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
