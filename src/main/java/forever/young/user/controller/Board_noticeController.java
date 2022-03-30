package forever.young.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import forever.young.user.service.Board_noticeService;
import forever.young.user.vo.Board_noticeVO;

@Controller
public class Board_noticeController {
   @Autowired
   private Board_noticeService board_noticeService;
   
   /* �Խñ� ��� ��������(����¡ �۾� �ʿ�) */
   @RequestMapping(value="notice.do")
   public String noticeList(Board_noticeVO vo, Model model) throws Exception {
      System.out.println("������ �ҷ�����");
      List<Board_noticeVO> getBoardList = board_noticeService.getBoardList(vo);
      model.addAttribute("board", getBoardList);
      System.out.println("�Խñ� �������� ����");
      return "customercenter/notice";
   }
   
   /* �Խ��� �󼼳��� ��������*/
   @RequestMapping("notice_detail.do")
   public String noticeDetail(HttpServletRequest request, Board_noticeVO vo, Model model) throws Exception {
      //������ �Խñ��� ��ȣ 
      String num = request.getParameter("notice_serial");
      
      //��ȸ�� 1������Ű�� �󼼳��� ���������
      board_noticeService.updateHit(Integer.parseInt(num));
      
      model.addAttribute("board", board_noticeService.getBoard(vo));
      return "customercenter/noticeDetail";
   }
   
   
   

}