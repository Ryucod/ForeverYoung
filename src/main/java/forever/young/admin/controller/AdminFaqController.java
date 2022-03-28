package forever.young.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import forever.young.admin.service.AdminService;
import forever.young.admin.vo.AdminFaqVO;

@Controller
public class AdminFaqController {
	@Autowired
	private AdminService adminService;
	
	

	// ������ FAQ ����������
	@RequestMapping("admin_faq.mdo")
	public String adminFaq(Model model, AdminFaqVO faq) {
		List<AdminFaqVO> faqList = adminService.getFaqList(faq);
		if (faqList.size() > 0) {
			model.addAttribute("faqList", faqList);
		}
	
		return "dashboard/faq/admin_faq";
	}

	// FAQ ����
	@RequestMapping("deleteFaq.mdo")
	public String deleteNotice(AdminFaqVO faq) {
		System.out.println("FAQ ���� �޼��� ����");
		int success = 0;
		success = adminService.deleteFaq(faq);
		if (success != 0) {
			return "redirect:admin_faq.mdo";
		} else {
			return "redirect:admin_faq.mdo";
		}
	}

	// FAQ �Է� ȭ�� �̵�
	@RequestMapping("insertFaqPage.mdo")
	public String insertFaqPage(Model model, AdminFaqVO faq) {

		return "dashboard/faq/insertFaq";
	}

	// FAQ ���(Insert)
	@RequestMapping(value="insertFaq.mdo")
	public String insertFaq(AdminFaqVO faq) {
		adminService.insertFaq(faq);
		
		return "redirect:admin_faq.mdo";
	}

	// FAQ ���� ȭ�� �̵�
	@RequestMapping("updateFaqPage.mdo")
	public String updateFaqPage(Model model, AdminFaqVO faq) {
		model.addAttribute("getFaq", adminService.getFaq(faq));
		return "dashboard/faq/admin_faqWrite";
	}

	// FAQ update
	@RequestMapping("updateFaq.mdo")
	public String updateFaq(Model model, AdminFaqVO faq) {
		adminService.updateFaq(faq);

		return "redirect:admin_faq.mdo";
	}

}
