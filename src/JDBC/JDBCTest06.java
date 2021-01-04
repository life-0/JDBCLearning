package JDBC;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*ʵ�ֹ���:
        1.����:ģ���û���¼���ܵ�ʵ��
        2.ҵ������:
            �������е�ʱ��,�ṩ��һ����������,�������û������û���������
            �û������û���������֮��,�ύ��Ϣ,java�����ռ����û���Ϣ
            java�����������ݿ���֤�û����������Ƿ�Ϸ�
            �Ϸ�: ��ʾ��¼�ɹ�
            ���Ϸ�:  ��ʾ��¼ʧ��
        3.���ݿ��׼��:
             ��ʵ�ʿ�����,������ʹ��רҵ�Ľ�ģ����,�ڴ˳�����,ʹ����PowerDesigner
        4.��ǰ������ڵ�����
            �û���:
                fdsa
            ����:
                fdsa' or '1'='1
            ��¼�ɹ�
            �������󱻳�ΪSQLע��(��ȫ����).(�ڿ;���ʹ��)
         5.����SQLע��ĸ���ԭ����ʲô?
                �û��������Ϣ�к���sql���Ĺؼ���,������Щ�ؼ��ֲ���sql���ı������
                ����sql����ԭ�ⱻŤ��,�����ﵽsqlע��

 */
public class JDBCTest06 {
    public static void main(String[] args) {
        //��ʼ��һ������
        Map<String, String> userLoginInfo = initUI();

        //��¼,��֤�û���������
        boolean siginInSuccess = siginIn(userLoginInfo);
        System.out.println(siginInSuccess == true ? "��¼�ɹ�" : "��¼ʧ��");

    }

    /*
     *   �û���¼:
     *               userLoginInfo  �û���¼��Ϣ
     *   @return     false��ʾʧ��,true��ʾ�ɹ�
     * */
    private static boolean siginIn(Map<String, String> userLoginInfo) {
        //��дJDBC����
        boolean userSuccess = false;//��¼�Ƿ�ɹ��ı��
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        //�����������,�õ�Map�洢����ֵ
        String userName = userLoginInfo.get("userName");
        String userPassword = userLoginInfo.get("userPassword");
        try {
            //1.ע������
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.��ȡ����
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base?serverTimezone=gmt", "root", "333");
            //3.��ȡ���ݿ��������
            statement = connection.createStatement();
            //4.ִ��sql���
            //����һ�ַ�ʽ:
            /*String sql="select * from t_user where userName='"+userLoginInfo.get("userName")+"' and userPassword" +
                   "='"+userLoginInfo.get("userPassword")+"'";*/
            //��һ��
            String sql = "select * from t_user where loginName='" + userName + "' and loginPassword ='" + userPassword + "'";
            //�������������sql����ƴ��,���´���ĺ�����,����sql����DBMS,DBMS����sql����
            //���ý��û��ṩ��"�Ƿ���Ϣ"�����ȥ,������ԭsql���ĺ��屻Ť����

            resultSet = statement.executeQuery(sql);
            //5.��������
            if (resultSet.next()) {
                userSuccess = true;//��ʾ��¼�ɹ�
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //6.�ͷ���Դ
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

    //�õ�����ֵ,����תΪMap����
    private static Map<String, String> initUI() {
        Scanner s = new Scanner(System.in);
        System.out.println("�û���:");
        String userName = s.nextLine();
        System.out.println("����:");
        String userPassword = s.nextLine();
        Map<String, String> userLoginInfo = new HashMap<>();
        userLoginInfo.put("userName", userName);
        userLoginInfo.put("userPassword", userPassword);
        return userLoginInfo;
    }
}
