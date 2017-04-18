package com.goach.web.factory;

import com.goach.web.dao.ILoginDao;
import com.goach.web.dao.IRegisterDao;
import com.goach.web.proxy.LoginDaoProxy;
import com.goach.web.proxy.RegisterDaoProxy;

public class LoginDaoFactory {
	public static ILoginDao getInstance() throws Exception{
		return new LoginDaoProxy();
	}
}
