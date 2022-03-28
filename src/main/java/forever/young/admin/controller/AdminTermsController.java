package forever.young.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import forever.young.admin.service.AdminTermsService;
import forever.young.admin.vo.AdminTermsVO;
import forever.young.user.vo.PersonalQnaVO;

@Controller
public class AdminTermsController {

	@Autowired
	private AdminTermsService admintermsservice;

	// ������
	@RequestMapping(value = "termslist.mdo")
	public String gettermslist(Model model, AdminTermsVO admintermsvo) {
		model.addAttribute("termslist", admintermsservice.getTermslist());
		return "terms/terms";
	}

	// ������ ������ �̵�
	@RequestMapping(value = "insertTerms.mdo")
	public String terms(AdminTermsVO admintermsvo) {
		return "terms/insertTerms";
	}

	// ������ 
	@RequestMapping(value = "insertTermsProc.mdo", method = RequestMethod.POST)
	public String insertTerms(AdminTermsVO admintermsvo) {
		int success = 0;
		success = admintermsservice.insertTerms(admintermsvo);
		if (success != 0) {
			return "redirect:termslist.mdo";
		} else {
			return "redirect:insertTerms.mdo";
		}
	}

	// ������� ������ �̵�
	@RequestMapping(value = "updateTerms.mdo")
	public String updateterms(Model model, AdminTermsVO admintermsvo) {
		model.addAttribute("terms", admintermsservice.getTerms(admintermsvo));
		return "terms/updateTerms";
	}
		
	// �������
	@RequestMapping(value = "updateTermsProc.mdo", method = RequestMethod.POST)
	public String updateTerms(AdminTermsVO admintermsvo) {
		int success = 0;
		success = admintermsservice.updateTerms(admintermsvo);
		if (success != 0) {
			return "redirect:termslist.mdo";
		} else {
			return "redirect:updateTerms.mdo";
		}
	}

	// �������
	@RequestMapping(value = "deleteTermsProc.mdo")
	public String deleteTerms(AdminTermsVO admintermsvo, Model model) {
		model.addAttribute("terms", admintermsservice.getTerms(admintermsvo));
				
		int success = 0;
		success = admintermsservice.deleteTerms(admintermsvo);
		if (success != 0) {
			return "redirect:termslist.mdo";
		} else {
			return "redirect:updateTerms.mdo";
		}
	}

}
