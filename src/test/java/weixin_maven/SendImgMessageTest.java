package weixin_maven;

import java.util.HashMap;
import java.util.Map;

import com.domain.po.AccessToken;
import com.util.WeiXinUtil;

public class SendImgMessageTest {

	public static void main(String[] args) {
		AccessToken token = WeiXinUtil.getAccessToken1();
		String media_id = null;
		try {
			media_id = WeiXinUtil.uploadImg("D:/guayigua.png", token.getToken(), "image");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		System.out.println(media_id);
		Map textMessage = new HashMap();
		Map filter = new HashMap();
		filter.put("is_to_all", true);
		textMessage.put("filter", filter);
		Map image = new HashMap();
		image.put("media_id", media_id);

		textMessage.put("image", image);
		textMessage.put("msgtype", "image");
		try {
			String errcode = WeiXinUtil.sendMessage(token.getToken(), textMessage);
			System.out.println(errcode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
