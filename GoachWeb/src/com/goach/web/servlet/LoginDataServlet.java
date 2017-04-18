package com.goach.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goach.web.dao.ILoginDao;
import com.goach.web.factory.LoginDaoFactory;
import com.goach.web.vo.BaseResult;
import com.goach.web.vo.RegisterBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LoginDataServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
		System.out.println("doGet");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost");
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("q_version");
		String tel = request.getParameter("device_id");
		System.out.println("email===="+email);
		System.out.println("tel===="+tel);
		ILoginDao loginDao ;
		try {
			loginDao = LoginDaoFactory.getInstance();
			BaseResult<RegisterBean> registerBean = loginDao.requestLogin(userName, password);
			Gson mGson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			out.write(mGson.toJson(registerBean, BaseResult.class));
			out.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		out.close();
		
	}
}
