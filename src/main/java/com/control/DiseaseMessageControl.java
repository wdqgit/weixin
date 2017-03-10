package com.control;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.domain.po.DiseaseMessage;
import com.domain.po.PageBean;
import com.service.DiseaseMessageService;

@Controller
@RequestMapping("/disease")
public class DiseaseMessageControl {
	@Resource
	private DiseaseMessageService diseaseMessageService;
	@RequestMapping("/list")
	public ModelAndView list(Integer currentPage){
		ModelAndView mav = new ModelAndView();
		if(currentPage == null || currentPage < 1){
			currentPage = 1;
		}
		PageBean<DiseaseMessage> pageBean = diseaseMessageService.getPageBean(currentPage, 10); 
		mav.addObject("pageBean", pageBean);
		mav.setViewName("diseaseMessage/list");
		return mav;
	}
	@RequestMapping("/adminList")
	public ModelAndView adminList(Integer currentPage){
		ModelAndView mav = new ModelAndView();
		if(currentPage == null || currentPage < 1){
			currentPage = 1;
		}
		PageBean<DiseaseMessage> pageBean = diseaseMessageService.getPageBean(currentPage, 10); 
		mav.addObject("pageBean", pageBean);
		mav.setViewName("diseaseMessage/adminList");
		return mav;
	}
	@RequestMapping("/addUI")
	public String addUI(){
		return "diseaseMessage/add";
	}
	@RequestMapping("/add")
	public ModelAndView add(DiseaseMessage diseaseMessage){
		ModelAndView mav = new ModelAndView();
		diseaseMessageService.add(diseaseMessage);
		mav.addObject("message", "添加成功！");
		mav.addObject("path", "adminList.do");
		mav.setViewName("success");
		return mav;
	}
	@RequestMapping("/updateUI")
	public ModelAndView updateUI(Long id){
		ModelAndView mav = new ModelAndView();
		DiseaseMessage diseaseMessage = diseaseMessageService.get(id);
		mav.addObject("diseaseMessage", diseaseMessage);
		mav.setViewName("diseaseMessage/update");
		return mav;
	}
	@RequestMapping("/update")
	public ModelAndView update(DiseaseMessage diseaseMessage){
		ModelAndView mav = new ModelAndView();
		diseaseMessageService.update(diseaseMessage);
		mav.addObject("message", "修改成功！");
		mav.addObject("path", "adminList.do");
		mav.setViewName("success");
		return mav;
	}
	@RequestMapping("/delete")
	public String delete(Long id){
		diseaseMessageService.delete(id);
		return "redirect:adminList.do";
	}
	@RequestMapping("/find")
	public ModelAndView find(String key){
		ModelAndView mav = new ModelAndView();
		List<DiseaseMessage> diseaseMessages = diseaseMessageService.find(key);
		mav.addObject("diseaseMessages", diseaseMessages);
		mav.setViewName("diseaseMessage/find");
		return mav;
	}
	@RequestMapping(("/view"))
	public ModelAndView view(Long id){
		ModelAndView mav = new ModelAndView();
		DiseaseMessage diseaseMessage = diseaseMessageService.get(id);
		mav.addObject("diseaseMessage", diseaseMessage);
		mav.setViewName("diseaseMessage/view");
		return mav;
	}
}
