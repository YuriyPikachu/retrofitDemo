package com.goach.web.constant;

public interface Constant {
	String databaseUser = "root";
	String password = "123456";
	// MySQL��JDBC URL��д��ʽ��jdbc:mysql://�������ƣ����Ӷ˿�/���ݿ������?����=ֵ
    // ������������Ҫָ��useUnicode��characterEncoding
    // ִ�����ݿ����֮ǰҪ�����ݿ����ϵͳ�ϴ���һ�����ݿ⣬�����Լ�����
    // �������֮ǰ��Ҫ�ȴ���javademo���ݿ�
	String url = "jdbc:mysql://localhost:3306/goach?"
			+ "user="+databaseUser+"&password="+password+"&useUnicode=true&characterEncoding=UTF-8&useSSL=false";
}
