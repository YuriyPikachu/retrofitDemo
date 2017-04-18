package com.goach.web.proxy;

import com.goach.web.dao.INewsDao;
import com.goach.web.dbc.SQLDatabaseConnection;
import com.goach.web.impl.NewsDaoImpl;
import com.goach.web.vo.BaseResult;
import com.goach.web.vo.News;

public class NewsDaoProxy implements INewsDao{
	private SQLDatabaseConnection dbc = null;
	private INewsDao dao = null;

	public NewsDaoProxy() throws Exception {
		this.dbc = new SQLDatabaseConnection();
		this.dao = new NewsDaoImpl(this.dbc.conn());
	}
	@Override
	public BaseResult<News> requestNews(String userId)
			throws Exception {
		BaseResult<News> newsBean=null;
		try{
			newsBean=this.dao.requestNews(userId);
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		return newsBean;
	}

}
