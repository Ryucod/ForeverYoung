package forever.young.user.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import forever.young.admin.vo.AdminBannerVO;
import forever.young.user.service.GoodsQnaService;
import forever.young.user.service.PersonalQnaService;
import forever.young.user.vo.Pagination;
import forever.young.user.vo.PersonalQnaVO;
import forever.young.user.vo.UserVO;
import s3.AwsS3;

@Controller
@SessionAttributes("userId")
public class PersonalQnaController {
	private String url = "https://fyawsbucket.s3.ap-northeast-2.amazonaws.com/";

	//DB
	@Autowired
	private AwsS3 awsS3;
	
	@Autowired
	private PersonalQnaService personalqnaService;
	
	
	@RequestMapping("oneqmain.do")
	public String clientCenter1(Model model, @RequestParam(required = false, defaultValue = "1") int page, @RequestParam(required = false, defaultValue = "1") int range, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("userId") == null) {
			System.out.println("�α����������� �̵�");
			return "join_login/login";
		}else {
         
			int listCnt=personalqnaService.getBoardListCnt();
			session=request.getSession();
			String user_id=((String)session.getAttribute("userId"));
			Pagination pagination=new Pagination();
         
			pagination.pageInfo(page, range, listCnt);
         
			model.addAttribute("pagination", pagination);
			model.addAttribute("board", personalqnaService.getBoard_personalList(pagination, user_id));
			System.out.println(pagination.getStartList());
			return "clientCenter/oneqmain2";
		}
	}

	//1:1���� �Խ��� ������
//	@RequestMapping("oneqmain.do")
//	public String getList(HttpServletRequest request, PersonalQnaVO vo, Model model) {
//		HttpSession session=request.getSession();
//		vo.setUser_id((String)session.getAttribute("userId"));
//		List<PersonalQnaVO> boardList=personalqnaService.getPersonalQnaList(vo);
//		
//		if(boardList.size()>0) {
//			model.addAttribute("peersonalqnaboard", boardList);
//		}
//		return "clientCenter/oneqmain";
//	}
	
	//1:1 ���� ���� ������
	@RequestMapping("oneqwrite.do")
	public String oneqwriteGETPage(HttpServletRequest request, UserVO vo, Model model, PersonalQnaVO pvo) {
		HttpSession session=request.getSession();
		
		pvo.setUser_id((String)session.getAttribute("userId"));
		vo.setUser_id((String)session.getAttribute("userId"));
		model.addAttribute("user", personalqnaService.getUser(vo));
				
		return "clientCenter/oneqwrite";
	}
	
	//1:1���� ���� �ۼ�
	@RequestMapping(value="insertPersonalQna.do")
	public String insertPersonalQna(MultipartFile uploadFile, PersonalQnaVO vo) {
		
		try {
			String key = "personalQna/"+uploadFile.getOriginalFilename();
			InputStream is = uploadFile.getInputStream();
			String contentType = uploadFile.getContentType();
			long contentLength = uploadFile.getSize();
			
			awsS3.upload(is, key, contentType, contentLength);
			
			vo.setQna_personal_image1(url + key);
			
			personalqnaService.insertPersonalQna(vo);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return "redirect:oneqmain.do";
		
	}
	//1:1 ���� ���� ������
	@RequestMapping(value="updatePersonalQnaPage.do")
	public String updatePersonalQna(PersonalQnaVO vo, Model model, UserVO userVo) {
		PersonalQnaVO getPersonalQna=personalqnaService.getPersonalQna(vo);
		model.addAttribute("getPersonalQna", getPersonalQna);
		
		return "clientCenter/oneqwrite";
	}
	
	//1:1 ���� ���� ���
	@RequestMapping(value="updatePersonalQna.do")
	public String updatePostPersonalQna(PersonalQnaVO vo, MultipartFile uploadFile) {
		
		if(uploadFile.getSize() != 0) {
			//��ü�ϰ� ���� �̹����� ���� ��� 0�� �ƴϹǷ� s3�� �ִ� �̹����� ����
			PersonalQnaVO qnavo = personalqnaService.getPersonalQna(vo);
			String deleteKey = qnavo.getQna_personal_image1().substring(49);
			awsS3.delete(deleteKey);
			
			//���ο� �̹����� s3�� ���
			try {
				String key = "personalQna/"+uploadFile.getOriginalFilename();
				InputStream is = uploadFile.getInputStream();
				String contentType = uploadFile.getContentType();
				long contentLength = uploadFile.getSize();
				awsS3.upload(is, key, contentType, contentLength);
				
				vo.setQna_personal_image1(url + key);
			}catch(IOException e) {
				e.printStackTrace();
			}
			
			//��ü�ϰ���� �̹����� ���� ��� �̹����� ���� �����
			//���ο� �̹����� ����� �Ŀ� ���ο� �̹��� ��θ� �����Ͽ� db�� ����
//			int success = 0;
//			success = personalqnaService.updatePersonalQna(vo);
//			
//			if(success != 0) {
//				return "redirect:oneqmain.do";
//			}else {
//				return "redirect:oneqmain.do";
//			}
			personalqnaService.updatePersonalQna(vo);
		}
		
		return "redirect:oneqmain.do";
	
//		return "redirect:oneqmain.do";
	}
	
	//1:1 ���� ���� ���
	@RequestMapping(value="deletePersonalQna.do")
	public String deletePersonalQnaPOST(PersonalQnaVO vo, MultipartFile uploadFile, @RequestParam(required = false, defaultValue = "1") int page, @RequestParam(required = false, defaultValue = "1") int range, HttpServletRequest request) {
		
		boolean result = false;
		
		int listCnt=personalqnaService.getBoardListCnt();
		
		HttpSession session=request.getSession();
		String user_id=((String)session.getAttribute("userId"));
		Pagination pagination=new Pagination();
		
		pagination.pageInfo(page, range, listCnt);
		
		//1. db���� �����ϰ���� �����͸� �����´�.
		PersonalQnaVO qnavo = personalqnaService.getPersonalQna(vo);
		
		//2. �����Ϳ��� ���� ��θ� delete�� ��ο� ��ƹ�����.
		String deletePath = qnavo.getQna_personal_image1();
		
		//3. deletePath�� �ִ� �����Ϳ� db�� �ִ� ��� ��ο� ���ؼ� ��ġ�ϴ� ��θ� ����
		List<PersonalQnaVO> qnaList = personalqnaService.getBoard_personalList(pagination, user_id);
		
		for(PersonalQnaVO qna : qnaList) {
			System.out.println("bann : " + qna.getQna_personal_image1());
			System.out.println("deletepath : " + deletePath);
			
			if(qna.getQna_personal_image1().equals(deletePath)) {
				personalqnaService.deletePersonalQna(vo);
				result = true;
				
				break;
			}
		}
		
		if(!result) {
			String deleteKey = qnavo.getQna_personal_image1().substring(49);
			awsS3.delete(deleteKey);
			
			personalqnaService.deletePersonalQna(vo);
			
		}
		
		
//		personalqnaService.deletePersonalQna(vo);
		return "redirect:oneqmain.do";
	}
}
