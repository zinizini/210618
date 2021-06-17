package com.icia.memberboard.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icia.memberboard.dto.BoardDTO;
import com.icia.memberboard.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService bs;
	
	private ModelAndView mav;
	
	@RequestMapping(value="/writepage")
	public String writePage() {
		return "boardwrite";
	}
	
	@RequestMapping(value="/boardwrite")
	public ModelAndView boardWrite(@ModelAttribute BoardDTO board) throws IllegalStateException, IOException{
		mav = bs.boardWrite(board);
		return mav;
	}
	
	@RequestMapping(value="/boardlist")
	public ModelAndView boardList() {
		mav = bs.boardList();
		return mav;
	}

	
	@RequestMapping(value="/boardview")
	public ModelAndView boardView(@RequestParam("bnumber") int bnumber,
			@RequestParam(value="page", required=false, defaultValue="1") int page) {
		mav = bs.boardView(bnumber,page);
		return mav;
	}
	
	@RequestMapping(value="/paging")
	public ModelAndView boardPaging(@RequestParam(value="page", required=false, defaultValue="1") int page) {
		mav = bs.boardPaging(page);
		return mav;
	}
			
	@RequestMapping(value="/mypage")
	public String myPage() {
		return "membermain";
	}
	
	@RequestMapping(value="/boardupdate")
	public ModelAndView boardUpdate(@RequestParam("bnumber") int bnumber) {
		mav = bs.boardUpdate(bnumber);
		return mav;
	}
	
	@RequestMapping(value="/bupdateprocess")
	public ModelAndView bupdateProcess(@ModelAttribute BoardDTO board) {
		mav = bs.bupdateProcess(board);
		return mav;
	}
	
	@RequestMapping(value="/boarddelete")
	public ModelAndView boardDelete(@RequestParam("bnumber") int bnumber) {
		mav = bs.boardDelete(bnumber);
		return mav;
	}
	
	@RequestMapping(value="/search")
	public ModelAndView boardSearch(@RequestParam("searchtype") String searchType, 
									@RequestParam("keyword") String keyword) {
		mav = bs.boardSearch(searchType, keyword);
		return mav;
	}
}
