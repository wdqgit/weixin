package com.util;

import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.domain.resp.Article;
import com.domain.resp.Image;
import com.domain.resp.ImageMessage;
import com.domain.resp.MusicMessage;
import com.domain.resp.NewsMessage;
import com.domain.resp.TextMessage;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import  com.thoughtworks.xstream.XStream;
import  com.thoughtworks.xstream.core.util.QuickWriter;
import  com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import  com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import  com.thoughtworks.xstream.io.xml.XppDriver;

import net.sf.json.JSONObject;

public class MessageUtil {
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";
	/**
	 * ������Ϣ���ͣ�����
	 */
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
	/**
	 * ������Ϣ���ͣ�ͼ��
	 */
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";
	/**
	 * ������Ϣ���ͣ��ı�
	 */
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";
	/**
	 * ������Ϣ���ͣ�ͼƬ
	 */
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
	/**
	 * ������Ϣ���ͣ�����
	 */
	public static final String REQ_MESSAGE_TYPE_LINK = "link";
	/**
	 * ������Ϣ���ͣ�����λ��
	 */
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
	/**
	 * ������Ϣ���ͣ���Ƶ
	 */
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
	/**
	 * ������Ϣ���ͣ�����
	 */
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";
	/**
	 * �¼����ͣ�subscribe(����)
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
	/**
	 * �¼����ͣ�unsubscribe(ȡ������)
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
	/**
	 * �¼����ͣ�CLICK(�Զ���˵�����¼�)
	 */
	public static final String EVENT_TYPE_CLICK = "CLICK";
	public static final String EVENT_TYPE_VIEW = "VIEW";
	public static final String EVENT_TYPE_SCANCODE_PUSH = "scancode_push";
	public static final String EVENT_TYPE_LOCATION = "location";

	/**
	 * ����΢�ŷ���������XML��
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
		// ����������洢�� HashMap��
		Map<String, String> map = new HashMap<String, String>();
		// �� request��ȡ��������
		InputStream inputStream = request.getInputStream();
		// ��ȡ������
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// �õ� xml��Ԫ��
		Element root = document.getRootElement();
		// �õ���Ԫ�ص������ӽڵ�
		List<Element> elementList = root.elements();
		// ���������ӽڵ�
		for (Element e : elementList){
			List<Element> elements = e.elements();
			if(!elements.isEmpty()){
				for(Element e1 : elements){
					map.put(e1.getName(), e1.getText());
				}
			}else{
				map.put(e.getName(), e.getText());
				
			}
		}
		// �ͷ���Դ
		inputStream.close();
		inputStream = null;
		return map;
	}

	/**
	 * �ı���Ϣ����ת���� xml
	 *
	 * @param textMessage�ı���Ϣ����
	 * @return xml
	 */
	public static String textMessageToXml(TextMessage textMessage) {
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}

	/**
	 * ������Ϣ����ת���� xml
	 *
	 * @param musicMessage������Ϣ����
	 * @return xml
	 */
	public static String musicMessageToXml(MusicMessage musicMessage) {

		xstream.alias("xml", musicMessage.getClass());
		return xstream.toXML(musicMessage);
	}

	/**
	 * ͼ����Ϣ����ת���� xml
	 *
	 * @param newsMessageͼ����Ϣ����
	 * @return xml
	 */
	public static String newsMessageToXml(NewsMessage newsMessage) {
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(newsMessage);
	}
	public static String imageMessageToXml(ImageMessage imageMessage) {
		xstream.alias("xml", imageMessage.getClass());
		xstream.alias("Image", new Image().getClass());
		return xstream.toXML(imageMessage);
	}

	/**
	 * ��չ xstream��ʹ��֧�� CDATA��
	 *
	 * @date 2013-05-19
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// ������ xml�ڵ��ת�������� CDATA���
				boolean cdata = true;

				@SuppressWarnings("unchecked")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					
					if (cdata) {
						System.out.println(text);
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});
	
	public static int sendDuanXin(int code, String phone){
		String url="http://gw.api.taobao.com/router/rest";  
		//成为开发者，创建应用后系统自动生成  
		String appkey="23535101";  
		String secret="88999002e3a519e7332abdd90a145f4a";  
		//短信模板的内容  
		String json="{\"code\":\"" + code + "\"}";  
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);  
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();  
		req.setExtend("123456");  
		req.setSmsType("normal");  
		req.setSmsFreeSignName("用于微信号推广");  
		req.setSmsParamString(json);  
		req.setRecNum(phone);  
		req.setSmsTemplateCode("SMS_26200177");  
		try {  
		    AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);  
		    System.out.println(rsp.getBody());  
		    JSONObject jsonObject = JSONObject.fromObject(rsp.getBody());
		    if(jsonObject.get("error_response") == null){
		    	return 1;
		    }else{
		    	return -1;
		    }
		} catch (Exception e) {  
		    // TODO: handle exception  
		    return -1;  
		}  
	}
	
	

}
