package com.goach.web.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;

import com.goach.web.dao.IRegisterDao;
import com.goach.web.dbc.SQLDatabaseConnection;
import com.goach.web.vo.BaseResult;
import com.goach.web.vo.RegisterBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RegisterDaoImpl implements IRegisterDao {
	private Connection conn;
	private Statement statement;
	public RegisterDaoImpl(Connection conn){
		this.conn = conn ;
	}
	@Override
	public BaseResult<RegisterBean> requestRegister(String userName, String password) throws Exception{
		BaseResult<RegisterBean> baseResult = new BaseResult<RegisterBean>();
		RegisterBean registerBean = new RegisterBean();
		String sql = "select count(*) from user";
		statement = conn.createStatement();
		ResultSet resultSetCount = statement.executeQuery(sql);
		int count = 0;
		if(resultSetCount.next()){
			count= resultSetCount.getInt(1)+1000000;
		}
		sql = "insert into user(id,username,password) values('"+count+"','"+userName+"','"+password+"')";
		int result = statement.executeUpdate(sql);
		if(result != -1){
			sql = "select * from user where username = '"+userName+"' and password = '"+password+"'";
			ResultSet resultSet = statement.executeQuery(sql);
			registerBean.setErrorCode(2);
			while(resultSet.next()){
				registerBean.setErrorCode(1);
				baseResult.setResultCode(200);
				registerBean.setUserId(resultSet.getInt(1));
				registerBean.setUserName(resultSet.getString(2));
			}
			baseResult.setResponseTime(new Date(System.currentTimeMillis()));
			baseResult.setData(registerBean);
		}
		return baseResult;
	}
	public static void main(String[] args) throws Exception {
		SQLDatabaseConnection sqlDatabaseConn = new SQLDatabaseConnection();
		IRegisterDao registerDao = new RegisterDaoImpl(sqlDatabaseConn.conn());
		registerDao.requestRegister("Admin2", "123456");
		sqlDatabaseConn.close();
		
	}
}
