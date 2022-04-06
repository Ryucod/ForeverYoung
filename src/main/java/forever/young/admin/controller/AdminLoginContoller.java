package forever.young.admin.controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import forever.young.admin.service.AdminSalesService;
import forever.young.admin.service.AdminService;
import forever.young.admin.vo.AdminSalesVO;
import forever.young.admin.vo.AdminVO;
import forever.young.admin.vo.DateVO;

@Controller
@SessionAttributes("adminId")
public class AdminLoginContoller {
	@Autowired
	private AdminService adminService;
	@Autowired
	private AdminSalesService adminsalesservice;

	// ������ �α���������
	@RequestMapping("admin_login.mdo")
	public String adminlogin() {
		return "admin_login";
	}

	@PostMapping("sendDate.mdo")
	@ResponseBody
	public List<AdminSalesVO> sendDate(@RequestBody DateVO vo, Model model) {

		System.out.println("vo : " + vo.toString());

		List<AdminSalesVO> salesList = adminsalesservice.getDateSales(vo);

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("msg", "SUCCESS");
		Gson gson = new Gson();
		String result = gson.toJson(salesList);

		return salesList;
	}

	// ������ ������������
	@RequestMapping("main_dashboardDate.mdo")
	public String mainSub(@RequestParam("start_date") String StartDate, @RequestParam("end_date") String endDate,
			Model model, AdminSalesVO adminsalesvo) {
		// ���ϸ��� ���ϱ�
		model.addAttribute("getTodaySales", adminsalesservice.getTodaySales(adminsalesvo));

		// ������ ��ǰ�� �Ǹ���Ȳ ���ϱ�
		model.addAttribute("ExcelList", adminsalesservice.getSales(adminsalesvo));

		// �Ⱓ�� ����
		DateVO vo = new DateVO();
		vo.setStart_date(Date.valueOf(StartDate));
		vo.setEnd_date(Date.valueOf(endDate));
		model.addAttribute("salesList", adminsalesservice.getDateSales(vo));

		// 1�� ī�װ��� ����
		for (int i = 0; i < adminsalesservice.getCateSales(adminsalesvo).size(); i++) {
			model.addAttribute("CatePrice" + i, adminsalesservice.getCateSales(adminsalesvo).get(i).getPRICE());
			model.addAttribute("CateName" + i,
					adminsalesservice.getCateSales(adminsalesvo).get(i).getCATEGORY_FIRST_NAME());
		}

		// ���� ������Ȳ���ϱ�
		model.addAttribute("monthSalesList", adminsalesservice.getMonthSales(adminsalesvo));
		for (int i = 0; i < adminsalesservice.getMonthSales(adminsalesvo).size(); i++) {
			model.addAttribute("monthprice" + i, adminsalesservice.getMonthSales(adminsalesvo).get(i).getPRICE());
			model.addAttribute("monthprice1" + i, adminsalesservice.getMonthSales(adminsalesvo).get(i).getPRICE1());
		}

		// �Ϻ� ������Ȳ���ϱ�
		for (int i = 0; i < adminsalesservice.getDaySales(adminsalesvo).size(); i++) {
			model.addAttribute("day" + i, adminsalesservice.getDaySales(adminsalesvo).get(i).getDATE1());
			model.addAttribute("dayprice" + i, adminsalesservice.getDaySales(adminsalesvo).get(i).getPRICE());
		}

		return "dashboard/main_dashboard";
	}

	// ������ ����������
	@RequestMapping("main_dashboard.mdo")
	public String main(Model model, AdminSalesVO adminsalesvo) {
		// ���ϸ��� ���ϱ�
		model.addAttribute("getTodaySales", adminsalesservice.getTodaySales(adminsalesvo));

		// ������ ��ǰ�� �Ǹ���Ȳ ���ϱ�
		model.addAttribute("ExcelList", adminsalesservice.getSales(adminsalesvo));

		// 1�� ī�װ��� ����
		for (int i = 0; i < adminsalesservice.getCateSales(adminsalesvo).size(); i++) {
			model.addAttribute("CatePrice" + i, adminsalesservice.getCateSales(adminsalesvo).get(i).getPRICE());
			model.addAttribute("CateName" + i,
					adminsalesservice.getCateSales(adminsalesvo).get(i).getCATEGORY_FIRST_NAME());
		}

		// ���� ������Ȳ���ϱ�
		model.addAttribute("monthSalesList", adminsalesservice.getMonthSales(adminsalesvo));
		for (int i = 0; i < adminsalesservice.getMonthSales(adminsalesvo).size(); i++) {
			model.addAttribute("monthprice" + i, adminsalesservice.getMonthSales(adminsalesvo).get(i).getPRICE());
			model.addAttribute("monthprice1" + i, adminsalesservice.getMonthSales(adminsalesvo).get(i).getPRICE1());
		}

		// �Ϻ� ������Ȳ���ϱ�
		for (int i = 0; i < adminsalesservice.getDaySales(adminsalesvo).size(); i++) {
			model.addAttribute("day" + i, adminsalesservice.getDaySales(adminsalesvo).get(i).getDATE1());
			model.addAttribute("dayprice" + i, adminsalesservice.getDaySales(adminsalesvo).get(i).getPRICE());
		}

		return "dashboard/main_dashboard";
	}

	// �����ڸ���������
//	@RequestMapping("admin_index.mdo")
//	public String sales(AdminSalesVO sales, AdminSales2VO sales2, Model model, AdminBestGoodsVO best, GoodsQnaVO gqna,
//			AdminOrderVO order) {
//		// �Ϻ�
//		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
//		Calendar day = Calendar.getInstance();
//		day.add(Calendar.DATE, -30);
//		String startDate = date.format(day.getTime());
//		sales.setStartdate(startDate);
//		sales.setEnddate(date.format(new Date()));
//		model.addAttribute("chartList", JSONArray.fromObject(adminService.getDate(sales)));
//		// ����
//		SimpleDateFormat date2 = new SimpleDateFormat("yyyy-MM");
//		Calendar month = Calendar.getInstance();
//		month.add(Calendar.DATE, -100);
//		String startDate2 = date2.format(month.getTime());
//		sales2.setStartdate2(startDate2);
//		sales2.setEnddate2(date2.format(new Date()));
//		model.addAttribute("chartMonth", JSONArray.fromObject(adminService.getMonth(sales2)));
//		model.addAttribute("bestList", adminService.bestList(best));
//		model.addAttribute("gqnaCount", adminService.gqnaCount(gqna));
//		model.addAttribute("orderCount", adminService.orderCount(order));
//		return "admin_index";
//	}

	// ������ �α���ó��
	@RequestMapping("loginProc.mdo")
	public String adminJoin(AdminVO admin, Model model, AdminSalesVO adminsalesvo, HttpSession httpSession) {
		AdminVO adminInfo = adminService.getAdmin(admin);
		System.out.println(adminInfo);
		String inputPw = admin.getAdmin_pw();
		String dbPw = adminInfo.getAdmin_pw();

		if (dbPw.equals(inputPw)) {
			model.addAttribute("adminId", admin.getAdmin_id());

			// ���ϸ��� ���ϱ�
			model.addAttribute("getTodaySales", adminsalesservice.getTodaySales(adminsalesvo));

			// ������ ��ǰ�� �Ǹ���Ȳ ���ϱ�
			model.addAttribute("ExcelList", adminsalesservice.getSales(adminsalesvo));

			// 1�� ī�װ��� ����
			for (int i = 0; i < adminsalesservice.getCateSales(adminsalesvo).size(); i++) {
				model.addAttribute("CatePrice" + i, adminsalesservice.getCateSales(adminsalesvo).get(i).getPRICE());
				model.addAttribute("CateName" + i,
						adminsalesservice.getCateSales(adminsalesvo).get(i).getCATEGORY_FIRST_NAME());
			}

			// ���� ������Ȳ���ϱ�
			model.addAttribute("monthSalesList", adminsalesservice.getMonthSales(adminsalesvo));
			for (int i = 0; i < adminsalesservice.getMonthSales(adminsalesvo).size(); i++) {
				model.addAttribute("monthprice" + i, adminsalesservice.getMonthSales(adminsalesvo).get(i).getPRICE());
				model.addAttribute("monthprice1" + i, adminsalesservice.getMonthSales(adminsalesvo).get(i).getPRICE1());
			}

			// �Ϻ� ������Ȳ���ϱ�
			for (int i = 0; i < adminsalesservice.getDaySales(adminsalesvo).size(); i++) {
				model.addAttribute("day" + i, adminsalesservice.getDaySales(adminsalesvo).get(i).getDATE1());
				model.addAttribute("dayprice" + i, adminsalesservice.getDaySales(adminsalesvo).get(i).getPRICE());
			}

			return "dashboard/main_dashboard";
		} else {
			return "admin_login";
		}
	}

	// ������ �α׾ƿ�ó��
	@RequestMapping("logoutProc.mdo")
	public String logout(SessionStatus sessionStatus) {
		adminService.logout(sessionStatus);
		return "redirect:admin_login.mdo";
	}

}
