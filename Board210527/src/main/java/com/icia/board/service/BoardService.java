package com.icia.board.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icia.board.dao.BoardDAO;
import com.icia.board.dao.CommentDAO;
import com.icia.board.dto.BoardDTO;
import com.icia.board.dto.CommentDTO;
import com.icia.board.dto.PageDTO;

@Service
public class BoardService {

	@Autowired
	private BoardDAO bdao;
	@Autowired
	private CommentDAO cdao;
	
	private ModelAndView mav;
	
	public ModelAndView boardWrite(BoardDTO board) {
		mav = new ModelAndView();
		int writeResult = bdao.boardWrite(board);
		
		// 글쓰기 성공 : 목록 출력 (목록출력을 담당하는 컨트롤러의 주소를 요청해야함.)
		// 글쓰기 실패 : boardwrite.jsp를 띄움. 
		if(writeResult > 0) {
			mav.setViewName("redirect:/boardlist");
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
		
		return mav;
	}

	public ModelAndView boardView(int bnumber, int page) {
		mav = new ModelAndView();
		// 1. 해당 글의 조회수 값 1증가 (update 쿼리)
		// 2. 해당 글의 내용 가져오기 (select 쿼리)
		bdao.boardHits(bnumber);
		
		BoardDTO board = bdao.boardView(bnumber);
		
		// 상세보기 후 목록으로 돌아갈 때 현재 페이지를 유지하기 위해 page도 같이 가져감. 
		mav.addObject("page", page);
		
		// boardview.jsp를 열 때 DB에 저장된 댓글 리스트도 함께 가져감. 
		List<CommentDTO> commentList = cdao.commentList(bnumber);
		mav.addObject("commentList", commentList);
		
		mav.addObject("board", board);
		
		mav.setViewName("boardview");
		return mav;
	}

	public ModelAndView boardUpdate(int bnumber) {
		// 이 메소드의 목적 : DB에서 bnumber에 해당하는 데이터를 가져와서 boardupdate.jsp로 목적지를 지정. 
		mav = new ModelAndView();
		BoardDTO board = bdao.boardView(bnumber);
		mav.addObject("boardUpdate", board);
		mav.setViewName("boardupdate");
		return mav;
	}

	public ModelAndView updateProcess(BoardDTO board) {
		mav = new ModelAndView();
		int updateResult = bdao.updateProcess(board);
		if(updateResult > 0) {
			//1. 목록 출력 
//			mav.setViewName("redirect:/boardlist"); //boardlist.jsp
			//2. 해당 글의 상세화면 출력 
			mav.setViewName("redirect:/boardview?bnumber="+board.getBnumber());
		}
		return mav;
	}

	public ModelAndView boardDelete(int bnumber) {
		mav = new ModelAndView();
		int deleteResult = bdao.boardDelete(bnumber);
		if(deleteResult > 0) {
			mav.setViewName("redirect:/boardlist");
		}
		return mav;
	}

	public ModelAndView boardWriteFile(BoardDTO board) throws IllegalStateException, IOException {
		mav = new ModelAndView();
		// dto에 담긴 파일을 가져옴 
		MultipartFile bfile = board.getBfile();
		// 파일 이름을 가져옴(파일이름을 DB에 저장하기 위해)
		String bfilename = bfile.getOriginalFilename();
		// 파일명 중복을 피하기 위해 파일이름앞에 현재 시간값을 붙임. 
		bfilename = System.currentTimeMillis() + "-" + bfilename;
		System.out.println("boardWriteFile 메소드 " + bfilename);
		// 파일 저장하기 
		String savePath = "E:\\source_jyh\\spring\\Board210527\\src\\main\\webapp\\resources\\upload\\"+bfilename;
		// bfile이 비어있지 않다면(즉 파일이 있으면) savePath에 저장을 하겠다.
		if(!bfile.isEmpty()) {
			bfile.transferTo(new File(savePath));
		}
		// 여기까지의 내용은 파일을 저장하는 과정 
		
		board.setBfilename(bfilename);
		bdao.boardWriteFile(board);
		
		mav.setViewName("redirect:/boardlist");
		
		return mav;
	}

	// 페이징 처리에 활용할 상수 
	private static final int PAGE_LIMIT = 2; // 한페이지에 보여질 글 개수 
	private static final int BLOCK_LIMIT = 5; // 한화면에 보여질 페이지 개수
	
	public ModelAndView boardPaging(int page) {
		mav = new ModelAndView();
		// 게시글 갯수 가져오기 
		int listCount = bdao.listCount();
		
		// 매개변수로 넘겨받은 page 변수값을 기준으로 현재 보여줘야 하는 게시글의 범위 계산 
		int startRow = (page-1) * PAGE_LIMIT + 1;
		int endRow = page * PAGE_LIMIT;
		// page=1 일 때(1페이지클릭) startRow=1, endRow=3
		// page=2 일 때(2페이지클릭) startRow=4, endRow=6
		// page=3 일 때(3페이지클릭) startRow=7, endRow=9
		
		// startRow, endRow 기준으로 boardlist 가져오기 
		PageDTO paging = new PageDTO();
		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		List<BoardDTO> boardList = bdao.boardPaging(paging);
		
		// 필요한 페이지 계산 
		// maxPage : 최대로 필요한 페이지 갯수 
		// 글갯수: 16개, 한페이지에 보여질 글갯수: 3개 => maxPage=6
		// 3.9=>4, 4.1=>5
		int maxPage = (int)(Math.ceil((double)listCount / PAGE_LIMIT));
		// 페이지 번호에서 시작할 페이지의 번호
		// 1,4,7,10~~~ (BLOCK_LIMIT=3 일때)
		// 1,6,11,16~~~ (BLOCK_LIMIT=5 일때)
		// 1,11,21,31~~~ (BLOCK_LIMIT=10 일때)
		int startPage = (((int)(Math.ceil((double)page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
		// 페이지 번호에서 마지막 페이지의 번호
		// 3,6,9,12~~~ (BLOCK_LIMIT=3 일때)
		// 5,10,15,20~~~ (BLOCK_LIMIT=5 일때)
		// 10,20,30,40~~~ (BLOCK_LIMIT=10 일때)
		int endPage = startPage + BLOCK_LIMIT - 1;
		
		/* 계산결과 전체 페이지 갯수는 4개밖에 없는데
		 * BLOCK_LIMIT 5라면 endPage는 5의 값을 갖게 됨. 
		 * 그러면 5페이지는 클릭을 해봐야 나올 데이터도 없고 필요없는 페이지 번호임. 따라서 BLOCK_LIMIT이 5라고 하더라도 
		 * 하단의 페이지 목록은 1,2,3,4 만 노출되면 되기 때문에 아래와 같은 식을 통해 endPage값을 조정.  
		 */
		if(endPage > maxPage)
			endPage = maxPage;
		
		// 계산된 page 관련 값을 PageDTO 객체에 담음 
		paging.setPage(page);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		
		mav.addObject("paging", paging);
		mav.addObject("boardList", boardList);
		mav.setViewName("boardlistpaging");
		
		return mav;
	}

	
	public ModelAndView boardSearch(String searchType, String keyword) {
		mav = new ModelAndView();
		
		// Map을 사용하여 mapper로 전달 
		Map<String, String> searchMap = new HashMap<String, String>();
		searchMap.put("type", searchType);
		searchMap.put("word", keyword);
		
		List<BoardDTO> boardList = bdao.boardSearch(searchMap);
		
		mav.addObject("boardList", boardList);
		mav.setViewName("boardlist");
		
		return mav;
	}

}












