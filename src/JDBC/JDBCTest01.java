package JDBC;

import java.sql.*;

public class JDBCTest01 {

    public static void main(String[] args) {
        Statement stmt = null;
        Connection conn = null;
        try {
            //1.注册驱动
            Driver driver = new com.mysql.cj.jdbc.Driver();  //多态,父类型引用指向子类型对象
            //Driver  driver = new oracle.jdbc.driver.OracleDriver();   oracle的驱动
            DriverManager.registerDriver(driver);
            //2.获取连接
            /*
             *   url:统一资源定位符(网络某个资源的绝对路径)
             *   https://www.baidu.com/就是url
             *   url包括哪几部分?
             *       协议
             *       IP
             *       PORT
             *       资源名
             *
             *    http://182.61.200.7:80/index.html
             *       http:// 通信协议
             *       182.61.200.7     服务器IP地址
             *       80      服务器上软件的端口
             *       index.html  是服务器上某个资源名
             *
             *   jdbc:mysql://localhost:3306/base?serverTimezone=gmt
             *       jdbc:mysql://      协议
             *       localhost       本机的IP地址
             *       3306        mysql数据库端口号
             *       base        具体的数据库实例名
             *
             *    什么是通信协议,有什么用?
             *       通信协议是通信之前就提前定好的数据传送格式
             *       数据包具体怎么传数据,格式提前定好的
             *
             *   oracle的url:
             *       jdbc:oracle:thin:@localhost:1521:orcl
             */
            String url = "jdbc:mysql://localhost:3306/base?serverTimezone=gmt";
            String user = "root";
            String password = "333";
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("数据库连接对象=" + conn);
            //com.mysql.cj.jdbc.ConnectionImpl@fb434

            //3.获取数据库操作对象(Statement专门执行sql语句的)
            stmt = conn.createStatement();

            //4.执行sql
            String sql = "insert into dept(deptno,dname,loc) values(50,'人事部','南宁')";
            int count = stmt.executeUpdate(sql);    //执行DML语句
            //  专门执行DML语句的(insert delete update)
            //  返回值是"影响数据库中的记录条数"
            System.out.println(count == 1 ? "保存成功" : "保存失败");

            //5.处理查询结果

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //6.释放资源
            //为了保证资源一定释放,在finally语句块中关闭资源
            //并且要遵循从小到大依次关闭
            //分别对其try...catch
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
