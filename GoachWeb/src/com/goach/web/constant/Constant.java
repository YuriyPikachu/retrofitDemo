package com.goach.web.constant;

public interface Constant {
	String databaseUser = "root";
	String password = "123456";
	// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
    // 避免中文乱码要指定useUnicode和characterEncoding
    // 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
    // 下面语句之前就要先创建javademo数据库
	String url = "jdbc:mysql://localhost:3306/goach?"
			+ "user="+databaseUser+"&password="+password+"&useUnicode=true&characterEncoding=UTF-8&useSSL=false";
}
