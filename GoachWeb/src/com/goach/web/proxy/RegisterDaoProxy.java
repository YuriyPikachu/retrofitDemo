package com.goach.web.proxy;

import com.goach.web.dao.IRegisterDao;
import com.goach.web.dbc.SQLDatabaseConnection;
import com.goach.web.impl.RegisterDaoImpl;
import com.goach.web.vo.BaseResult;
import com.goach.web.vo.RegisterBean;

public class RegisterDaoProxy implements IRegisterDao{
	private SQLDatabaseConnection dbc = null;
	private IRegisterDao dao = null;

	public RegisterDaoProxy() throws Exception {
		this.dbc = new SQLDatabaseConnection();
		this.dao = new RegisterDaoImpl(this.dbc.conn());
	}
	@Override
	public BaseResult<RegisterBean> requestRegister(String userName, String password)
			throws Exception {
		BaseResult<RegisterBean> regiserBean=null;
		try{
			regiserBean=this.dao.requestRegister(userName,password);
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		return regiserBean;
	}

}
