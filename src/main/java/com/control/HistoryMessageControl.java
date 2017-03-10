package com.control;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.domain.po.HistoryMessage;
import com.domain.po.PageBean;
import com.service.HistoryMessageService;

@Controller
@RequestMapping("/history")
public class HistoryMessageControl {
	@Resource
	private HistoryMessageService historyMessageService;
	@RequestMapping("/list")
	public ModelAndView list(Integer currentPage){
		ModelAndView mav = new ModelAndView();
		if(currentPage == null || currentPage < 1){
			currentPage = 1;
		}
		PageBean<HistoryMessage> pageBean = historyMessageService.getPageBean(currentPage, 10); 
		mav.addObject("pageBean", pageBean);
		mav.setViewName("historyMessage/list");
		return mav;
	}
	@RequestMapping("/addUI")
	public String addUI(){
		return "historyMessage/add";
	}
	@RequestMapping("/add")
	public ModelAndView add(HistoryMessage historyMessage){
		ModelAndView mav = new ModelAndView();
		historyMessageService.add(historyMessage);
		mav.addObject("message", "添加成功！");
		mav.addObject("path", "adminList.do");
		mav.setViewName("success");
		return mav;
	}
	@RequestMapping("/updateUI")
	public ModelAndView updateUI(Long id){
		ModelAndView mav = new ModelAndView();
		HistoryMessage historyMessage = historyMessageService.get(id);
		mav.addObject("historyMessage", historyMessage);
		mav.setViewName("historyMessage/update");
		return mav;
	}
	@RequestMapping("/update")
	public ModelAndView update(HistoryMessage historyMessage){
		ModelAndView mav = new ModelAndView();
		historyMessageService.update(historyMessage);
		mav.addObject("message", "修改成功！");
		mav.addObject("path", "adminList.do");
		mav.setViewName("success");
		return mav;
	}
	@RequestMapping("/delete")
	public String delete(Long id){
		historyMessageService.delete(id);
		return "redirect:adminList.do";
	}
	@RequestMapping(("/view"))
	public ModelAndView view(Long id){
		ModelAndView mav = new ModelAndView();
		HistoryMessage historyMessage = historyMessageService.get(id);
		mav.addObject("historyMessage", historyMessage);
		mav.setViewName("historyMessage/view");
		return mav;
	}
	@RequestMapping("/adminList")
	public ModelAndView adminList(Integer currentPage){
		ModelAndView mav = new ModelAndView();
		if(currentPage == null || currentPage < 1){
			currentPage = 1;
		}
		PageBean<HistoryMessage> pageBean = historyMessageService.getPageBean(currentPage, 10); 
		mav.addObject("pageBean", pageBean);
		mav.setViewName("historyMessage/adminList");
		return mav;
	}
}
