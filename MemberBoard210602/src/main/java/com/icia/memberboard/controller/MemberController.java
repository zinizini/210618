package com.icia.memberboard.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icia.memberboard.dto.MemberDTO;
import com.icia.memberboard.service.MemberService;


@Controller
public class MemberController {
	@Autowired
	private MemberService ms;
	
	private ModelAndView mav;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value="/joinpage")
	public String joinPage() {
		return "memberjoin";
	}
	
	@RequestMapping(value="/memberjoin")
	public ModelAndView memberJoin(@ModelAttribute MemberDTO member) throws IllegalStateException, IOException {
		mav = ms.memberJoin(member);
		return mav;
	}
	@RequestMapping(value="idcheck")
	public @ResponseBody String idCheck(@RequestParam("mid") String mid) {
		System.out.println("idcheck 메소드 호출됨");
		System.out.println("입력 id값"+mid);
		String result = ms.idCheck(mid);
		return result;
	}
	
	@RequestMapping(value="/loginpage")
	public String loginPage() {
		return "memberlogin";
	}
	
	@RequestMapping(value="/login")
	public ModelAndView memberLogin(@ModelAttribute MemberDTO member) {
		System.out.println("login 메소드"+member.toString());
		mav = ms.memberLogin(member);
		return mav;
	}
	
	@RequestMapping(value="/memberlist")
	public ModelAndView memberList() {
		mav = ms.memberList();
		return mav;
	}
	
	
	@RequestMapping(value="/memberview")
	public ModelAndView memberView(@RequestParam("mid") String mid){
		mav = ms.memberView(mid);
		return mav;
	}
	
	@RequestMapping(value = "/memberviewajax")
	public @ResponseBody MemberDTO memberViewAjax(@RequestParam("mid") String mid) {
		System.out.println("memberViewAjax 메소드 호출됨");
		System.out.println("전달 id값 " + mid);
		MemberDTO member = ms.memberViewAjax(mid);
		System.out.println(member);
		return member;
	}
	
	@RequestMapping(value="memberupdate")
	public ModelAndView update() {
		mav = ms.update();
		return mav;
	}
	
	@RequestMapping(value="/updateprocess")
	public ModelAndView updateProcess(@ModelAttribute MemberDTO member) {
		mav = ms.updateProcess(member);
		return mav;
	}

	@RequestMapping(value="logout")
	public String logout() {
		session.invalidate();
		return "home";
	}
	
	@RequestMapping(value="/memberdelete")
	public ModelAndView memberDelete(@RequestParam("mid") String mid) {
		mav = ms.memberDelete(mid);
		return mav;
	}
	

}



