package forever.young.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import forever.young.admin.service.AdminService;
import forever.young.admin.vo.AdminOrderVO;

@Controller
public class AdminOrderController {
	@Autowired
	private AdminService service;
	
	//������ �ֹ����� �����Ϸ� ����Ʈ
	@RequestMapping("admin_orderWaitList.mdo")
	public String orderWaitList(Model model)throws Exception{
		model.addAttribute("orderWaitList", service.getOrderWaitList());
		
		return "order/admin_orderWaitList";
	}
	
	
	//������ �ֹ����� ����Ʈ(�ֹ� ���)
	@RequestMapping("admin_cancelList.mdo")
	public String orderCancleList(Model model)throws Exception{
		model.addAttribute("orderCacelList", service.getOrderCancleList());
		
		return "order/admin_orderCancel";
	}
}
