package com.icia.memberboard.service;


import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icia.memberboard.dao.MemberDAO;
import com.icia.memberboard.dto.MemberDTO;

@Service
public class MemberService {
	@Autowired
	private MemberDAO mdao; 
	
	private ModelAndView mav;
	
	@Autowired
	private HttpSession session;

	public ModelAndView memberJoin(MemberDTO member) throws IllegalStateException, IOException {
		mav = new ModelAndView();
		MultipartFile mfile = member.getMfile();
		String mfilename = mfile.getOriginalFilename();
		mfilename = System.currentTimeMillis() + "-" + mfilename;
		System.out.println("memberjoin 메소드 " + mfilename);
		member.setMfilename(mfilename);
		int insertResult = 0;
		insertResult = mdao.memberJoin(member);
		if(insertResult > 0) { 
			mav.setViewName("memberlogin");
		} else { 
			mav.setViewName("memberjoin");
		}
		return mav;
	}

	public String idCheck(String mid) {
		String checkResult = mdao.idCheck(mid);
		String result = "";
		if(checkResult == null) {
			result = "ok";
		} else {
			result = "no";
		}
		return result;
	}

	public ModelAndView memberLogin(MemberDTO member) {
		mav = new ModelAndView();

		String loginId = mdao.memberLogin(member);

		if (loginId != null) {
			session.setAttribute("loginMember", loginId);
			mav.setViewName("boardlist");
		} else {
			mav.setViewName("memberlogin");
		}
		return mav;
	}

	public ModelAndView memberList() {
		mav = new ModelAndView();
		List<MemberDTO> memberList = mdao.memberList();
		mav.addObject("memberList", memberList);
		mav.setViewName("memberlist");
		return mav;
	}

	public ModelAndView memberView(String mid) {
		mav = new ModelAndView();
		MemberDTO member = mdao.memberView(mid);
		mav.addObject("result", member);
		mav.setViewName("memberview");
		return mav;
	}

	public MemberDTO memberViewAjax(String mid) {
		MemberDTO member = mdao.memberView(mid);
		return member;
	}


	public ModelAndView update() {
		mav = new ModelAndView();
		String loginId = (String) session.getAttribute("loginMember");
		MemberDTO memberUpdate = mdao.update(loginId);
		
		mav.addObject("member1", memberUpdate);
		mav.setViewName("memberupdate");
		return mav;
	}

	public ModelAndView updateProcess(MemberDTO member) {
		mav = new ModelAndView();
		int updateResult = mdao.updateProcess(member);
		if(updateResult > 0) {
			mav.setViewName("membermain");
		} else {
			mav.setViewName("updatefail");
		}
		return mav;
	}

	public ModelAndView memberDelete(String mid) {
		mav = new ModelAndView();
		mdao.memberDelete(mid);
		mav.setViewName("redirect:/memberlist");
		return mav;
	}



	
}



