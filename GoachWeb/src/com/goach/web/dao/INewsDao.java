package com.goach.web.dao;

import com.goach.web.vo.BaseResult;
import com.goach.web.vo.News;

public interface INewsDao {
	BaseResult<News> requestNews(String userId) throws Exception;
}
