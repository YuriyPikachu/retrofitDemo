package com.goach.web.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.goach.web.constant.Constant;

public class SQLDatabaseConnection {
	Connection conn = null ;
	public Connection conn() throws Exception{
			try{
				Class.forName("com.mysql.jdbc.Driver");
				 System.out.println("成功加载MySQL驱动程序");
				 conn = DriverManager.getConnection(Constant.url);
			}catch (SQLException ex) {
	            System.out.println("MySQL操作错误");
	            ex.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			return conn;
			
	}
	public void close() throws Exception {
		if (this.conn != null) {
			try {
				this.conn.close();
			} catch (Exception e) {
				throw e;
			}
		}
	}
	 public static void main(String[] args) throws Exception {
		 SQLDatabaseConnection sqlDatabaseConn = new SQLDatabaseConnection();
		String sql ;
		 Statement stmt = sqlDatabaseConn.conn().createStatement();
		   sql = "create table student(NO char(20),name varchar(20),primary key(NO))";
		   int result = stmt.executeUpdate(sql);
		   if (result != -1) {
             System.out.println("创建数据表成功");
             sql = "insert into student(NO,name) values('2012001','陶伟基')";
             result = stmt.executeUpdate(sql);
             sql = "insert into student(NO,name) values('2012002','周小俊')";
             result = stmt.executeUpdate(sql);
             sql = "select * from student";
             ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
             System.out.println("学号\t姓名");
             while (rs.next()) {
                 System.out.println(rs.getString(1) + "\t" + rs.getString(2));
                 // 入如果返回的是int类型可以用getInt()
             }
         }
	 }
 }
