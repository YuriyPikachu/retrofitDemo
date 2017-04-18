package com.goach.web.dao;

import com.goach.web.vo.BaseResult;
import com.goach.web.vo.RegisterBean;

public interface ILoginDao {
	BaseResult<RegisterBean> requestLogin(String username,String password) throws Exception;
}
