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
			TextMessage  textMessage  =  new  TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new  Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
					//�ı���Ϣ
			textMessage.setContent("欢迎来到梦想义诊微信公众号，在这里可以看到义诊的活动，可以报名参加，查看病症信息");
			respMessage  =  MessageUtil.textMessageToXml(textMessage);
			
			
		}  catch  (Exception  e) {
			e.printStackTrace();
		}
		System.out.println(respMessage);
			return  respMessage;
		}
	}
