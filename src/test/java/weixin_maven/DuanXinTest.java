package weixin_maven;

import com.util.MessageUtil;

public class DuanXinTest {

	public static void main(String[] args) {
		int result = MessageUtil.sendDuanXin((int)((Math.random() * 9 + 1) * 100000), "15737347187");
		if(result == 1){
			System.out.println("发送成功");
		}else{
			System.out.println("发送失败");
		}
	}

}
