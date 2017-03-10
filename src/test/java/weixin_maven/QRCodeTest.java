package weixin_maven;

import com.domain.po.AccessToken;
import com.util.WeiXinUtil;

public class QRCodeTest {

	public static void main(String[] args) throws Exception {
		System.out.println(WeiXinUtil.createYJQRCode());
	}

}
