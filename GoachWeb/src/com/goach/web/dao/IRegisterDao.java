package com.goach.web.dao;

import com.goach.web.vo.BaseResult;
import com.goach.web.vo.RegisterBean;

public interface IRegisterDao{
	public BaseResult<RegisterBean> requestRegister(String userName,String password) throws Exception;
}
