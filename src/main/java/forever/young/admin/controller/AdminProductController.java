package forever.young.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import forever.young.admin.service.AdminService;
import forever.young.admin.vo.AdminRegistVO;
import forever.young.vo.ProductVO;

@SessionAttributes("category_goods_serial")
@Controller
public class AdminProductController {
	private String url = "https://fyawsbucket.s3.ap-northeast-2.amazonaws.com/";

	// DB
	//@Autowired
	//private AwsS3 awsS3;

	@Autowired
	private AdminService adminService;

	// ��ǰ��� ����Ʈ
	@RequestMapping("admin_product.mdo")
	public String getGoodsList(AdminRegistVO regist, Model model) {
		List<AdminRegistVO> productList = adminService.goodsList(regist);
//		for (AdminRegistVO vo : productList) {
//			if (vo.getGoods_detail_stock_quantity() < 15) {
//				model.addAttribute("notification", true);
//				break;
//			}
//		}
		model.addAttribute("goodsList", productList);
		System.out.println(productList);
		return "dashboard/product/product";
	}

	// ��ǰ ����
	@RequestMapping("deleteGoods.mdo")
	public String deleteGoods(AdminRegistVO regist) {
		System.out.println("��ǰ���� �޼��� ����");
		int success = 0;
		success = adminService.deleteGoods(regist);
		if (success != 0) {
			return "redirect:admin_product.mdo";
		} else {
			return "redirect:admin_product.mdo";
		}
	}

	// ��ǰ �� ȭ�� �̵�
	@RequestMapping("updateGoodsPage.mdo")
	public String updateGoodsPage(Model model, ProductVO product) {
		model.addAttribute("categoryFirst", adminService.getCategoryFirstList(product));
		model.addAttribute("categorySecond", adminService.getCategorySecondList(product));
		model.addAttribute("categoryThird", adminService.getCategoryThirdList(product));
		model.addAttribute("category_goods_serial", product.getCategory_goods_serial());

		ProductVO getProduct = adminService.getProduct(product);
		model.addAttribute("getProduct", getProduct);
		ProductVO getProductDetail = adminService.getProductDetail(product);
		model.addAttribute("getProductDetail", getProductDetail);
		return "dashboard/product/productWrite";
	}

	// ��ǰ ����
//	@RequestMapping("updateGoods.mdo")
//	public String updateGoods(MultipartFile uploadFile,AdminRegistVO regist, ProductVO product) {
//		adminService.updateGoods(regist);
//		adminService.updateGoodsDetail(regist);
//		
//		
//		if(uploadFile.getSize() != 0) {
//			//��ü�ϰ� ���� �̹����� ���� ��� 0�� �ƴϹǷ� s3�� �ִ� �̹����� ����
//			ProductVO adminRegist = adminService.getProduct(product);
//			String deleteKey = adminRegist.getCategory_goods_main_img().substring(49);
//			awsS3.delete(deleteKey);
//			
//			//���ο� �̹����� s3�� ���
//			try {
//				String key = "product/"+uploadFile.getOriginalFilename();
//				InputStream is = uploadFile.getInputStream();
//				String contentType = uploadFile.getContentType();
//				long contentLength = uploadFile.getSize();
//				awsS3.upload(is, key, contentType, contentLength);
//				
//				System.out.println("����");
//				
//				adminRegist.setCategory_goods_main_img(url + key);
//			}catch(IOException e) {
//				e.printStackTrace();
//			}
//			
//			//��ü�ϰ���� �̹����� ���� ��� �̹����� ���� �����
//			//���ο� �̹����� ����� �Ŀ� ���ο� �̹��� ��θ� �����Ͽ� db�� ����
//			int success = 0;
//			success = adminService.updateGoods(regist);
//			
//			if(success != 0) {
//				return "redirect:admin_product.mdo";
//			}else {
//				return "redirect:admin_product.mdo";
//			}
//		}else {
//			int success = 0;
//			success = adminService.updateGoods(regist);
//			
//			if(success != 0) {
//				return "redirect:admin_product.mdo";
//			}else {
//				return "redirect:admin_product.mdo";
//			}
//		}
//	}

	// ��ǰ ����
	@RequestMapping("updateGoods.mdo") // ���� ī�װ� ���λ��׿��� �����ϴ°� ������
	public String categoryGoodsUpdate(AdminRegistVO regist, ProductVO product
			/*@RequestParam("category_goods_main_img") MultipartFile file1,
			@RequestParam("category_goods_thum_img") MultipartFile file2,
			@RequestParam("category_goods_detail") MultipartFile file3*/) {
		adminService.updateGoods(regist);
		adminService.updateGoodsDetail(regist);

//		int success = 0;
//		String key1 = null, key2 = null, key3 = null;
//		String deleteKey1, deleteKey2, deleteKey3;

		// S3�۾�
		// ���� �̹��� �����ϰ� �������
//		if(file1.getSize() != 0) {
//			//���� ��ϵǾ��ִ� �̹����� ��θ� �ҷ��ͼ� S3�� �̹����� �����Ѵ�.
////			deleteKey1 = adminService.getProduct(product).getCategory_goods_main_img().substring(49);
////			awsS3.delete(deleteKey1);
//			try {
//				key1 = "categoryGoods/" + file1.getOriginalFilename();
//				InputStream is = file1.getInputStream();
//				String contentType = file1.getContentType(); 
//				long contentLength = file1.getSize();
//				awsS3.upload(is, key1, contentType, contentLength);
//			}catch (IOException e) { e.printStackTrace();}
//			//db�۾��� ���� �� 
//			product.setCategory_goods_main_img(url+key1);
//		}
//		
//		//����� �̹��� �����ϰ� �������
//		if(file2.getSize() !=0){
////			deleteKey2 = adminService.getProduct(product).getCategory_goods_thum_img().substring(49);
////			awsS3.delete(deleteKey2);
//			try {
//				key2 =  "categoryGoods/" + file2.getOriginalFilename();
//				InputStream is = file2.getInputStream();
//				String contentType = file2.getContentType(); 
//				long contentLength = file2.getSize();
//				awsS3.upload(is, key2, contentType, contentLength);
//			} catch (IOException e) { e.printStackTrace();}
//			//db�۾��� ���� �� 
//			product.setCategory_goods_thum_img(url+key2);
//		}
//		
//		//��ǰ ���� �� �̹��� �����ϰ� �������
//		if(file3.getSize() !=0){
////			deleteKey3 = adminService.getProduct(product).getCategory_goods_detail().substring(49);
////			awsS3.delete(deleteKey3);
//			try {
//				key3 = "categoryGoods/" + file3.getOriginalFilename();
//				InputStream is = file3.getInputStream();
//				String contentType = file3.getContentType(); 
//				long contentLength = file3.getSize();
//				awsS3.upload(is, key3, contentType, contentLength);
//			} catch (IOException e) { e.printStackTrace();}
//			//db�۾��� ���� �� 
//			product.setCategory_goods_detail(url+key3);	
//		}
//		success = adminService.updateGoods(regist);	

//		if (success != 0) {
//			return "redirect:admin_product.mdo";
//		} else {
			return "redirect:admin_product.mdo";
//		}

	}

	@RequestMapping("insertGoodsPage.mdo")
	public String insertProductPage(Model model, ProductVO product) {
		model.addAttribute("categoryFirst", adminService.getCategoryFirstList(product));
		model.addAttribute("categorySecond", adminService.getCategorySecondList(product));
		model.addAttribute("categoryThird", adminService.getCategoryThirdList(product));
		model.addAttribute("category_goods_serial", product.getCategory_goods_serial());
		return "dashboard/product/insertProduct";
	}

	// ��ǰ ī�װ� ���(Insert)
	@RequestMapping("insertGoods.mdo")
	public String insertProuct(ProductVO product
								/*
								 * @RequestParam("category_goods_main_img") MultipartFile file1,
								 * 
								 * @RequestParam("category_goods_thum_img") MultipartFile file2,
								 * 
								 * @RequestParam("category_goods_detail") MultipartFile file3,
								 */
		) {
//		int result1=0, result2=0;
//		String key1 = null, key2 = null, key3 = null;
//
//		// ���� �̹��� ����ϴ°��
//		if (file1.getSize() != 0) {
//			key1 = "categoryGoods/" + file1.getOriginalFilename();
//			product.setCategory_goods_main_img(url + key1);
//		}
//		// ����� �̹��� ����ϴ°��
//		if (file2.getSize() != 0) {
//			key2 = "categoryGoods/" + file2.getOriginalFilename();
//			product.setCategory_goods_thum_img(url + key2);
//		}
//		// �� �̹��� ����ϴ°��
//		if (file3.getSize() != 0) {
//			key3 = "categoryGoods/" + file3.getOriginalFilename();
//			product.setCategory_goods_detail(url + key3);
//		}
//
//		// 3�� ī�װ� ��ǰ ���(db�۾�)
		adminService.insertGoods(product);
//		// 3�� ī�װ� ��ǰ ������ ���(db�۾�)
		adminService.insertGoodsDetail(product);
		adminService.insertGoodsStock(product);
//
//		System.out.println(result1);
//		System.out.println(result2);
//		// db�� ���������� ���� s3�۾� ����
//		if (result1 != 0 && result2 != 0) {
//			try {
//				// ����
//				InputStream is = file1.getInputStream();
//				String contentType = file1.getContentType();
//				long contentLength = file1.getSize();
//				awsS3.upload(is, key1, contentType, contentLength);
//
//				// �����
//				is = file2.getInputStream();
//				contentType = file2.getContentType();
//				contentLength = file2.getSize();
//				awsS3.upload(is, key2, contentType, contentLength);
//
//				// ��
//				is = file3.getInputStream();
//				contentType = file3.getContentType();
//				contentLength = file3.getSize();
//				awsS3.upload(is, key3, contentType, contentLength);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//		} // end if
		return "redirect:admin_product.mdo";
	}

}
