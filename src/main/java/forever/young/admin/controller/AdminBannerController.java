package forever.young.admin.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import forever.young.admin.service.AdminService;
import forever.young.admin.vo.AdminBannerVO;
import s3.AwsS3;

@Controller
public class AdminBannerController {
	private String url = "https://fyawsbucket.s3.ap-northeast-2.amazonaws.com/";
	
	@Autowired
	private AdminService service;

	//DB
	@Autowired
	private AwsS3 awsS3;
	
	//��� ����Ʈ
	@RequestMapping("admin_bannerList.mdo")
	public String adminBannerList(Model model) throws Exception {
		model.addAttribute("bannerList", service.getBannerList());
		return "banner/admin_bannerList";
	}
	
	//������ ��� ���������
	@RequestMapping("admin_bannerWrite.mdo")
	public String adminBannerWrite()throws Exception {
		return "banner/admin_bannerWrite";
	}
	
	//������ ��� ����������
	@RequestMapping("admin_bannerInsert.mdo")
	public String adminBannerInsert(MultipartFile uploadFile, AdminBannerVO banner) throws Exception {
		try {
			String key = "banner/"+uploadFile.getOriginalFilename();
			InputStream is = uploadFile.getInputStream();
			String contentType = uploadFile.getContentType();
			long contentLength = uploadFile.getSize();
			awsS3.upload(is, key, contentType, contentLength);
			
			banner.setBanner_filepath(url + key);
			service.insertBanner(banner);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return "redirect:admin_bannerList.mdo";
	}
	
	//������ ��� ���λ���������
	@RequestMapping("/admin_banner.mdo")
	public String adminBanner(Model model, AdminBannerVO banner) throws Exception {
		model.addAttribute("banner", service.getBanner(banner));
		
		return "banner/admin_banner";
	}
	
	//������ ��� ����������
	@RequestMapping("admin_bannerDelete.mdo")
	public String adminBannerDelete(AdminBannerVO banner) throws Exception {
		boolean result = false;
		int success = 0;
		
		//1. db���� �����ϰ���� �����͸� �����´�.
		AdminBannerVO bannerVO = service.getBanner(banner);
		
		//2. �����Ϳ��� ���� ��θ� delete�� ��ο� ��ƹ�����.
		String deletePath = bannerVO.getBanner_filepath();
		
		//3. deletePath�� �ִ� �����Ϳ� db�� �ִ� ��� ��ο� ���ؼ� ��ġ�ϴ� ��θ� ����
		List<AdminBannerVO> bannerList = service.getBannerList();
		
		for(AdminBannerVO bann : bannerList) {
			System.out.println("bann : " + bann.getBanner_filepath());
			System.out.println("deletepath : " + deletePath);
			
			if(bann.getBanner_filepath().equals(deletePath)) {
				success = service.deleteBanner(banner);
				result = true;
				
				break;
			}
		}
		
		if(!result) {
			String deleteKey = bannerVO.getBanner_filepath().substring(49);
			awsS3.delete(deleteKey);
			
			success = service.deleteBanner(banner);
			
			if(success != 0) {
				return "redirect:admin_bannerList.mdo";
			}else {
				return "redirect:admin_banner.mdo";
			}
		}else {
			if(success != 0) {
				return "redirect:admin_bannerList.mdo";
			}else {
				return "redirect:admin_banner.mdo";
			}
		}
	}
	
	//������ ��� ����
	@RequestMapping("admin_bannerUpdate.mdo")
	public String adminBannerUpdate(MultipartFile uploadFile,AdminBannerVO banner) throws Exception {
		if(uploadFile.getSize() != 0) {
			//��ü�ϰ� ���� �̹����� ���� ��� 0�� �ƴϹǷ� s3�� �ִ� �̹����� ����
			AdminBannerVO bannerVO = service.getBanner(banner);
			String deleteKey = bannerVO.getBanner_filepath().substring(49);
			awsS3.delete(deleteKey);
			
			//���ο� �̹����� s3�� ���
			try {
				String key = "banner/"+uploadFile.getOriginalFilename();
				InputStream is = uploadFile.getInputStream();
				String contentType = uploadFile.getContentType();
				long contentLength = uploadFile.getSize();
				awsS3.upload(is, key, contentType, contentLength);
				
				banner.setBanner_filepath(url + key);
			}catch(IOException e) {
				e.printStackTrace();
			}
			
			//��ü�ϰ���� �̹����� ���� ��� �̹����� ���� �����
			//���ο� �̹����� ����� �Ŀ� ���ο� �̹��� ��θ� �����Ͽ� db�� ����
			int success = 0;
			success = service.updateBanner1(banner);
			
			if(success != 0) {
				return "redirect:admin_bannerList.mdo";
			}else {
				return "redirect:admin_banner.mdo";
			}
		}else {
			int success = 0;
			success = service.updateBanner2(banner);
			
			if(success != 0) {
				return "redirect:admin_bannerList.mdo";
			}else {
				return "redirect:admin_banner.mdo";
			}
		}
	}
}