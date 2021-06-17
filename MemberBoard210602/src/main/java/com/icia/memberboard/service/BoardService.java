package com.icia.memberboard.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icia.memberboard.dto.CommentDTO;
import com.icia.memberboard.dto.PageDTO;
import com.icia.memberboard.dao.BoardDAO;
import com.icia.memberboard.dao.CommentDAO;
import com.icia.memberboard.dto.BoardDTO;

@Service
public class BoardService {

	@Autowired
	private BoardDAO bdao;
	@Autowired
	private CommentDAO cdao;
	
	private ModelAndView mav;

	public ModelAndView boardWrite(BoardDTO board) throws IllegalStateException, IOException {
		mav = new ModelAndView();
		MultipartFile bfile = board.getBfile();
		String bfilename = bfile.getOriginalFilename();
		bfilename = System.currentTimeMillis() + "-" + bfilename;
		System.out.println("boardWriteFile 메소드 " + bfilename);
		String savePath = "C:\\source_hhj\\spring\\MemberBoard210602\\src\\main\\webapp\\resources\\upload\\"+bfilename;
		board.setBfilename(bfilename);
		bfile.transferTo(new File(savePath));
		int writeResult = bdao.boardWrite(board);
		if(writeResult > 0) {
			mav.setViewName("boardlist");
		} else {
			mav.setViewName("boardwrite");
		}
		return mav;
	}
	public ModelAndView boardList() {
		mav = new ModelAndView();
		List<BoardDTO> boardList = bdao.boardList();
		
		mav.addObject("boardList", boardList);
		mav.setViewName("boardlist");
		return null;
	}



	public ModelAndView boardView(int bnumber, int page) {
		mav = new ModelAndView();
		bdao.boardHits(bnumber);
		
		BoardDTO board = bdao.boardView(bnumber);
		
		mav.addObject("page", page);
		
		List<CommentDTO> commentList = cdao.commentList(bnumber);
		mav.addObject("commentList", commentList);
		
		mav.addObject("board", board);
		
		mav.setViewName("boardview");
		return mav;
	}
	
	private static final int PAGE_LIMIT = 2;  
	private static final int BLOCK_LIMIT = 5;
	public ModelAndView boardPaging(int page) {
		mav = new ModelAndView();
		int listCount = bdao.listCount(); 
		int startRow = (page-1) * PAGE_LIMIT + 1;
		int endRow = page * PAGE_LIMIT; 
		PageDTO paging = new PageDTO();
		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		List<BoardDTO> boardList = bdao.boardPaging(paging);
		
		int maxPage = (int)(Math.ceil((double)listCount / PAGE_LIMIT));
		int startPage = (((int)(Math.ceil((double)page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
		int endPage = startPage + BLOCK_LIMIT - 1;
		if(endPage > maxPage)
			endPage = maxPage;
		
		paging.setPage(page);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		
		mav.addObject("paging", paging);
		mav.addObject("boardList", boardList);
		mav.setViewName("boardlistpaging");
		
		return mav;
	}
	public ModelAndView boardUpdate(int bnumber) {
		mav = new ModelAndView();
		BoardDTO board = bdao.boardView(bnumber);
		mav.addObject("boardUpdate", board);
		mav.setViewName("boardupdate");
		return mav;
	}
	public ModelAndView bupdateProcess(BoardDTO board) {
		mav = new ModelAndView();
		int updateResult = bdao.bupdateProcess(board);
		if(updateResult > 0) {
			mav.setViewName("redirect:/boardview?bnumber="+board.getBnumber());
		}
		return mav;
	}
	public ModelAndView boardDelete(int bnumber) {
		mav = new ModelAndView();
		int deleteResult = bdao.boardDelete(bnumber);
		if(deleteResult > 0) {
			mav.setViewName("redirect:/paging");
		}
		return mav;
	}
	public ModelAndView boardSearch(String searchType, String keyword) {
		mav = new ModelAndView();
		Map<String, String> searchMap = new HashMap<String, String>();
		searchMap.put("type", searchType);
		searchMap.put("word", keyword);
		List<BoardDTO> boardList = bdao.boardSearch(searchMap);
		mav.addObject("boardList", boardList);
		mav.setViewName("boardlist");
		return mav;
	}
	
}


