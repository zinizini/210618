package com.icia.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.example.dao.TestDAO;

@Service
public class TestService {
	
	// TestService 클래스는 com.icia.example.service 패지키에 소속되어 있고,
	// TestDAO 클래스는 com.icia.example.dao 패키지에 소속되어있음.
	// 두 클래스는 서로 다른 패키지에 소속되어 있기 때문에 import를 해줘야 사용이 가능함.
	@Autowired
	private TestDAO tdao;
	
	public void method1() {
		System.out.println("method1 호출됨");
	}
	
	public void method2(String data1) {
		System.out.println(data1);
		// helllo.jsp -> HomeController로 부터 받은 data1d을 TestDAO 클래스의 daoMethod1()으로 전달
		tdao.daoMethod1(data1);
		
	}
	
	
}
