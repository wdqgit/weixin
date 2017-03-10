package com.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.domain.resp.Article;
import com.domain.resp.Image;
import com.domain.resp.ImageMessage;
import com.domain.resp.NewsMessage;
import com.domain.resp.TextMessage;
import com.service.CoreService;
import com.util.MessageUtil;
import com.util.WeiXinUtil;

@Service
public class CoreServiceImpl implements CoreService{

	public String processRequest(HttpServletRequest request) {
		String  respMessage  =  null;
		try  {
			//Ĭ�Ϸ��ص��ı���Ϣ����
			String  respContent  =  "�������쳣�����Ժ��ԣ�";
			//  xml�������
			Map<String,  String>  requestMap  = MessageUtil.parseXml(request);
			//���ͷ��ʺţ�open_id��
			String  fromUserName  =  requestMap.get("FromUserName");
			//�����ʺ�
			String  toUserName  =  requestMap.get("ToUserName");
			//��Ϣ����
			String  msgType  =  requestMap.get("MsgType");
			//��Ϣ����
			String content = requestMap.get("Content");
			System.out.println(msgType);
			
			if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)){
				if(content.startsWith("翻译")){
					TextMessage  textMessage  =  new  TextMessage();
					textMessage.setToUserName(fromUserName);
					textMessage.setFromUserName(toUserName);
					textMessage.setCreateTime(new  Date().getTime());
					textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
					textMessage.setFuncFlag(0);
					String[] contents = content.split(" ");
					String query = "";
					for(int i = 1; i < contents.length - 1; i++){
						query += contents[i] + " ";
					}
					String retVal = WeiXinUtil.translate(query.trim(), contents[contents.length - 1].trim());
					textMessage.setContent(retVal);
					respMessage  =  MessageUtil.textMessageToXml(textMessage);
				}else if(content.equals("1")){
					//�ظ��ı���Ϣ
					TextMessage  textMessage  =  new  TextMessage();
					textMessage.setToUserName(fromUserName);
					textMessage.setFromUserName(toUserName);
					textMessage.setCreateTime(new  Date().getTime());
					textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
					textMessage.setFuncFlag(0);
					//�ı���Ϣ
					textMessage.setContent("测试文本");
					respMessage  =  MessageUtil.textMessageToXml(textMessage);
				}else if(content.equals("2")){
					NewsMessage newsMessage = new NewsMessage();
					newsMessage.setToUserName(fromUserName);
					newsMessage.setFromUserName(toUserName);
					newsMessage.setCreateTime(new Date().getTime());
					newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
					newsMessage.setFuncFlag(0);
					Article article = new Article();
					article.setTitle("测试");
					article.setDescription("测试图文消息");
					article.setPicUrl("http://yizhen.tunnel.qydev.com/weixin/image/6.jpg");
					
					article.setUrl("www.baidu.com");
					List<Article> articles = new ArrayList<Article>();
					articles.add(article);
					newsMessage.setArticles(articles);
					newsMessage.setArticleCount(articles.size());
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}else if(content.equals("3")){
					ImageMessage imageMessage = new ImageMessage();
					Image image = new Image();
					image.setMediaId("wYQOcR9JK8UYygmJo7ExGQQiz0j-2PL-99daJAAfa2ZV6zmVCyp5OpaGu8PZ-wT_");
					imageMessage.setToUserName(fromUserName);
					imageMessage.setFromUserName(toUserName);
					imageMessage.setCreateTime(new Date().getTime());
					imageMessage.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_IMAGE);
					imageMessage.setImage(image);
					
					respMessage = MessageUtil.imageMessageToXml(imageMessage);
				}else if(content.equals("4")){
					//百度翻译介绍
					TextMessage  textMessage  =  new  TextMessage();
					textMessage.setToUserName(fromUserName);
					textMessage.setFromUserName(toUserName);
					textMessage.setCreateTime(new  Date().getTime());
					textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
					textMessage.setFuncFlag(0);
					
					textMessage.setContent("百度翻译使用指南\n"
							+ "使用实例：\n"
							+ "翻译 足球 英语\n"
							+"翻译 中国足球 韩语\n\n"
							+"回复？显示主菜单");
					respMessage  =  MessageUtil.textMessageToXml(textMessage);
				}else if(content.equals("?") || content.equals("？")){
				
					respMessage  =  WeiXinUtil.menuText(fromUserName, toUserName);
				}
			
			}else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)){
				String eventType = requestMap.get("Event");
				String eventKey = requestMap.get("EventKey");
				System.out.println(eventType);
				if("MASSSENDJOBFINISH".equals(eventType)){
					String status = requestMap.get("Status");
					System.out.println(status);
					TextMessage  textMessage  =  new  TextMessage();
					textMessage.setToUserName(toUserName);
					textMessage.setFromUserName(fromUserName);
					textMessage.setCreateTime(new  Date().getTime());
					textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
					textMessage.setFuncFlag(0);
					
					textMessage.setContent(status);
					respMessage  =  MessageUtil.textMessageToXml(textMessage);
				}else
				if(eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){
					respMessage = WeiXinUtil.menuText(fromUserName, toUserName);
				}else if(eventType.equals(MessageUtil.EVENT_TYPE_CLICK)){
						if(eventKey.equals("11"))
						respMessage = WeiXinUtil.menuText(fromUserName, toUserName);
				}else if(eventType.equals(MessageUtil.EVENT_TYPE_VIEW)){
					System.out.println(eventKey);
					if(eventKey.equals("http://www.baidu.com")){
						System.out.println(eventKey);
						respMessage = WeiXinUtil.menuText(fromUserName, toUserName);
					}
				}else if(eventType.equals(MessageUtil.EVENT_TYPE_SCANCODE_PUSH)){
					System.out.println(eventKey);
					if(eventKey.equals("32")){
						System.out.println(eventKey);
						respMessage = WeiXinUtil.menuText(fromUserName, toUserName);
					}
				}
			}else if(msgType.equals(MessageUtil.EVENT_TYPE_LOCATION)){
				
			
				
					TextMessage  textMessage  =  new  TextMessage();
					textMessage.setToUserName(fromUserName);
					textMessage.setFromUserName(toUserName);
					textMessage.setCreateTime(new  Date().getTime());
					textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
					textMessage.setFuncFlag(0);
					String label = requestMap.get("Label");
					System.out.println(label);
					textMessage.setContent(label);
					respMessage  =  MessageUtil.textMessageToXml(textMessage);
				
		}
		}  catch  (Exception  e) {
			e.printStackTrace();
		}
		System.out.println(respMessage);
			return  respMessage;
		}
	}
