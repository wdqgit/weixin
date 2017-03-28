package com.control;

import java.net.URLDecoder;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.domain.po.Activity;
import com.domain.po.Admin;
import com.domain.po.People;
import com.domain.po.Sex;
import com.service.PeopleService;
import com.util.MessageUtil;

@Controller
@RequestMapping("/people")
public class PeopleControl {
	@Resource
	private PeopleService peopleService;
	@RequestMapping("/registerUI")
	public String registerUI(){
		return "people/register";
	}
	@RequestMapping("/register")
	public ModelAndView register(Admin admin, HttpServletRequest req){
		ModelAndView mav = new ModelAndView();
		peopleService.saveAdmin(admin);
		mav.setViewName("redirect:listAdmin.do");
		return mav;
	}
	@RequestMapping("/listAdmin")
	public ModelAndView listAdmin(){
		ModelAndView mav = new ModelAndView();
		List<Admin> admins = peopleService.findAdmin();
		mav.addObject("admins", admins);
		mav.setViewName("people/adminList");
		return mav;
	}
	@RequestMapping("/deleteAdmin")
	public ModelAndView deleteAdmin(Long id){
		ModelAndView mav = new ModelAndView();
		peopleService.deleteAdmin(id);
		mav.setViewName("redirect:listAdmin.do");
		return mav;
	}
	@ResponseBody
	@RequestMapping("/sendIdCode")
	public void sendIdCode(HttpServletRequest req, String phone){
		int code = (int)((Math.random()*9+1)*100000);
		HttpSession session = req.getSession();
		session.setAttribute("code", code);
		session.setMaxInactiveInterval(60);
		System.out.println("sendIdCode phone:" + phone);
		MessageUtil.sendDuanXin(code, phone);
	}
	@RequestMapping("/loginUI")
	public ModelAndView loginUI(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("people/login");
		return mav;
	}
	@RequestMapping("/login")
	public ModelAndView login(String phone, String idCode, HttpServletRequest req){
		ModelAndView mav = new ModelAndView();
		Admin admin = peopleService.getAdminByPhone(phone);
		if(admin != null){
			/*String code = String.valueOf(req.getSession().getAttribute("code"));
			if(code != null){
				if(code.equals(idCode)){*/
					req.getSession().setAttribute("admin", admin);
					mav.addObject("type", "admin");
					String path = req.getParameter("path");
					if(path != null && !"".equals(path)){
						path = URLDecoder.decode(path);
						mav.setViewName("redirect:" + path);
					}else{
						mav.setViewName("redirect:/admin/index.do");
					}
					return mav;
			/*	}else{
					mav.addObject("message", "验证码错误！");
				}
			}else{
				mav.addObject("message", "验证码在60秒内有效！");
			}*/
		}else{
			People people = peopleService.getByPhone(phone);
			if(people != null){
				//String code = String.valueOf(req.getSession().getAttribute("code"));
//				if(code != null){
//					if(code.equals(idCode)){
						req.getSession().setAttribute("people", people);
						mav.addObject("type", "people");
						String path = req.getParameter("path");
						if(path != null && !"".equals(path)){
							path = URLDecoder.decode(path);
							mav.setViewName("redirect:" + path);
						}else{
							mav.setViewName("redirect:/activity/list.do");
						}
						return mav;
//					}else{
//						mav.addObject("message", "验证码错误！");
//					}
//				}else{
//					mav.addObject("message", "验证码在60秒内有效！");
//				}
			}else{
				String code = String.valueOf(req.getSession().getAttribute("code"));
				if(code != null){
					if(code.equals(idCode)){
						people = new People();
						people.setPhone(phone);
						peopleService.save(people);
						req.getSession().setAttribute("people", people);
						mav.addObject("type","people");
						String path = req.getParameter("path");
						if(path != null && !"".equals(path)){
							path = URLDecoder.decode(path);
							mav.setViewName("redirect:" + path);
						}else{
							mav.setViewName("redirect:/activity/list.do");
						}
						return mav;
					}else{
						mav.addObject("message", "验证码错误！");
					}
				}else{
					mav.addObject("message", "验证码在60秒内有效！");
				}
				
			}
		}
		mav.setViewName("people/login");
		return mav;
	}
	@RequestMapping("/view")
	public ModelAndView view(Long id, String type, HttpServletRequest req){
		ModelAndView mav = new ModelAndView();
		if(id == null || id <= 0){
			if(req.getSession().getAttribute("people") != null){
				People people = (People) req.getSession().getAttribute("people");
				mav.addObject("people", people);
				mav.addObject("type", "people");
				mav.setViewName("/people/view");
				return mav;
			}else if(req.getSession().getAttribute("admin") != null){
				Admin admin = (Admin) req.getSession().getAttribute("admin");
				mav.addObject("admin", admin);
				mav.addObject("type", "admin");
				mav.setViewName("/people/view");
				return mav;
			}
		}
		System.out.println(type);
		if("admin".equals(type)){
			Admin admin = peopleService.getAdminById(id);
			mav.addObject("admin", admin);
			mav.addObject("type", "admin");
		}else{
			People people = peopleService.get(id);
			mav.addObject("people", people);
			mav.addObject("type", "people");
		}
		mav.setViewName("/people/view");
		
		
		return mav;
	}
	@RequestMapping("/updateUI")
	public ModelAndView updateUI(Long id, String type){
		ModelAndView mav = new ModelAndView();
		if("admin".equals(type)){
			Admin admin = peopleService.getAdminById(id);
			mav.addObject("admin", admin);
			mav.addObject("type", "admin");
		}else{
			People people = peopleService.get(id);
			mav.addObject("people", people);
			mav.addObject("type", "people");
		}
		mav.setViewName("/people/update");
		
		return mav;
	}
	@RequestMapping("/update")
	public ModelAndView update(String type,Long id, String name, String address, String sex, String phone){
		ModelAndView mav = new ModelAndView();
		if("admin".equals(type)){
			Admin admin = new Admin();
			admin.setId(id);
			admin.setName(name);
			admin.setAddress(address);
			admin.setPhone(phone);
			peopleService.updateAdmin(admin);
		}else{
			People people = new People();
			people.setId(id);
			people.setName(name);
			people.setAddress(address);
			people.setSex(Sex.valueOf(sex));
			people.setPhone(phone);
			peopleService.updatePeople(people);
			
		}
		
		mav.addObject("type", type);
		mav.setViewName("redirect:/activity/list.do");
		
		return mav;
	}
	@RequestMapping("/logout")
	public String logout(String type, HttpServletRequest req){
		if("admin".equals(type)){
			req.getSession().removeAttribute("admin");
		}else if("people".equals(type)){
			req.getSession().removeAttribute("people");
		}
		return "redirect:loginUI.do";
	}
	
	@RequestMapping("/viewMyActivitys")
	public ModelAndView viewMyActivitys(Long id){
		ModelAndView mav = new ModelAndView();
		People people = peopleService.get(id);
		mav.addObject("activitys", people.getActivitys());
		mav.setViewName("people/viewMyActivitys");
		
		return mav;
	}
	@RequestMapping("/remove")
	public ModelAndView remove(Long activityId, Long peopleId){
		ModelAndView mav = new ModelAndView();
		People people = peopleService.get(peopleId);
		Set<Activity> activitys = people.getActivitys();
		for(Activity activity : activitys){
			if(activity.getId() == activityId){
				activitys.remove(activity);
			}
		}
		people.setActivitys(activitys);
		peopleService.updatePeople(people);
		mav.setViewName("redirect:viewMyActivitys.do?id=" + people.getId());
		return mav;
	}
	
	@RequestMapping("/me")
	public String me(){
		return "me";
	}
}
