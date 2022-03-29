package forever.young.user.controller;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import forever.young.sms.MessageService;
import forever.young.user.service.UserService;
import forever.young.user.vo.UserDetailVO;
import forever.young.user.vo.UserVO;

@Controller

public class UserJoinController {
	
	private final UserService userService;
	
	@Inject
	public UserJoinController(UserService userService) {
		this.userService=userService;
	}
	
	//ȸ������ ������
	@RequestMapping("join.do")
	public String join() throws Exception{
		return "join_login/join";
	}
	//ȸ������ ó��
	@RequestMapping(value="joinProc.do", method=RequestMethod.POST)
	public String registerPOST(UserVO userVo,UserDetailVO details, RedirectAttributes redirectAttributes)throws Exception{
		System.out.println("ȸ�����Կ� ����");
		String hashedPw=BCrypt.hashpw(userVo.getUser_password(), BCrypt.gensalt());
		userVo.setUser_password(hashedPw);
		userService.register(userVo);
		userService.insertUserDetail(details);
		redirectAttributes.addFlashAttribute("msg", "REGISTERED");
		
		
		return "join_login/login";
	}
	//���̵�üũ
	@RequestMapping(value="idCheck.do", produces="html/text; charset=utf-8")
	@ResponseBody
	public String idCheck(UserVO userVo) {
		boolean used=userService.idCheck(userVo);
		if(used)
			return "{\"message\":\"��밡���� ���̵� �Դϴ�.\",\"usedId\":\"����\"}";
		else
			return "{\"message\":\"��� �Ұ����� ���̵� �Դϴ�.\",\"usedId\":\"�Ұ���\"}";
	}
	//�̸��� üũ
	@RequestMapping(value="emailCheck.do", produces="html/text; charset=utf-8")
	@ResponseBody
	public String emailCheck(UserVO userVo) {
		boolean used=userService.emailCheck(userVo);
		if(used)
			return "{\"message\":\"��밡���� �̸��� �Դϴ�.\",\"usedEmail\":\"����\"}";
		else
			return "{\"message\":\"��� �Ұ����� �̸��� �Դϴ�.\",\"usedEmail\":\"�Ұ���\"}";
	}
	// ȸ������ -��������
	   @RequestMapping(value = "/smsCheck.do", produces = "html/text; charset=utf-8")
	   @ResponseBody
	   public String smsService(UserVO user) {
	      System.out.println("sms�߼�");
	      MessageService sms = new MessageService();

	      int randomNum = (int) (Math.random() * 10000) + 1;
	      String randomStr;
	      if (randomNum / 1000 == 0)
	         randomStr = "0" + randomNum;
	      else if (randomNum / 100 == 0)
	         randomStr = "00" + randomNum;
	      else if (randomNum / 10 == 0)
	         randomStr = "000" + randomNum;
	      else
	         randomStr = String.valueOf(randomNum);

	      sms.sendMessage(user.getUser_phone(), randomStr);
	      String numberData;
	      numberData = "{\"checkNum\":" + randomStr + "}";
	      return numberData;
	   }
}
