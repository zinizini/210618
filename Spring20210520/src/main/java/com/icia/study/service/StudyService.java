package com.icia.study.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.icia.study.dao.StudyDAO;

@Service
public class StudyService {
	
	@Autowired
	private StudyDAO sdao;
	
	public void insertDB(String param1) {
		sdao.insertDB(param1);
	}
	
//	private ModelAndView mav;
//
//	public ModelAndView selectDB() {
//		mav = new ModelAndView();
//		// DB에서 조회한 내용을 ArrayList에 담음
//		// 우변 : db에서 조회한 결과를 List에 담아옴
//		// 좌변 : 담아온 내용을 dbList 변수에 저장
//		List<String> dbList = sdao.selectDB();
//		// dbList에 담긴 값을 가지고 select.jsp로 이동해야함.
//		// 어떤 데이터를 어떤 페이지로 가지고 가기 위해 mav 객체 이용
//		
//		// 데이터를 담는 내용
//		mav.addObject("dbList", dbList);
//		// 목적지를 지정하는 내용(jsp 파일 이름)
//		mav.setViewName("select");
//		
//		return mav;
//		
//	}

		

}
