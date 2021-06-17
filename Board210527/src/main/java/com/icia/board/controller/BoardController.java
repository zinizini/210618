package com.icia.board.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icia.board.dto.BoardDTO;
import com.icia.board.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService bs;
	
	private ModelAndView mav;
	
	// 글쓰기 화면을 띄우는 메소드
	@RequestMapping(value="/writepage")
	public String writePage() {
		return "boardwrite";
	}
	// 글쓰기 처리를 하는 메소드
	@RequestMapping(value="/boardwrite")
	public ModelAndView boardWrite(@ModelAttribute BoardDTO board) {
		
		mav = bs.boardWrite(board);
		return mav;
	}
	
	// 파일첨부 글쓰기 
	@RequestMapping(value="/boardwritefile")
	public ModelAndView boardWriteFile(@ModelAttribute BoardDTO board) throws IllegalStateException, IOException {
		mav = bs.boardWriteFile(board);
		return mav;
	}
	
	//글목록
	@RequestMapping(value="/boardlist")
	public ModelAndView boardList() {
		mav = bs.boardList();
		return mav;
	}
	
	//글조회 
	@RequestMapping(value="/boardview")
	public ModelAndView boardView(@RequestParam("bnumber") int bnumber,
					@RequestParam(value="page", required=false, defaultValue="1") int page) {
		mav = bs.boardView(bnumber, page);
		return mav;
	}
	
	//수정화면 요청 
	@RequestMapping(value="/boardupdate")
	public ModelAndView boardUpdate(@RequestParam("bnumber") int bnumber) {
		mav = bs.boardUpdate(bnumber);
		return mav;
	}
	
	//수정처리
	@RequestMapping(value="/updateprocess")
	public ModelAndView updateProcess(@ModelAttribute BoardDTO board) {
		mav = bs.updateProcess(board);
		return mav;
	}
	
	//삭제처리
	@RequestMapping(value="/boarddelete")
	public ModelAndView boardDelete(@RequestParam("bnumber") int bnumber) {
		mav = bs.boardDelete(bnumber);
		return mav;
	}
	
	//페이징처리
	@RequestMapping(value="/paging")
	public ModelAndView boardPaging(@RequestParam(value="page", required=false, defaultValue="1") int page) {
		mav = bs.boardPaging(page);
		return mav;
	}
	
	//검색처리
	@RequestMapping(value="/search")
	public ModelAndView boardSearch(@RequestParam("searchtype") String searchType, 
									@RequestParam("keyword") String keyword) {
		mav = bs.boardSearch(searchType, keyword);
		return mav;
	}
	
	
}













