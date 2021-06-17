package com.icia.memberboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icia.memberboard.dto.CommentDTO;
import com.icia.memberboard.service.CommentService;


@Controller
@RequestMapping("/comment/*")
public class CommentController {
	@Autowired
	private CommentService cs;
	
	private ModelAndView mav;
	
	@RequestMapping(value="commentwrite")
	public @ResponseBody List<CommentDTO> commentWrite(@ModelAttribute CommentDTO comment) {
		List<CommentDTO> commentList = cs.commentWrite(comment);
		return commentList;
	}
	

}
