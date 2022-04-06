package forever.young.admin.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import forever.young.admin.service.AdminService;
import forever.young.admin.vo.AdminUserVO;
import forever.young.admin.vo.AdminVO;
import forever.young.email.Email;

@Controller
@SessionAttributes({ "user_id", "user_name" })
public class AdminMemberController {
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private Email email;

	// ������ �α���������
//	@RequestMapping("member.mdo")
//	public String gomember() {
//		return "dashboard/member";
//	}

	// ������� ����Ʈ
	@RequestMapping("admin_userList.mdo")
	public String adminuserList(AdminUserVO adminUser, HttpSession session, Model model) {
		model.addAttribute("userList", adminService.userList(adminUser));
		List<AdminUserVO> userList = adminService.userList(adminUser);
		System.out.println(userList);
		return "dashboard/member";
	}

	// �����ڸ�� ����Ʈ
	@RequestMapping("admin_List.mdo")
	public String adminList(AdminVO admin, HttpSession session, Model model) {
		model.addAttribute("adminList", adminService.adminList(admin));
		List<AdminVO> adminList = adminService.adminList(admin);
		System.out.println(adminList);
		return "dashboard/admin";
	}

	// ȸ�� ����
	@RequestMapping("deleteUser.mdo")
	public String deleteUser(AdminUserVO user) {
		int success = 0;
		success = adminService.deleteUser(user);
		if (success != 0) {
			return "redirect:admin_userList.mdo";
		} else {
			return "redirect:admin_userList.mdo";
		}
	}

	// ������ ����
	@RequestMapping("deleteAdmin.mdo")
	public String deleteAdmin(AdminVO admin) {
		int success = 0;
		success = adminService.deleteAdmin(admin);
		if (success != 0) {
			return "redirect:admin_adminList.mdo";
		} else {
			return "redirect:admin_adminList.mdo";
		}
	}
	//ȸ�� ����
	@RequestMapping("updateUser.mdo")
	@ResponseBody
	public int updateUser(AdminUserVO user, HttpServletRequest request, Model model) {
		String user_id = request.getParameter("user_id");
		String user_status = request.getParameter("user_status");
		int status = Integer.parseInt(user_status);
		
		String id = user.getUser_id();
		
		String getUserEmail = adminService.getMail(user);
		int userStatus = adminService.getUserStatus(user);
		
				
		if(getUserEmail !=null) {
			if(userStatus == 0) {
				
				email.setContent("�ȳ��ϼ���. ���������Դϴ�. ���Բ����� 6���� ���� ���������� ���Ͽ� ���̵� �޸����� ��ȯ �� �����Դϴ�. �����մϴ�.");
				email.setReceiver(getUserEmail);
				email.setSubject(getUserEmail+"���� ������ �޸� �������� ��ȯ �����Դϴ�.");
				try {
					MimeMessage msg=mailSender.createMimeMessage();
					MimeMessageHelper messageHelper=new MimeMessageHelper(msg, true, "UTF-8");
					
					messageHelper.setSubject(email.getSubject());
					messageHelper.setText(email.getContent());
					messageHelper.setTo(email.getReceiver());
					messageHelper.setFrom("kkmm0298@gmail.com");
					msg.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(email.getReceiver()));
					mailSender.send(msg);
				}catch(MessagingException e) {
					e.printStackTrace();
				}
			}


		}
		int success = 0;
		success = adminService.updateUser(user);
		
		return success;
	}

}
