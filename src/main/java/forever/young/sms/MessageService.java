package forever.young.sms;

import java.util.HashMap;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class MessageService {
	public void sendMessage(String toNumber, String randomNumber) {
		String apiKey = "NCSRNVHBD2ISTLS8";// = "";
		String apiSecret = "R8HMOYOTINYZYMW7M9GYFIRL2VN92XRA";// = "";
		Message coolsms = new Message(apiKey, apiSecret);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", toNumber);
		params.put("from", "01095665544");
		params.put("type", "SMS");
		params.put("text", "[ForeverYoung] �������� ������ȣ " + randomNumber + " �� �Է��ϼ���.");
		params.put("app_version", "test app 1.2"); // application name and version

		try {
			JSONObject obj = (JSONObject) coolsms.send(params);
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}
	}
	public void sendMessage2(String toNumber) {
		String apiKey = "NCSRNVHBD2ISTLS8";// = "";
		String apiSecret = "R8HMOYOTINYZYMW7M9GYFIRL2VN92XRA";// = "";
		Message coolsms = new Message(apiKey, apiSecret);
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", toNumber);
		params.put("from", "01095665544");
		params.put("type", "SMS");
		params.put("text", "������ 1:1���� �亯�� �ۼ��Ǿ����ϴ�." + "�������� ������������ Ȯ�����ּ���.");
		params.put("app_version", "test app 1.2"); // application name and version
		
		try {
			JSONObject obj = (JSONObject) coolsms.send(params);
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}
	}

}
