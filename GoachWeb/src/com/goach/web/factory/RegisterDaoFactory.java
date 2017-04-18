package com.goach.web.factory;

import com.goach.web.dao.IRegisterDao;
import com.goach.web.proxy.RegisterDaoProxy;

public class RegisterDaoFactory {
	public static IRegisterDao getInstance() throws Exception{
		return new RegisterDaoProxy();
	}
}
