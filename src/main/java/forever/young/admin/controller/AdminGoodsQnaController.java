package forever.young.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import forever.young.admin.service.AdminService;
import forever.young.admin.vo.GoodsQnaVO;

@Controller
public class AdminGoodsQnaController {
	@Autowired
	private AdminService service;
	
	//������ ��ǰ ���� ����Ʈ (�亯���)
	@RequestMapping("gqnaList.mdo")
	public String gqnaList(GoodsQnaVO gqna, Model model)throws Exception{
		model.addAttribute("gqnaList", service.gqnaList(gqna));
		
		return "qna/goodsQna";
	}
	
	//������ ��ǰ���� ���� (�亯 ���)
	@RequestMapping("deleteGqna.mdo")
	public String deleteGqna(GoodsQnaVO gqna)throws Exception {
		gqna.getCategory_goods_name();
		
		int delete = 0;
		delete = service.deleteGqna(gqna);
		
		if(delete != 0) {
			return "redirect:gqnaList.mdo";
		}else {
			return "redirect:gqnaList.mdo";
		}
	}
	
	//��ǰ���� �亯
	@RequestMapping("toAnswer.mdo")
	public String toAnanswer(GoodsQnaVO gqna, Model model) throws Exception {
		model.addAttribute("qnaone", service.gqnaOne(gqna));
		
		return "qna/answerGoodsQna";
	}
	
	//������ ��ǰ ���� �亯
	@RequestMapping("updateGqna.mdo")
	public String updateGqna(GoodsQnaVO gqna)throws Exception{
		int update = 0;
		update = service.updateGqna(gqna);
		
		if(update != 0) {
			return "redirect:gqnaList.mdo";
		}else {
			return "redirect:toAnswer.mdo";
		}
	}
	
	//������ ��ǰ ���� ����Ʈ (�亯�Ϸ�)
	@RequestMapping("gqnaListSuc.mdo")
	public String gqnaListSuc(GoodsQnaVO gqna, Model model)throws Exception{
		model.addAttribute("gqnaListSuc", service.gqnaListSuc(gqna));
		
		return "qna/sucGoodsQna";
	}
	
	//������ ��ǰ ���� ���� (�亯�Ϸ�)
	@RequestMapping("deleteGqnaSuc.mdo")
	public String deleteGqnaSuc(GoodsQnaVO gqna)throws Exception{
		gqna.getCategory_goods_name();
		
		int delete = 0;
		delete = service.deleteGqna(gqna);
		if(delete != 0) {
			return "redirect:gqnaListSuc.mdo";
		}else {
			return "redirect:gqnaListSuc.mdo";
		}
	}
	
	//������ ��ǰ ���� �亯 Ȯ��
	@RequestMapping("checkAnswer.mdo")
	public String checkAnswer(GoodsQnaVO gqna, Model model)throws Exception{
		model.addAttribute("qnaone", service.gqnaOne(gqna));
		System.out.println("again");
		
		return "qna/checkAnswerQna";
	}
	
	//������ ��ǰ ���� �亯 ����
	@RequestMapping("updateGqnaSuc.mdo")
	public String updateGqnaSuc(GoodsQnaVO gqna)throws Exception{
		int update = 0;
		update = service.updateGqnaSuc(gqna);
		
		if(update != 0) {
			return "redirect:gqnaListSuc.mdo";
		}else {
			return "redirect:checkAnswer.mdo";
		}
	}
}
