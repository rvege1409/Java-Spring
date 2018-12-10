package com.javalec.springex;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController {

	@RequestMapping("/studentConfirm")
	public String studentRedirect(HttpServletRequest hsr, Model model) {
		
		
		String id = hsr.getParameter("id");
		if(id.equals("abc")) {
			return "redirect:studentOK";
		}
		return "redirect:studentNg";
	}
	
	@RequestMapping("/studentOK")
	public String studentOk(Model model) {
		return "student/studentOk";
	}
	
	@RequestMapping("/studentNg")
	public String studentNg(Model model) {
		return "student/studentNg";
	}
	
	@RequestMapping("/studentURL1")
	public String studentURL1(Model model) {
		return "redirect:http://localhost:8181/springex/studentURL1.jsp";
	}
	
	@RequestMapping("/studentURL2")
	public String studentURL2(Model model) {
		return "redirect:student/studentURL2.jsp";
	}
}
