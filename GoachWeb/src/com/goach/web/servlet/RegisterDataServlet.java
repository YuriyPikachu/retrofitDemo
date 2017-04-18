package com.goach.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goach.web.dao.IRegisterDao;
import com.goach.web.factory.RegisterDaoFactory;
import com.goach.web.vo.BaseResult;
import com.goach.web.vo.RegisterBean;
import com.google.gson.Gson;

public class RegisterDataServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		IRegisterDao registerDao ;
		try {
			registerDao = RegisterDaoFactory.getInstance();
			BaseResult<RegisterBean> registerBean = registerDao.requestRegister(userName, password);
			out.write(new Gson().toJson(registerBean, BaseResult.class));
			out.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		out.close();
	}
}
