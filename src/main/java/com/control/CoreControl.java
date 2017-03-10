package com.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.domain.po.AccessToken;
import com.domain.po.qunfa.Article;
import com.domain.po.qunfa.NewsMessage;
import com.service.CoreService;
import com.util.SignUtil;
import com.util.WeiXinUtil;

@Controller
@RequestMapping("/core")
public class CoreControl {
	@Resource
	private CoreService coreService;

	@RequestMapping(value = "/CoreControl", method = RequestMethod.GET)
	public void checkServer(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String signature = req.getParameter("signature");
		// ʱ���
		String timestamp = req.getParameter("timestamp");
		// �����
		String nonce = req.getParameter("nonce");
		// ����ַ���
		String echostr = req.getParameter("echostr");
		PrintWriter out = resp.getWriter();
		// ͨ������ signature���������У�飬��У��ɹ���ԭ������
		// echostr����ʾ����ɹ�������ʧ��

		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		out.close();
		out = null;

	}

	@RequestMapping(value = "/CoreControl", method = RequestMethod.POST)

	public void getMessage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		System.out.println("-----");
		String respMessage = coreService.processRequest(req);
		System.out.println(respMessage);
		PrintWriter out = resp.getWriter();
		out.print(respMessage);
		out.flush();
		out.close();

	}

	@RequestMapping("/test")
	public void test(String[] title) {
		System.out.println(title.length);
	}

	@RequestMapping("/getQRCode")
	public ModelAndView getQRCode() {
		ModelAndView mav = new ModelAndView();
		String url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
		String ticket = "gQGC7zoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL2p6dEpWcnZsNGlxYlJuS0hsaE9uAAIEK8UjWAMEAAAAAA==";
		try {
			url = url.replace("TICKET", URLEncoder.encode(ticket, "UTF-8"));
			mav.addObject("url", url);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.setViewName("mass/qrcode");
		return mav;

	}

	@RequestMapping("/sendNewsUI")
	public String sendNewsUI() {
		return "mass/news";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/sendNews")
	public String sendNews(@RequestParam("file") MultipartFile file, Article article, HttpServletRequest req) {

		String path = WeiXinUtil.uploadFile(file, req);
		AccessToken token = WeiXinUtil.getAccessToken(req);
		try {
			String media_id = WeiXinUtil.upload(path, token.getToken(), file.getContentType());
			article.setThumb_media_id(media_id);
			article.setShow_cover_pic(1);
			article.setAuthor(article.getAuthor());
			article.setContent(article.getContent());
			article.setTitle(article.getTitle());
			article.setDigest(article.getDigest());
			try {
				NewsMessage newsMessage = new NewsMessage();
				newsMessage.setArticles(new Article[] { article });
				String news_media_id = WeiXinUtil.uploadNews(token.getToken(), newsMessage);
				Map news = new HashMap();
				Map filter = new HashMap();
				filter.put("is_to_all", false);
				filter.put("group_id", 101);
				news.put("filter", filter);
				Map mpnews = new HashMap();
				mpnews.put("media_id", news_media_id);
				news.put("mpnews", mpnews);
				news.put("msgtype", "mpnews");
				String errcode = WeiXinUtil.sendMessage(token.getToken(), news);
				if (errcode.equals("0")) {
					return "mass/sendSuccess";
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "sendError";
	}

	@RequestMapping("/sendTextUI")
	public String sendTextUI() {
		return "mass/sendText";
	}

	@RequestMapping("/sendText")
	public String sendText(String content, HttpServletRequest req) throws UnsupportedEncodingException {
		Map textMessage = new HashMap();
		Map filter = new HashMap();
		filter.put("is_to_all", true);
		//filter.put("group_id", 101);
		textMessage.put("filter", filter);
		Map text = new HashMap();

		;
		System.out.println(content);
		text.put("content", content);

		textMessage.put("text", text);
		textMessage.put("msgtype", "text");
		AccessToken token = WeiXinUtil.getAccessToken(req);
		try {
			String errcode = WeiXinUtil.sendMessage(token.getToken(), textMessage);
			if (errcode.equals("0")) {
				return "mass/sendSuccess";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "sendError";
	}

	@RequestMapping("/sendImgUI")
	public String sendImgUI() {
		return "mass/sendImg";
	}

	@RequestMapping("/sendImg")
	public String sendImg(@RequestParam("file") MultipartFile file, HttpServletRequest req) {
		String path = WeiXinUtil.uploadFile(file, req);
		AccessToken token = WeiXinUtil.getAccessToken(req);
		String media_id = null;
		try {
			media_id = WeiXinUtil.uploadImg(path, token.getToken(), "image");
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		Map imgMessage = new HashMap();
		Map filter = new HashMap();
		filter.put("is_to_all", true);
		imgMessage.put("filter", filter);
		Map image = new HashMap();
		image.put("media_id", media_id);

		imgMessage.put("image", image);
		imgMessage.put("msgtype", "image");
		System.out.println(imgMessage);
		try {
			String errcode = WeiXinUtil.sendMessage(token.getToken(), imgMessage);
			if (errcode.equals("0")) {
				return "mass/sendSuccess";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "sendError";
	}

	@RequestMapping("/sendVideoUI")
	public String sendVideoUI() {
		return "mass/sendVideo";
	}

	@RequestMapping("/sendVideo")
	public String sendVideo(@RequestParam("file") MultipartFile file, String title, String description,
			HttpServletRequest req) {
		String path = WeiXinUtil.uploadFile(file, req);
		AccessToken token = WeiXinUtil.getAccessToken(req);
		String media_id = null;
		try {
			media_id = WeiXinUtil.upload(path, token.getToken(), file.getContentType());
			title = new String(title.getBytes("ISO-8859-1"), "UTF-8");
			description = new String(description.getBytes("ISO-8859-1"), "UTF-8");
			Map<String, String> video = new HashMap<String, String>();
			video.put("media_id", media_id);
			video.put("title", title);
			video.put("description", description);
			String videoMedia_id = WeiXinUtil.uploadVideo(token.getToken(), video);

			Map videoMessage = new HashMap();
			Map filter = new HashMap();
			filter.put("is_to_all", true);
			videoMessage.put("filter", filter);
			Map mpvideo = new HashMap();
			mpvideo.put("media_id", videoMedia_id);

			videoMessage.put("mpvideo", mpvideo);
			videoMessage.put("msgtype", "mpvideo");

			String errcode = WeiXinUtil.sendMessage(token.getToken(), videoMessage);
			if (errcode.equals("0")) {
				return "mass/sendSuccess";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "sendError";
	}

}
