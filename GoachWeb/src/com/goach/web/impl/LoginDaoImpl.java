package com.goach.web.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.goach.web.dao.ILoginDao;
import com.goach.web.vo.BaseResult;
import com.goach.web.vo.RegisterBean;

public class LoginDaoImpl implements ILoginDao{
	private Connection conn;
	private Statement statement;
	public LoginDaoImpl(Connection conn){
		this.conn = conn ;
	}
	@Override
	public BaseResult<RegisterBean> requestLogin(String username, String password) throws Exception{
		BaseResult<RegisterBean> baseBean = new BaseResult<RegisterBean>();
		RegisterBean registerBean = new RegisterBean();
		statement = conn.createStatement();
		String sql = "select * from user where username = '"+username+"' and password = '"+password+"'";
		ResultSet resultSet = statement.executeQuery(sql);
		registerBean.setErrorCode(2);
		System.out.println("gfgfgf"+username);
		System.out.println("gfgfgf"+password);

		while(resultSet.next()){
			
			registerBean.setErrorCode(1);
			registerBean.setUserId(resultSet.getInt(1));
			registerBean.setUserName(resultSet.getString(2));
		}
		baseBean.setResultCode(200);
		baseBean.setResponseTime(new Date(System.currentTimeMillis()));
		baseBean.setData(registerBean);
		return baseBean;
	}

}
