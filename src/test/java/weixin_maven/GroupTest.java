package weixin_maven;

import org.junit.Test;

import com.domain.po.AccessToken;
import com.util.WeiXinUtil;


public class GroupTest {
	@Test
	public void createGroup() throws Exception{
		String json = "{\"group\":{\"name\":\"wang\"}}";/*groupId:101*/
		AccessToken token = WeiXinUtil.getAccessToken1();
		System.out.println(token.getToken());
		System.out.println(WeiXinUtil.group(token.getToken(), json));
	}
	@Test
	public void mvUserToGroup() throws Exception{
		String json = "{\"openid\":\"oY_StwBVSjIGqoz565loZI9UiDxA\",\"to_groupid\":101}";
		System.out.println(WeiXinUtil.mvUserToGroup(WeiXinUtil.getAccessToken1().getToken(), json));
	}
	
}
