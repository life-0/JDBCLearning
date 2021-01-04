package JDBC;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/*
    1.���SQLע������?
        ֻҪ�û��ṩ����Ϣ������SQL���ı������,����ͽ����
        ��ʹ�û��ṩ����Ϣ�к���SQL���Ĺؼ���,��û�в������,��������
        Ҫ���û���Ϣ������SQL���ı���,��ô����ʹ��java.sql.PrepareStatement
        PrepareStatement�ӿڼ̳���java.sql.Statement
        PrepareStatement���������ݿ��������
        PrepareStatementԭ����:Ԥ�ȶ�SQL���Ŀ�ܽ��б���,Ȼ���ٸ�SQL��䴫"ֵ".
    2.���Խ��:
        �û���:
            fdsa
       ����:
            fdsa' or '1'='1
       ��¼ʧ��
    3.���SQLע��Ĺؼ���ʲô?
        �û��ṩ����Ϣ��ʹ����sql���Ĺؼ���,������Щ�ؼ��ֲ�û�в������.��������
    4.�Ա�һ��Statement��PreparedStatement?
            Statement����sqlע������,PreparedStatement�����sqlע������
            Statement�Ǳ���һ��ִ��һ��,PreparedStatement�Ǳ���һ��,ִ��N��,PreparedStatementЧ�ʽϸ�һЩ
            PreparedStatement�ڱ���׶������͵İ�ȫ���
    5.ʲô����±���ʹ��Statement��?
            ҵ�������֧��sql���ע���ʱ��
            Statement֧��SQLע��,����ҵ����Ҫ������Ҫ����sql���ƴ�ӵı�����Statement

*/

public class JDBCTest07 {
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
        PreparedStatement preparedStatement = null;//����ʹ��PreparedStatement(Ԥ��������ݿ��������)
        ResultSet resultSet = null;

        //�����������,�õ�Map�洢����ֵ
        String userName = userLoginInfo.get("userName");
        String userPassword = userLoginInfo.get("userPassword");
        try {
            //1.ע������
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.��ȡ����
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base?serverTimezone=gmt", "root", "333");
            //3.��ȡԤ��������ݿ��������
            //SQL���Ŀ���.����һ�� ? ,��ʾһ��ռλ��,һ��?��������һ��"ֵ",ע��:ռλ������ʹ�õ�����������
            String sql = "select * from t_user where loginName=? and loginPassword =?";
            //����ִ�е��˴�,�ᷢ��sql�����Ӹ�DBMSȻ��DBMS����sql����Ԥ�ȱ���
            preparedStatement = connection.prepareStatement(sql);
            //��ռλ��?��ֵ(��һ���ʺ��±���1,�ڶ����ʺ��±���2,JDBC���е��±��1��ʼ.)
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userPassword);

            //4.ִ��sql���
            //�������������sql����ƴ��,���´���ĺ�����,����sql����DBMS,DBMS����sql����
            //���ý��û��ṩ��"�Ƿ���Ϣ"�����ȥ,������ԭsql���ĺ��屻Ť����

            resultSet = preparedStatement.executeQuery();
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
