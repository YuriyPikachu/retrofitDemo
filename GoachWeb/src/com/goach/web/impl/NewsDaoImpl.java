package com.goach.web.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.goach.web.dao.ILoginDao;
import com.goach.web.dao.INewsDao;
import com.goach.web.dao.IRegisterDao;
import com.goach.web.dbc.SQLDatabaseConnection;
import com.goach.web.vo.BaseResult;
import com.goach.web.vo.NewItem;
import com.goach.web.vo.News;
import com.goach.web.vo.RegisterBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class NewsDaoImpl implements INewsDao{
	private Connection conn;
	private Statement statement;
	public NewsDaoImpl(Connection conn){
		this.conn = conn ;
	}
	@Override
	public BaseResult<News> requestNews(String userId) throws Exception{
		BaseResult<News> baseBean = new BaseResult<News>();
		News newsBean = new News();
		
		List<NewItem> mDataList = new ArrayList<NewItem>();
		statement = conn.createStatement();
		String sql = "select * from news where userId = '"+userId+"'";
		ResultSet resultSet = statement.executeQuery(sql);
		System.out.println("gfgfgf"+userId);
		while(resultSet.next()){
			NewItem newItem = new NewItem();
			newItem.setId(resultSet.getInt(1));
			newItem.setTitle(resultSet.getString(2));
			newItem.setContent(resultSet.getString(3));
			mDataList.add(newItem);
		}
		newsBean.setNewsItem(mDataList);
		baseBean.setResultCode(200);
		baseBean.setResponseTime(new Date(System.currentTimeMillis()));
		baseBean.setData(newsBean);
		Gson mGson = new GsonBuilder()
        .setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String str = mGson.toJson(baseBean, BaseResult.class);
		System.out.println(str);
		return baseBean;
	}
	public static void main(String[] args) throws Exception {
		SQLDatabaseConnection sqlDatabaseConn = new SQLDatabaseConnection();
		INewsDao newsDao = new NewsDaoImpl(sqlDatabaseConn.conn());
		newsDao.requestNews("1000000");
		sqlDatabaseConn.close();
		
	}

}
