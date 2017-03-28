package com.control;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.util.WeiXinUtil;

@Controller
@RequestMapping("/qrcode")
public class QRCodeControl {
	
	@RequestMapping("/list")
	public ModelAndView list(String path, String yjpath){
		ModelAndView mav = new ModelAndView();
		mav.addObject("path", path);
		mav.addObject("yjpath", yjpath);
		mav.setViewName("qrcode");
		return mav;
	}

	@RequestMapping("/createQRCode")
	public ModelAndView createQRCode(){
		ModelAndView mav = new ModelAndView();
		String ticket = WeiXinUtil.createQRCode();
		String url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
		try {
			url = url.replace("TICKET", URLEncoder.encode(ticket, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.addObject("path", url);
		mav.setViewName("qrcode");
		return mav;
	}
	@RequestMapping("/createYJQRCode")
	public ModelAndView createYJQRCode(){
		ModelAndView mav = new ModelAndView();
		String ticket = WeiXinUtil.createYJQRCode();
		String url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
		try {
			url = url.replace("TICKET", URLEncoder.encode(ticket, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.addObject("yjpath", url);
		mav.setViewName("qrcode");
		return mav;
	}
	
}
