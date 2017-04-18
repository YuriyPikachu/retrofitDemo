package com.goach.web.proxy;

import com.goach.web.dao.ILoginDao;
import com.goach.web.dao.IRegisterDao;
import com.goach.web.dbc.SQLDatabaseConnection;
import com.goach.web.impl.LoginDaoImpl;
import com.goach.web.impl.RegisterDaoImpl;
import com.goach.web.vo.BaseResult;
import com.goach.web.vo.RegisterBean;

public class LoginDaoProxy implements ILoginDao{
	private SQLDatabaseConnection dbc = null;
	private ILoginDao dao = null;

	public LoginDaoProxy() throws Exception {
		this.dbc = new SQLDatabaseConnection();
		this.dao = new LoginDaoImpl(this.dbc.conn());
	}
	@Override
	public BaseResult<RegisterBean> requestLogin(String userName, String password)
			throws Exception {
		BaseResult<RegisterBean> regiserBean=null;
		try{
			regiserBean=this.dao.requestLogin(userName,password);
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		return regiserBean;
	}

}
