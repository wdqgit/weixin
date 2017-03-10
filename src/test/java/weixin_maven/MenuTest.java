package weixin_maven;

import com.domain.po.AccessToken;
import com.util.WeiXinUtil;

import net.sf.json.JSONObject;

public class MenuTest {

	public static void main(String[] args) {
		AccessToken token = WeiXinUtil.getAccessToken1();
		String menu = JSONObject.fromObject(WeiXinUtil.initMenu()).toString();
		int result = WeiXinUtil.createMenu(token.getToken(), menu);
		System.out.println(result);
	}

}
