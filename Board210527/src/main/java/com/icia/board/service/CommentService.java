package com.icia.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.board.dao.CommentDAO;
import com.icia.board.dto.CommentDTO;

@Service
public class CommentService {
	
	@Autowired
	private CommentDAO cdao;

	public List<CommentDTO> commentWrite(CommentDTO comment) {
		/*
		 * 1. 댓글 등록을 수행 
		 * 2. 댓글 등록을 성공하면 해당 게시글의 댓글 리스트를 가져오고 Controller로 리턴
		 */
		int writeResult = cdao.commentWrite(comment);
		List<CommentDTO> commentList = new ArrayList<CommentDTO>(); 
		if(writeResult > 0)
			commentList = cdao.commentList(comment.getCbnumber());
		else 
			commentList = null;
		return commentList;
	}

}












