package com.javalec.spring_pjt_board.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javalec.spring_pjt_board.command.BCommand;
import com.javalec.spring_pjt_board.command.BContentCommand;
import com.javalec.spring_pjt_board.command.BDeleteCommand;
import com.javalec.spring_pjt_board.command.BListCommand;
import com.javalec.spring_pjt_board.command.BModifyCommand;
import com.javalec.spring_pjt_board.command.BReplyViewCommand;
import com.javalec.spring_pjt_board.command.BWriteCommand;

@Controller
public class BController {
	
	BCommand command;
	
	@RequestMapping("/list")  // 글 리스트 보기
	public String list(Model model) {
		System.out.println("list()");
		
		command = new BListCommand();
		command.execute(model); //작업 수행 메서드 (인터페이스) 코드 간결
		
		return "list";
	}
	
	@RequestMapping("/write_view") // 작성하는 화면
	public String write_view(Model model) {
		
		System.out.println("write_view()");
		
		return "write_view";
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/write") // 작성한 글의 form 데이터를 받기 위해 , 작성 하는 작업
	public String write(HttpServletRequest request, Model model) {
		System.out.println("write()");
		
		model.addAttribute("request", request);
		command = new BWriteCommand();
		command.execute(model);
		return "redirect:list";
	}
	
	@RequestMapping("/content_view") //컨텐츠 보기
	public String content_view(HttpServletRequest request, Model model) {
		System.out.println("content_view()");
		
		model.addAttribute("requset", request);
		command = new BContentCommand();
		command.execute(model);
		
		return "";
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/modify") // 수정하기
	public String Modify(HttpServletRequest request, Model model) {
		System.out.println("modify()");
		
		model.addAttribute("request", request);
		command = new BModifyCommand();
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view") //답글보기
	public String reply_view(HttpServletRequest request, Model model) {
		System.out.println("reply_view()");
		
		model.addAttribute("request", request);
		command = new BReplyViewCommand();
		command.execute(model);
		return "reply_view";
	}
	
	@RequestMapping("/reply") //답글하는거
	public String reply(HttpServletRequest request, Model model) {
		System.out.println("reply()");
		
		model.addAttribute("request", request);
		command = new BReplyViewCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("delete()");
		
		model.addAttribute("request", request);
		
		command = new BDeleteCommand();
		command.execute(model);
		return "redirect:list";
	}
} 
