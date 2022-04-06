package forever.young.admin.service;

import java.util.List;

import org.springframework.web.bind.support.SessionStatus;

import forever.young.admin.vo.AdminBannerVO;
import forever.young.admin.vo.AdminFaqVO;
import forever.young.admin.vo.AdminOrderVO;
import forever.young.admin.vo.AdminRegistVO;
import forever.young.admin.vo.AdminReviewVO;
import forever.young.admin.vo.AdminUserVO;
import forever.young.admin.vo.AdminVO;
import forever.young.admin.vo.GoodsQnaVO;
import forever.young.admin.vo.NoticeVO;
import forever.young.admin.vo.QnaPersonalVO;
import forever.young.vo.CategoryFirstVO;
import forever.young.vo.CategorySecondVO;
import forever.young.vo.CategoryThirdVO;
import forever.young.vo.ProductVO;

public interface AdminService {

	// ======================������=============================
	// ������ ������
	AdminVO getAdmin(AdminVO admin);

	// ������ ����Ʈ ������
	List<AdminVO> adminList(AdminVO admin);

	// ������ �α׾ƿ�
	void logout(SessionStatus sessionStatus);

	// ������ ����
	int deleteAdmin(AdminVO admin);

	// ======================ȸ��==============================
	// ȸ�� ����Ʈ ������
	List<AdminUserVO> userList(AdminUserVO adminUser);

	// ȸ�� ����
	int deleteUser(AdminUserVO user);
	
	public int updateUser(AdminUserVO user);
	
	//public String getMail(AdminUserVO user);
	//ȸ�� ���� �̸��Ϻ�����
	public AdminUserVO getUserInfo(AdminUserVO user);
	public String getMail(AdminUserVO user);
	public int getUserStatus(AdminUserVO user);
	// =======================1:1����==============================
	// 1:1���� �亯��� ����Ʈ
	List<QnaPersonalVO> getQnaPersonalList(QnaPersonalVO qna);

	// 1:1���� �亯��� ���λ���
	public QnaPersonalVO getQnaPersonalWrite(QnaPersonalVO qna);

	// 1:1���� �亯��� �亯 ���
	int qnaPersonalUpdate(QnaPersonalVO qna);
	
	// =======================��������==============================
	   // �������� ����Ʈ
	   List<NoticeVO> getNoticeList(NoticeVO notice);

	   // �������� ����
	   int deleteNotice(NoticeVO notice);

	   // �������� ����
	   NoticeVO getNotice(NoticeVO notice);

	   // update
	   int updateNotice(NoticeVO notice);

	   // �������� �Է�
	   int insertNotice(NoticeVO notice);

	   // =======================FAQ==============================
	   // FAQ ����Ʈ
	   List<AdminFaqVO> getFaqList(AdminFaqVO faq);

	   // FAQ ����
	   int deleteFaq(AdminFaqVO faq);

	   // FAQ �Է�
	   int insertFaq(AdminFaqVO faq);

	   // �������� ����
	   AdminFaqVO getFaq(AdminFaqVO faq);

	   // update
	   int updateFaq(AdminFaqVO faq);

	   // =======================product=============================
	   // ��ǰ��ȸ����Ʈ
	   List<AdminRegistVO> goodsList(AdminRegistVO regist);

	   // ��ǰ ����
	   int deleteGoods(AdminRegistVO regist);

	   // ��ǰ ����
	   int updateGoods(AdminRegistVO regist);

	   int updateGoodsDetail(AdminRegistVO regist);

	   // ��ǰ ���
	   int insertGoods(ProductVO product);

	   int insertGoodsDetail(ProductVO product);

	   int insertGoodsStock(ProductVO product);

	   // ==========================ī�װ� ����Ʈ=============================
	   List<CategoryFirstVO> getCategoryFirstList(ProductVO product);

	   List<CategorySecondVO> getCategorySecondList(ProductVO product);

	   List<CategoryThirdVO> getCategoryThirdList(ProductVO product);

	   public ProductVO getProduct(ProductVO product);

	   public ProductVO getProductDetail(ProductVO product);
	   //==================����============================
	 //������ ���� ��ǰ
		List<AdminReviewVO> getReviewGoodsName(AdminReviewVO review)throws Exception;

		//������ ���� ����
		AdminReviewVO getReviewContent(AdminReviewVO review)throws Exception;
		
		//������ ���� ����
		int deleteReview(AdminReviewVO review)throws Exception;
		
		//������ ���� ����Ʈ
		List<AdminReviewVO> getReviewList(AdminReviewVO review)throws Exception;
		//===============================QNA=============================
		//������ ��ǰ ���� ����Ʈ (�亯 ���)
		List<GoodsQnaVO> gqnaList(GoodsQnaVO gqna)throws Exception;
		
		//������ ��ǰ ���� ����Ʈ ����(�亯 ���)
		int deleteGqna(GoodsQnaVO gqna)throws Exception;
		
		//������ ��ǰ ���� �亯 �� 
		GoodsQnaVO gqnaOne(GoodsQnaVO gqna)throws Exception;
		
		//������ ��ǰ ���� �亯
		int updateGqna(GoodsQnaVO gqna)throws Exception;
		
		//������ ��ǰ ���� ����Ʈ(�亯 �Ϸ�)
		List<GoodsQnaVO> gqnaListSuc(GoodsQnaVO gqna)throws Exception;
		
		//������ ��ǰ ���� �亯 Ȯ�� �� ����
		int updateGqnaSuc(GoodsQnaVO gqna)throws Exception;
		//====================�ֹ�����==================================
		//������ �ֹ����� (�����Ϸ�)
		List<AdminOrderVO> getOrderWaitList()throws Exception;
		
		//������ �ֹ����� ����Ʈ (�������)
		List<AdminOrderVO> getOrderCancleList()throws Exception;
		//=====================���===============================
		//������ ��� ����Ʈ
		List<AdminBannerVO>getBannerList()throws Exception;
		
		//������ ��� ���
		int insertBanner(AdminBannerVO banner)throws Exception;
		
		//������ ��� ��
		AdminBannerVO getBanner(AdminBannerVO banner)throws Exception;
		
		//������ ��� ����
		int deleteBanner(AdminBannerVO banner)throws Exception;
		
		//������ ��� ����
		int updateBanner1(AdminBannerVO banner)throws Exception;
		
		int updateBanner2(AdminBannerVO banner)throws Exception;
}
