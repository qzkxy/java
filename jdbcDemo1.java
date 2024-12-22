package cn.itcast.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class jdbcDemo1 {
    public static void main(String[] args) throws Exception{

        //1.导入驱动jar包
        //2.注册驱动
        //自动注册可以省略 Class.forName("com.mysql.jdbc.Driver");
        //3.获取数据库连接对象
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
        //4.定义sql语句
        String sql = "update student set gender = '男' where id = 1;";
        //5.获取执行sql语句的对象
        Statement stmt = conn.createStatement();
        //6.执行sql语句，接受返回结果
        int count = stmt.executeUpdate(sql);
        //7.处理结果
        System.out.println(count);
        //8.释放资源
        stmt.close();
        conn.close();
    }
}
