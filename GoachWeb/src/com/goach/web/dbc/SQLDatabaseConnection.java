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
				 System.out.println("�ɹ�����MySQL��������");
				 conn = DriverManager.getConnection(Constant.url);
			}catch (SQLException ex) {
	            System.out.println("MySQL��������");
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
             System.out.println("�������ݱ�ɹ�");
             sql = "insert into student(NO,name) values('2012001','��ΰ��')";
             result = stmt.executeUpdate(sql);
             sql = "insert into student(NO,name) values('2012002','��С��')";
             result = stmt.executeUpdate(sql);
             sql = "select * from student";
             ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
             System.out.println("ѧ��\t����");
             while (rs.next()) {
                 System.out.println(rs.getString(1) + "\t" + rs.getString(2));
                 // ��������ص���int���Ϳ�����getInt()
             }
         }
	 }
 }
