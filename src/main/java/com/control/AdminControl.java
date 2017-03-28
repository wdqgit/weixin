package com.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminControl {
	@RequestMapping("/index")
	public String index(){
		return "admin/index";
	}
	@RequestMapping("/left")
	public String left(){
		return "admin/left";
	}
	@RequestMapping("/content")
	public String content(){
		return "admin/content";
	}
	@RequestMapping("/top")
	public String top(){
		return "admin/top";
	}
}
