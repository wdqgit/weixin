package com.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.domain.po.AccessToken;
import com.domain.po.Button;
import com.domain.po.ClickButton;
import com.domain.po.Menu;
import com.domain.po.ViewButton;
import com.domain.po.erWeiMa.Action_info;
import com.domain.po.erWeiMa.QRCode;
import com.domain.po.erWeiMa.Scene;
import com.domain.po.qunfa.Article;
import com.domain.po.qunfa.NewsMessage;
import com.domain.resp.TextMessage;

import net.sf.json.JSONObject;

public class WeiXinUtil {
	
	private static final String APPID = "wxd936d0397e4c0a2d"/*"wx0d351ff0e914aa3c"*/;
	private static final String APPSECRET = "0491262045d1eda770224df578635815"/*"614339c38872d94d6f91b2f016874111"*/;
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	private static final String CONFIG_PATH = "config.properties";
	private static final int TOKEN_TIME = 700000;
	private static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	private static final String CREATE_QRCODE_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN"; 
	
	
	public static JSONObject doGetStr(String url){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		JSONObject jsonObject = null;
		try {
			CloseableHttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity,"UTF-8");
			jsonObject = JSONObject.fromObject(result);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}
	public static JSONObject doPostStr(String url, String outStr){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		JSONObject jsonObject = null;
		httpPost.setEntity(new StringEntity(outStr, "UTF-8"));
		try {
			CloseableHttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, "UTF-8");
			jsonObject = JSONObject.fromObject(result);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	public static AccessToken getAccessToken1(){
		AccessToken token = new AccessToken();
		String url = null;
		try {
			url = ACCESS_TOKEN_URL.replace("APPID", URLEncoder.encode(APPID, "UTF-8")).replace("APPSECRET", URLEncoder.encode(APPSECRET, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jsonObject = doGetStr(url);
		if(jsonObject != null){
			token.setToken(jsonObject.getString("access_token"));
			token.setExpires(jsonObject.getInt("expires_in"));
		}
		return token;
	}
	
	public static AccessToken getAccessToken(HttpServletRequest req){
		String path = req.getSession().getServletContext().getRealPath("/WEB-INF/classes/" + CONFIG_PATH);
		PropertiesUtil putil = new PropertiesUtil(path);
		Long overduetime = Long.parseLong(putil.getProperty("OVERDUE_ACCESS_TOKEN_TIME") == null ? "0" : putil.getProperty("OVERDUE_ACCESS_TOKEN_TIME"));
		Long time = new Date().getTime();
		AccessToken token = new AccessToken();
		if(time >= overduetime){
			token = new WeiXinUtil().getAccessToken1();
			putil.writeProperties("OVERDUE_ACCESS_TOKEN_TIME", String.valueOf(time+TOKEN_TIME));
			putil.writeProperties("ACCESS_TOKEN", token.getToken());
		}else{
			token.setToken(putil.getProperty("ACCESS_TOKEN"));
			token.setExpires(7200);
		}
		
		
		return token;
	}
	/**
	 * 文件上传
	 * @param filePath
	 * @param accessToken
	 * @param type
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws KeyManagementException
	 */
	public static String upload(String filePath, String accessToken,String type) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			throw new IOException("文件不存在");
		}

		String url = UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE",type);
		
		URL urlObj = new URL(url);
		//连接
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

		con.setRequestMethod("POST"); 
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); 

		//设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");

		//设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

		StringBuilder sb = new StringBuilder();
		sb.append("--");
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");

		byte[] head = sb.toString().getBytes("utf-8");

		//获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		//输出表头
		out.write(head);

		//文件正文部分
		//把文件已流文件的方式 推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();

		//结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");//定义最后数据分隔线

		out.write(foot);

		out.flush();
		out.close();

		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		String result = null;
		try {
			//定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}

		JSONObject jsonObj = JSONObject.fromObject(result);
		System.out.println(jsonObj);
		String typeName = "media_id";
		if(!type.contains("image")){
			typeName = type + "_media_id";
		}
		String mediaId = jsonObj.getString(typeName);
		return mediaId;
	}
	
	public static Menu initMenu(){
		Menu menu = new Menu();
		ClickButton button11 = new ClickButton();
		button11.setName("click菜单");
		button11.setType("click");
		button11.setKey("11");
		
		ViewButton button21 = new ViewButton();
		button21.setName("view菜单");
		button21.setType("view");
		button21.setUrl("http://yizhen.tunnel.qydev.com/weixin/core/sendTextUI.do");
		
		ClickButton button32 = new ClickButton();
		button32.setName("扫码事件");
		button32.setType("scancode_push");
		button32.setKey("32");
		
		ClickButton button33 = new ClickButton();
		button33.setName("地理位置事件");
		button33.setType("location_select");
		button33.setKey("33");
		
		Button button31 = new Button();
		button31.setName("事件");
		button31.setSub_button(new Button[]{button32,button33});
		menu.setButton(new Button[]{button11,button21,button31});
		return menu;
	}
	
	public static int createMenu(String token, String menu){
		int result = 0;
		String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
		System.out.println(menu);
		JSONObject jsonObject = doPostStr(url, menu);
		if(jsonObject != null){
			result = jsonObject.getInt("errcode");
			System.out.println(result);
		}
		return result;
	}
	
	public static String menuText(String fromUserName, String toUserName){
		TextMessage  textMessage  =  new  TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new  Date().getTime());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setFuncFlag(0);
		//�ı���Ϣ
		textMessage.setContent("欢迎您的关注，请按照菜单提示进行操作：\n"
				+ "1.文本消息回复\n"
				+ "2.图文消息回复\n"
				+ "3.图片消息回复\n"
				+"4.百度翻译\n"
				
				+"回复？显示主菜单");
		return MessageUtil.textMessageToXml(textMessage);
	
		}
	
	public static String translate(String query, String to) throws UnsupportedEncodingException{
		HashMap<String, String> yuyan = new HashMap<String, String>(){
			{	put("zh", "中文");
				put("en", "英语");
				put("yue", "粤语");
				put("wyw", "wyw");
				put("jp", "日语");
				put("kor", "韩语");
				put("fra", "法语");
				put("spa", "西班牙语");
				put("th", "泰语");
				put("ara", "阿拉伯语");
				put("ru", "俄语");
				put("pt", "葡萄牙语");
				put("de", "德语");
				put("it", "意大利语");
				put("el", "希腊语");
				put("nl", "荷兰语");
				put("pl", "波兰语");
				put("bul", "保加利亚语");
				put("est", "爱沙尼亚语");
				put("dan", "丹麦语");
				put("fin", "芬兰语");
				put("cs", "捷克语");
				put("rom", "罗马尼亚语");
				put("slo", "斯洛文尼亚语");
				put("swe", "瑞典语");
				put("hu", "匈牙利语");
				put("cht", "繁体中文");
			}
		};
		boolean flag = false;
		for(Entry<String, String> entry : yuyan.entrySet()){
			if(entry.getKey().equals(to)){
				flag = true;
				break;
			}else if(entry.getValue().contains(to)){
				to = entry.getKey();
				flag = true;
				break;
			}
		}
		if(!flag){
			to = "auto";
		}
		
		
		String url = "http://api.fanyi.baidu.com/api/trans/vip/translate?q=keyWord&from=auto&to=TO&appid=APPID&salt=SALT&sign=SIGN";
		String appid = "20161109000031560";
		url = url.replace("APPID", appid);
		url = url.replace("TO", URLEncoder.encode(to, "UTF-8"));
		String salt = String.valueOf(System.currentTimeMillis());
		url = url.replace("SALT", salt);
		String key = "YAnSLIcmGqr5jdLIioPK";
		String yuanwen = appid + query +  salt + key;
		String sign = DigestUtils.md5Hex(yuanwen);
		url = url.replace("SIGN", sign);
		url = url.replace("keyWord", URLEncoder.encode(query, "UTF-8"));
		JSONObject jsonObject = WeiXinUtil.doGetStr(url);
		StringBuffer sb = new StringBuffer();
		List<Map<String, String>> maps = (List<Map<String, String>>) jsonObject.get("trans_result");
		for(Map<String, String> map : maps){
			sb.append("原文：" + map.get("src")).append("\r\n");
			sb.append(yuyan.get(jsonObject.get("to")) + "：" + map.get("dst"));
		}
		
		
		return sb.toString();
		
	}
	//生成临时二维码
	public static void createQRCode(){
		QRCode qrcode = new QRCode();
		qrcode.setAction_name("QR_LIMIT_SCENE");
		qrcode.setExpire_seconds(604800);
		Action_info action_info = new Action_info();
		Scene scene = new Scene();
		scene.setScene_id(123);
		action_info.setScene(scene);
		String outStr = JSONObject.fromObject(qrcode).toString();
		AccessToken token = getAccessToken1();
		String url = CREATE_QRCODE_URL.replace("TOKEN", token.getToken());
		
		JSONObject jsonObject = doPostStr(url, outStr);
		System.out.println(jsonObject);
		
	}
	
	public static String createYJQRCode(){
		Map qrcode = new HashMap();
		qrcode.put("action_name", "QR_LIMIT_STR_SCENE");
		Map action_info = new HashMap();
		Map scene = new HashMap();
		scene.put("scene_str", "123");
		action_info.put("scene", scene);
		qrcode.put("action_info", action_info);
		String qrcodeStr = JSONObject.fromObject(qrcode).toString();
		AccessToken token = getAccessToken1();
		String url = CREATE_QRCODE_URL.replace("TOKEN", token.getToken());
		JSONObject jsonObject = doPostStr(url, qrcodeStr);
		if(jsonObject.get("errcode") == null){
			return jsonObject.get("ticket").toString();
		}else{
			return null;
		}
		
	
		
	}
	
	public static String uploadImg(String filePath, String accessToken,String type) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			throw new IOException("文件不存在");
		}
		String url1 = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE";
		String url = url1.replace("ACCESS_TOKEN", URLEncoder.encode(accessToken, "UTF-8"));
		url = url.replace("TYPE", URLEncoder.encode(type, "UTF-8"));
		System.out.println(url);
		URL urlObj = new URL(url);
		//连接
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

		con.setRequestMethod("POST"); 
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); 

		//设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");

		//设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

		StringBuilder sb = new StringBuilder();
		sb.append("--");
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"media\";filename=\"" + file.getName() + "\";filelength=\"" + file.length() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");
		
		System.out.println(sb.toString());
		byte[] head = sb.toString().getBytes("utf-8");
		
		//获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		//输出表头
		out.write(head);

		//文件正文部分
		//把文件已流文件的方式 推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();

		//结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");//定义最后数据分隔线

		out.write(foot);

		out.flush();
		out.close();

		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		String result = null;
		try {
			//定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}

		JSONObject jsonObj = JSONObject.fromObject(result);
		System.out.println(jsonObj);
		String typeName = "media_id";
		
		String mediaId = jsonObj.getString(typeName);
		return mediaId;
	}
	
	public static String uploadFile(MultipartFile file, HttpServletRequest req){
		String path = req.getSession().getServletContext().getRealPath("upload");
		String name = file.getOriginalFilename();
		System.out.println(file.getName());
		String suffix = name.substring(name.indexOf("."), name.length());
		String fileName = new Date().getTime() + suffix;
		System.out.println(path + "\\" + fileName);
		File targetFile = new File(path, fileName);
		if(!targetFile.exists()){
			targetFile.mkdirs();
		}
		try {
			file.transferTo(targetFile);
			
			return path + "\\" + fileName;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String uploadNews(String token, NewsMessage newsMessage) throws Exception{
		String url = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";
		url = url.replace("ACCESS_TOKEN", URLEncoder.encode(token, "UTF-8"));
		String outStr = JSONObject.fromObject(newsMessage).toString();
		System.out.println(outStr);
		JSONObject jsonObject = doPostStr(url, outStr);
		System.out.println(jsonObject);
		return jsonObject.get("media_id").toString();
	}
	public static String uploadImg(String token, Map map) throws Exception{
		String url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN";
		url = url.replace("ACCESS_TOKEN", URLEncoder.encode(token, "UTF-8"));
		String outStr = JSONObject.fromObject(map).toString();
		System.out.println(outStr);
		JSONObject jsonObject = doPostStr(url, outStr);
		System.out.println(jsonObject);
		return jsonObject.get("media_id").toString();
	}
	public static String uploadVideo(String token, Map video) throws Exception{
		String url = "https://file.api.weixin.qq.com/cgi-bin/media/uploadvideo?access_token=ACCESS_TOKEN";
		url = url.replace("ACCESS_TOKEN", URLEncoder.encode(token, "UTF-8"));
		String outStr = JSONObject.fromObject(video).toString();
		System.out.println(outStr);
		JSONObject jsonObject = doPostStr(url, outStr);
		System.out.println(jsonObject);
		return jsonObject.get("media_id").toString();
	}
	
	public static String sendMessage(String token, Map map) throws Exception{
		String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
		url = url.replace("ACCESS_TOKEN", URLEncoder.encode(token, "UTF-8"));
		String outStr = JSONObject.fromObject(map).toString();
		System.out.println(outStr);
		JSONObject jsonObject = doPostStr(url, outStr);
		System.out.println(jsonObject);
		return jsonObject.get("errcode").toString();
	}
	
	public static String group(String token, String json) throws Exception{
		String url = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";
		url = url.replaceFirst("ACCESS_TOKEN", URLEncoder.encode(token, "UTF-8"));
		JSONObject jsonObject = doPostStr(url, json);
		return jsonObject.toString();
	}
	
	public static String mvUserToGroup(String token, String json) throws Exception{
		String url = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";
		url = url.replaceFirst("ACCESS_TOKEN", URLEncoder.encode(token, "UTF-8"));
		JSONObject jsonObject = doPostStr(url, json);
		return jsonObject.toString();
	}
	

}
