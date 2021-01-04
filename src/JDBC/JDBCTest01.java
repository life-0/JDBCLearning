package JDBC;

import java.sql.*;

public class JDBCTest01 {

    public static void main(String[] args) {
        Statement stmt = null;
        Connection conn = null;
        try {
            //1.ע������
            Driver driver = new com.mysql.cj.jdbc.Driver();  //��̬,����������ָ�������Ͷ���
            //Driver  driver = new oracle.jdbc.driver.OracleDriver();   oracle������
            DriverManager.registerDriver(driver);
            //2.��ȡ����
            /*
             *   url:ͳһ��Դ��λ��(����ĳ����Դ�ľ���·��)
             *   https://www.baidu.com/����url
             *   url�����ļ�����?
             *       Э��
             *       IP
             *       PORT
             *       ��Դ��
             *
             *    http://182.61.200.7:80/index.html
             *       http:// ͨ��Э��
             *       182.61.200.7     ������IP��ַ
             *       80      ������������Ķ˿�
             *       index.html  �Ƿ�������ĳ����Դ��
             *
             *   jdbc:mysql://localhost:3306/base?serverTimezone=gmt
             *       jdbc:mysql://      Э��
             *       localhost       ������IP��ַ
             *       3306        mysql���ݿ�˿ں�
             *       base        ��������ݿ�ʵ����
             *
             *    ʲô��ͨ��Э��,��ʲô��?
             *       ͨ��Э����ͨ��֮ǰ����ǰ���õ����ݴ��͸�ʽ
             *       ���ݰ�������ô������,��ʽ��ǰ���õ�
             *
             *   oracle��url:
             *       jdbc:oracle:thin:@localhost:1521:orcl
             */
            String url = "jdbc:mysql://localhost:3306/base?serverTimezone=gmt";
            String user = "root";
            String password = "333";
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("���ݿ����Ӷ���=" + conn);
            //com.mysql.cj.jdbc.ConnectionImpl@fb434

            //3.��ȡ���ݿ��������(Statementר��ִ��sql����)
            stmt = conn.createStatement();

            //4.ִ��sql
            String sql = "insert into dept(deptno,dname,loc) values(50,'���²�','����')";
            int count = stmt.executeUpdate(sql);    //ִ��DML���
            //  ר��ִ��DML����(insert delete update)
            //  ����ֵ��"Ӱ�����ݿ��еļ�¼����"
            System.out.println(count == 1 ? "����ɹ�" : "����ʧ��");

            //5.�����ѯ���

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //6.�ͷ���Դ
            //Ϊ�˱�֤��Դһ���ͷ�,��finally�����йر���Դ
            //����Ҫ��ѭ��С�������ιر�
            //�ֱ����try...catch
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        }

    }
}
