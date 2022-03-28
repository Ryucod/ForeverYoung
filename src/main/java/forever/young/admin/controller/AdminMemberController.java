package forever.young.admin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import forever.young.admin.service.AdminService;
import forever.young.admin.vo.AdminUserVO;
import forever.young.admin.vo.AdminVO;

@Controller
@SessionAttributes({ "user_id", "user_name" })
public class AdminMemberController {
	@Autowired
	private AdminService adminService;

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
}
