package com.goach.web.factory;


import com.goach.web.dao.INewsDao;
import com.goach.web.proxy.NewsDaoProxy;

public class NewsDaoFactory {
	public static INewsDao getInstance() throws Exception{
		return new NewsDaoProxy();
	}
}
