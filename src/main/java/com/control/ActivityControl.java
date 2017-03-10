package com.control;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.domain.po.Activity;
import com.domain.po.Admin;
import com.domain.po.People;
import com.service.ActivityService;
import com.service.PeopleService;

@Controller
@RequestMapping("/activity")
public class ActivityControl {
	@Resource
	private ActivityService activityService;
	@Resource
	private PeopleService peopleService;
	@RequestMapping("/list")
	public ModelAndView findRun(String type, HttpServletRequest req){
		ModelAndView mav = new ModelAndView();
		List<Activity> activitys = activityService.findRun();
		mav.addObject("activitys", activitys);
		mav.addObject("type", type);
		/*重新更新admin和people*/
		if("admin".equals(type)){
			Admin adminsession = (Admin)req.getSession().getAttribute("admin");
			Admin admin = peopleService.getAdminById(adminsession.getId());
			req.getSession().setAttribute("admin", admin);
		}else{
			People peoplesession = (People) req.getSession().getAttribute("people");
			People people = peopleService.get(peoplesession.getId());
			req.getSession().setAttribute("people", people);
		}
		mav.setViewName("activity/list");
		return mav;
	}
	
	
	@RequestMapping("/addUI")
	public String addUI(){
		return "activity/add";
	}
	@RequestMapping("/add")
	public ModelAndView add(Activity activity){
		ModelAndView mav = new ModelAndView();
		System.out.println(activity.getContent());
		activityService.save(activity);
		mav.addObject("message", "添加成功");
		mav.addObject("path", "list.do?type=admin");
		mav.setViewName("success");
		return mav;
	}
	@RequestMapping("/updateUI")
	public ModelAndView updateUI(Long id){
		ModelAndView mav = new ModelAndView();
		Activity activity = activityService.get(id);
		mav.addObject("activity", activity);
		mav.setViewName("activity/update");
		
		return mav;
	}
	@RequestMapping("/update")
	public ModelAndView update(Activity activity){
		ModelAndView mav = new ModelAndView();
		activityService.update(activity);
		mav.addObject("message", "修改成功");
		mav.setViewName("success");
		return mav;
	}

	@RequestMapping("/delete")
	public ModelAndView delete(Long id){
		ModelAndView mav = new ModelAndView();
		activityService.delete(id);
		mav.addObject("type", "admin");
		mav.setViewName("redirect:list.do");
		return mav;
	}
	@RequestMapping("/view")
	public ModelAndView view(Long id, String type){
		ModelAndView mav = new ModelAndView();
		Activity activity = activityService.get(id);
		mav.addObject("activity", activity);
		mav.addObject("type", type);
		mav.setViewName("/activity/view");
		return mav;
	}
	@RequestMapping("/addPeople")
	public ModelAndView addPeople(Long peopleid, Long activityid){
		ModelAndView mav = new ModelAndView();
		Activity activity = activityService.get(activityid);
		People people = peopleService.get(peopleid);
		System.out.println(people.getId());
		Set<People> peoples = activity.getPeoples();
		if(peoples == null){
			peoples = new HashSet<People>();
		}
		if(!peoples.contains(people)){
			peoples.add(people);
		}
		activity.setPeoples(peoples);
		activityService.update(activity);
		mav.addObject("type", "people");
		mav.setViewName("redirect:list.do");
		return mav;
	}
	

}
