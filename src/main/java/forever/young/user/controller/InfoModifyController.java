package forever.young.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import forever.young.user.service.InfoModifyService;
import forever.young.user.service.UserService;
import forever.young.user.vo.UserVO;

@Controller
@SessionAttributes("user")
public class InfoModifyController {
	@Autowired
	private InfoModifyService infoModifyService;
	@Autowired
	private UserService userService;
	
	//ȸ������ ù������
	@RequestMapping(value="modify1.do", method=RequestMethod.GET)
	public String modify1PageGET(UserVO userVo) {
				
		return "modify/modify1";
	}
	//ȸ������ ����������
	@RequestMapping(value="modify2.do", method=RequestMethod.POST)
	public String modify2PagePOST(UserVO userVo, Model model) {
		UserVO userVO=infoModifyService.getUserData(userVo);
		
		userVO.setUser_year(userVO.getUser_birth().substring(0, 4));
		userVO.setUser_month(userVO.getUser_birth().substring(4, 6));
		userVO.setUser_day(userVO.getUser_birth().substring(6));
		
		if(userVO==null || !BCrypt.checkpw(userVo.getUser_password(), userVO.getUser_password())) {
			System.out.println("Ȯ�� ����");
			return "modify/modify1";
		}else {
			//model.addAttribute("userData", userVO);
			System.out.println("Ȯ�� ����");
			System.out.println(userVO);
			return "modify/modify2";
		}
	}
	//ȸ������
	@RequestMapping("updateProc.do")
	public String updateProc(UserVO userVo, Model model) {
		//System.out.println("ȸ��������Ʈ ����");
		//System.out.println(userVo.toString());
		userVo.setUser_password(BCrypt.hashpw(userVo.getUser_new_pw(), BCrypt.gensalt()));
		infoModifyService.updateUser(userVo);
		//System.out.println("ȸ������ ���� ����");
		return "redirect:MyPageMain.do";
	}
	
	//ȸ��Ż�� ������
	@RequestMapping(value="preUserDelete.do", method=RequestMethod.GET)
	public String userDeletePageGET(UserVO userVo) {
					
		return "modify/preUserDelete";
	}
	private UserVO userVO;
	//ȸ��Ż�� ����������
		@RequestMapping(value="deleteProc.do", method=RequestMethod.POST)
		public String userDeletePagePOST(UserVO userVo, Model model) {
			userVO = infoModifyService.getUserData(userVo);
					
			if(userVO==null || !BCrypt.checkpw(userVo.getUser_password(), userVO.getUser_password())) {
				System.out.println("Ȯ�� ����");
				return "modify/preUserDelete";
			}else {
				//model.addAttribute("userData", userVO);
				System.out.println("Ȯ�� ����");
				System.out.println(userVO.toString());
				return "modify/userDelete";
			}
		}
	//ȸ��Ż��
	@RequestMapping("userDelete.do")
	public String deleteUser(UserVO userVo, SessionStatus sessionStatus, Model model) {
		System.out.println(userVO.toString());
		infoModifyService.deleteUser(userVO);
		System.out.println(userVO.toString());
		userService.logout(sessionStatus);
		System.out.println("ȸ��Ż�� ����");
		return "main/main";
	}
}
