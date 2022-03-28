package forever.young.admin.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import forever.young.admin.service.AdminService;
import forever.young.admin.vo.NoticeVO;

@Controller
public class AdminNoticeController {
	@Autowired
	private AdminService adminService;
	@InitBinder
    protected void initBinder(WebDataBinder binder){
        DateFormat  dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,true));
    }

	// ������ �������� ����������
	@RequestMapping("admin_notice.mdo")
	public String adminNotice(NoticeVO notice, HttpSession session, Model model) {
		List<NoticeVO> NoticeList = adminService.getNoticeList(notice);
		if (NoticeList.size() > 0) {
			model.addAttribute("noticeList", NoticeList);
		}
		System.out.println(NoticeList);
		return "dashboard/notice/admin_notice";
	}

	// �������� ����
	@RequestMapping("deleteNotice.mdo")
	public String deleteNotice(NoticeVO notice) {
		System.out.println("�������� ���� �޼��� ����");
		int success = 0;
		success = adminService.deleteNotice(notice);
		if (success != 0) {
			return "redirect:admin_notice.mdo";
		} else {
			return "redirect:admin_notice.mdo";
		}
	}

	// �������� �� ȭ�� �̵�
	@RequestMapping("updateNoticePage.mdo")
	public String updateNoticePage(Model model, NoticeVO notice) {
		model.addAttribute("getNotice", adminService.getNotice(notice));

		return "dashboard/notice/admin_noticeWrite";
	}
	
	// �������� update
	@RequestMapping("updateNotice.mdo")
	public String updateNotice(Model model, NoticeVO notice) {
		adminService.updateNotice(notice);
		
		return "redirect:admin_notice.mdo";
	}

	// �������� �Է� ȭ�� �̵�
	@RequestMapping("insertNoticePage.mdo")
	public String insertNoticePage(Model model, NoticeVO notice) {

		return "dashboard/notice/insertNotice";
	}

	// �������� ���(Insert)
	@RequestMapping("insertNotice.mdo")
	public String insertNotice(NoticeVO notice) {
		adminService.insertNotice(notice);
		return "redirect:admin_notice.mdo";
	}

}
