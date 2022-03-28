package forever.young.admin.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

@Repository
public class AdminDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	// ==========================������===============================
	// ������ ������
	public AdminVO getAdmin(AdminVO admin) {
		return sqlSession.selectOne("AdminDAO.getAdmin", admin);
	}

	// ������ ����
	public int insertAdmin(AdminVO admin) {
		return sqlSession.insert("AdminDAO.insertAdmin", admin);
	}

	// ������ ����Ʈ ������
	public List<AdminVO> getAdminList(AdminVO adminUser) {
		return sqlSession.selectList("AdminDAO.getAdminList", adminUser);
	}

	// ������ ����
	public int updateManager(AdminVO admin) {
		return sqlSession.update("AdminDAO.update", admin);
	}

	// ������ ����
	public int deleteAdmin(AdminVO admin) {
		return sqlSession.delete("AdminDAO.deleteAdmin", admin);
	}

	// ============================ȸ��===============================
	// ȸ�� ����Ʈ ������
	public List<AdminUserVO> getUserList(AdminUserVO adminUser) {
		return sqlSession.selectList("AdminDAO.getUserlist", adminUser);
	}

	// ���� ����
	public int deleteUser(AdminUserVO user) {
		return sqlSession.delete("AdminDAO.deleteUser", user);
	}

	// ==========================1:1����=============================
	// 1:1���� �亯 ��� ����Ʈ
	public List<QnaPersonalVO> getQnaPersonalList(QnaPersonalVO qna) {
		return sqlSession.selectList("qnaDAO.getQnaPersonalList", qna);
	}

	// 1:1���� �亯 ��� ���λ���
	public QnaPersonalVO getQnaPersonalWrite(QnaPersonalVO qna) {
		return sqlSession.selectOne("qnaDAO.getQnaPersonalWrite", qna);
	}

	// 1:1���� �亯 ���
	public int qnaPersonalUpdate(QnaPersonalVO qna) {
		return sqlSession.update("qnaDAO.qnaPersonalUpdate", qna);
	}
	// ==========================��������==============================
	   // �������� ����Ʈ
	   public List<NoticeVO> getNoticeList(NoticeVO notice) {
	      return sqlSession.selectList("AdminDAO.getNoticeList", notice);
	   }

	   // �������� ����
	   public int deleteNotice(NoticeVO notice) {
	      return sqlSession.delete("AdminDAO.deleteNotice", notice);
	   }

	   // �������� ����
	   public NoticeVO getNotice(NoticeVO notice) {
	      return sqlSession.selectOne("AdminDAO.getNotice", notice);
	   }

	   // update
	   public int updateNotice(NoticeVO notice) {
	      return sqlSession.update("AdminDAO.updateNotice", notice);
	   }

	   // �������� �Է�
	   public int insertNotice(NoticeVO notice) {
	      return sqlSession.insert("AdminDAO.insertNotice", notice);
	   }

	   // ==========================FAQ==============================
	   // FAQ ����Ʈ
	   public List<AdminFaqVO> getFaqList(AdminFaqVO faq) {
	      return sqlSession.selectList("AdminDAO.getFaqList", faq);
	   }

	   // FAQ ����
	   public int deleteFaq(AdminFaqVO faq) {
	      return sqlSession.delete("AdminDAO.deleteFaq", faq);
	   }

	   // FAQ �Է�
	   public int insertFaq(AdminFaqVO faq) {
	      return sqlSession.insert("AdminDAO.insertFaq", faq);
	   }

	   // FAQ ����
	   public AdminFaqVO getFaq(AdminFaqVO faq) {
	      return sqlSession.selectOne("AdminDAO.getFaq", faq);
	   }

	   // FAQ update
	   public int updateFaq(AdminFaqVO faq) {
	     
	      return sqlSession.update("AdminDAO.updateFaq", faq);
	   }

	   // ==========================product=============================
	   // ��ǰ��ȸ����Ʈ
	   public List<AdminRegistVO> goodsList(AdminRegistVO regist) {
	      return sqlSession.selectList("RegistDAO.getGoodsList", regist);
	   }

	   // ��ǰ ����
	   public int deleteGoods(AdminRegistVO regist) {
	      return sqlSession.delete("RegistDAO.deleteGoods", regist);
	   }

	   // ��ǰ ����
	   public int updateGoods(AdminRegistVO regist) {
	      return sqlSession.update("RegistDAO.updateGoods", regist);
	   }

	   public int updateGoodsDetail(AdminRegistVO regist) {
	      return sqlSession.update("RegistDAO.updateGoodsDetail", regist);
	   }

	   // ��ǰ ���
	   public int insertGoods(ProductVO product) {
	      return sqlSession.insert("RegistDAO.insertGoods", product);
	   }

	   public int insertGoodsDetail(ProductVO product) {
	      return sqlSession.insert("RegistDAO.insertGoodsDetail", product);
	   }

	   public int insertGoodsStock(ProductVO product) {
	      return sqlSession.insert("RegistDAO.insertGoodsStock", product);
	   }

	   // ==========================ī�װ� ����Ʈ=============================
	   public List<CategoryFirstVO> getCategoryFirstList(ProductVO product) {
	      return sqlSession.selectList("RegistDAO.getCategoryFirstList", product);
	   }

	   public List<CategorySecondVO> getCategorySecondList(ProductVO product) {
	      return sqlSession.selectList("RegistDAO.getCategorySecondList", product);
	   }

	   public List<CategoryThirdVO> getCategoryThirdList(ProductVO product) {
	      return sqlSession.selectList("RegistDAO.getCategoryThirdList", product);
	   }

	   public ProductVO getProduct(ProductVO product) {
	      return sqlSession.selectOne("RegistDAO.getProduct", product);
	   }

	   public ProductVO getProductDetail(ProductVO product) {
	      return sqlSession.selectOne("RegistDAO.getProductDetail", product);
	   }
	   ///////////////////////////////////////////////////////////////
	 //������ ���� ����Ʈ
		public List<AdminReviewVO> getReviewGoodsName(AdminReviewVO review)throws Exception{
			return sqlSession.selectList("adminReviewDAO.reviewGoodsName", review);
		}
		
		//������ ���� ����
		public AdminReviewVO getReviewContent(AdminReviewVO review)throws Exception{
			return sqlSession.selectOne( "adminReviewDAO.reviewContent", review);
		}
		
		//������ ���� ����
		public int deleteReview(AdminReviewVO review)throws Exception {
			return sqlSession.delete("adminReviewDAO.deleteReview", review);
		}
		
		//������ ���� ����Ʈ
		public List<AdminReviewVO> getReviewList(AdminReviewVO review)throws Exception{
			return sqlSession.selectList( "adminReviewDAO.reviewList", review);
		}
		//=========================QNA========================
		//������ QnA ����Ʈ(�亯 ���)
		public List<GoodsQnaVO> gqnaList(GoodsQnaVO gqna)throws Exception{
			return sqlSession.selectList("gqnaDAO.gqnaList", gqna);
		}
		
		//������ QnA ���� ����(�亯 ���)
		public int deleteGqna(GoodsQnaVO gqna)throws Exception {
			return sqlSession.delete( "gqnaDAO.deleteGqna", gqna);
		}
		
		//������ QnA ���� �亯 �ϱ� ��
		public GoodsQnaVO gqnaOne(GoodsQnaVO gqna) {
			return sqlSession.selectOne("gqnaDAO.gqnaOne", gqna);
		}
		
		//������ QnA ���� �亯
		public int updateGqna(GoodsQnaVO gqna)throws Exception{
			return sqlSession.update("gqnaDAO.updateGqna", gqna);
		}
		
		//������ QnA ���� ����Ʈ(�亯 �Ϸ�)
		public List<GoodsQnaVO> gqnaListSuc(GoodsQnaVO gqna)throws Exception{
			return sqlSession.selectList("gqnaDAO.gqnaListSuc", gqna);
		}
		
		//������ QnA ���� ����Ʈ �亯 Ȯ�� �� ���� 
		public int updateGqnaSuc(GoodsQnaVO gqna)throws Exception{
			return sqlSession.update("gqnaDAO.updateGqna", gqna);
		}
		//============================�ֹ�����===============================
		//������ �ֹ����� (���� �Ϸ� ~ ��� �غ� ��)
		public List<AdminOrderVO> getOderWaitList()throws Exception {
			return sqlSession.selectList("adminOrderDAO.getOrderWaitList");
		}
		
		//������ �ֹ����� order ���̺� ���� ���
		public List<AdminOrderVO> getOrderCancleList()throws Exception{
			return sqlSession.selectList("adminOrderDAO.getOrderCancleList");
		}
		//=================================��ʰ���==========================
			
		//������ ��� ����Ʈ
		public List<AdminBannerVO> getBannerList()throws Exception {
			return sqlSession.selectList("bannerDAO.getBannerList");
		}
		
		//������ ��� ���
		public int insertBanner(AdminBannerVO banner)throws Exception {
			return sqlSession.insert("bannerDAO.insertBanner", banner);
		}
		
		//������ ��������
		public AdminBannerVO getBanner(AdminBannerVO banner)throws Exception {
			return sqlSession.selectOne("bannerDAO.getBanner", banner);
		}
		
		//������ ��� ����
		public int deleteBanner(AdminBannerVO banner)throws Exception{
			return sqlSession.delete("bannerDAO.deleteBanner", banner);
		}
		
		//������ ��� ����
		public int updateBanner1(AdminBannerVO banner)throws Exception{
			return sqlSession.update("bannerDAO.updateBanner1", banner);
		}
		
		public int updateBanner2(AdminBannerVO banner)throws Exception{
			return sqlSession.update("bannerDAO.updateBanner2",banner);
		}
}
