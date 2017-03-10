package weixin_maven;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import com.util.WeiXinUtil;

public class UploadTest {

	public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException{
		WeiXinUtil wxu = new WeiXinUtil();
		System.out.println(wxu.upload("C:\fakepath\1.jpg", wxu.getAccessToken1().getToken(), "image"));
	}
	
}
