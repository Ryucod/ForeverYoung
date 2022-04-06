package forever.young.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.SessionStatus;

import forever.young.admin.dao.AdminDAO;
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
@Service("AdminService")
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDAO adminDao;

	// =========================������=============================
	@Override
	public AdminVO getAdmin(AdminVO admin) {
		return adminDao.getAdmin(admin);
	}

	@Override
	public List<AdminVO> adminList(AdminVO admin) {
		return adminDao.getAdminList(admin);
	}

	@Override
	public void logout(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
	}

	@Override
	public int deleteAdmin(AdminVO admin) {
		return adminDao.deleteAdmin(admin);
	}

	// ======================ȸ��=========================
	@Override
	public List<AdminUserVO> userList(AdminUserVO adminUser) {
		return adminDao.getUserList(adminUser);
	}

	// ȸ�� ����
	@Override
	public int deleteUser(AdminUserVO user) {
		return adminDao.deleteUser(user);
	}
	//ȸ�� ����
		@Override
		public int updateUser(AdminUserVO user) {
			return adminDao.updateUser(user);
		}
	//ȸ�� ���� ������
//		@Override
//		public String getMail(AdminUserVO user) {
//			return adminDao.getMail(user);
//		}
		@Override
		public AdminUserVO getUserInfo(AdminUserVO user) {
			// TODO Auto-generated method stub
			return adminDao.getUserInfo(user);
		}
		@Override
		public String getMail(AdminUserVO user) {
			return adminDao.getMail(user);
		}
		@Override
		public int getUserStatus(AdminUserVO user) {
			// TODO Auto-generated method stub
			return adminDao.getUserStatus(user);
		}

	// =====================1:1����=========================
	// ������ -1:1 ���� �亯 ��� ����Ʈ
	@Override
	public List<QnaPersonalVO> getQnaPersonalList(QnaPersonalVO qna) {
		return adminDao.getQnaPersonalList(qna);
	}

	// ������ - 1:1 ���� �亯 ��� ���λ���
	@Override
	public QnaPersonalVO getQnaPersonalWrite(QnaPersonalVO qna) {
		return adminDao.getQnaPersonalWrite(qna);
	}

	// ������ - 1:1 ���� �亯 ���
	@Override
	public int qnaPersonalUpdate(QnaPersonalVO qna) {
		return adminDao.qnaPersonalUpdate(qna);
	}
	// =====================��������=========================
	   // �������� ����Ʈ
	   @Override
	   public List<NoticeVO> getNoticeList(NoticeVO notice) {
	      return adminDao.getNoticeList(notice);
	   }

	   // �������� ����
	   @Override
	   public int deleteNotice(NoticeVO notice) {
	      return adminDao.deleteNotice(notice);
	   }

	   // �������� ����
	   @Override
	   public NoticeVO getNotice(NoticeVO notice) {
	      return adminDao.getNotice(notice);
	   }

	   // update
	   @Override
	   public int updateNotice(NoticeVO notice) {
	      return adminDao.updateNotice(notice);
	   }

	   // �������� �Է�
	   @Override
	   public int insertNotice(NoticeVO notice) {
	      return adminDao.insertNotice(notice);
	   }

	   // =====================FAQ=========================
	   // FAQ ����Ʈ
	   @Override
	   public List<AdminFaqVO> getFaqList(AdminFaqVO faq) {
	      return adminDao.getFaqList(faq);
	   }

	   // FAQ ����
	   @Override
	   public int deleteFaq(AdminFaqVO faq) {
	      return adminDao.deleteFaq(faq);
	   }

	   // FAQ �Է�
	   @Override
	   public int insertFaq(AdminFaqVO faq) {
	      return adminDao.insertFaq(faq);
	   }

	   // FAQ ����
	   @Override
	   public AdminFaqVO getFaq(AdminFaqVO faq) {
	      return adminDao.getFaq(faq);
	   }

	   // update
	   @Override
	   public int updateFaq(AdminFaqVO faq) {
	      return adminDao.updateFaq(faq);
	   }

	   // =====================product===========================
	   // ��ǰ��ȸ����Ʈ
	   @Override
	   public List<AdminRegistVO> goodsList(AdminRegistVO regist) {
	      return adminDao.goodsList(regist);
	   }

	   // ��ǰ����
	   @Override
	   public int deleteGoods(AdminRegistVO regist) {
	      return adminDao.deleteGoods(regist);
	   }

	   // ��ǰ ����
	   @Override
	   public int updateGoods(AdminRegistVO regist) {
	      return adminDao.updateGoods(regist);
	   }

	   @Override
	   public int updateGoodsDetail(AdminRegistVO regist) {
	      return adminDao.updateGoodsDetail(regist);
	   }

	   // ��ǰ ���
	   @Override
	   public int insertGoods(ProductVO product) {
	      return adminDao.insertGoods(product);
	   }

	   @Override
	   public int insertGoodsDetail(ProductVO product) {
	      return adminDao.insertGoodsDetail(product);
	   }

	   @Override
	   public int insertGoodsStock(ProductVO product) {
	      return adminDao.insertGoodsStock(product);
	   }

	   // ==========================ī�װ� ����Ʈ=============================
	   @Override
	   public List<CategoryFirstVO> getCategoryFirstList(ProductVO product) {
	      return adminDao.getCategoryFirstList(product);
	   }

	   @Override
	   public List<CategorySecondVO> getCategorySecondList(ProductVO product) {
	      return adminDao.getCategorySecondList(product);
	   }

	   @Override
	   public List<CategoryThirdVO> getCategoryThirdList(ProductVO product) {
	      return adminDao.getCategoryThirdList(product);
	   }

	   @Override
	   public ProductVO getProduct(ProductVO product) {
	      return adminDao.getProduct(product);
	   }

	   @Override
	   public ProductVO getProductDetail(ProductVO product) {
	      return adminDao.getProductDetail(product);
	   }
	   //================================����=====================
	 //������ �����ִ� ��ǰ
		@Override
		public List<AdminReviewVO> getReviewGoodsName(AdminReviewVO review)throws Exception {
			// TODO Auto-generated method stub
			return adminDao.getReviewGoodsName(review);
		}
		
		//������ ���� ����
		@Override
		public AdminReviewVO getReviewContent(AdminReviewVO review)throws Exception {
			// TODO Auto-generated method stub
			return adminDao.getReviewContent(review);
		}
		
		//������ ���� ����
		@Override
		public int deleteReview(AdminReviewVO review) throws Exception {
			// TODO Auto-generated method stub
			return adminDao.deleteReview(review);
		}
		
		//������ ���� ����Ʈ
		@Override
		public List<AdminReviewVO> getReviewList(AdminReviewVO review) throws Exception {
			// TODO Auto-generated method stub
			return adminDao.getReviewList(review);
		}
		//=====================QNA========================================
		//������ ��ǰ ���� ����Ʈ (�亯���)
		@Override
		public List<GoodsQnaVO> gqnaList(GoodsQnaVO gqna) throws Exception {
			// TODO Auto-generated method stub
			return adminDao.gqnaList(gqna);
		}
		
		//������ ��ǰ ���� ����
		@Override
		public int deleteGqna(GoodsQnaVO gqna) throws Exception {
			// TODO Auto-generated method stub
			return adminDao.deleteGqna(gqna);
		}
		
		//������ ��ǰ ���� �亯 ��
		@Override
		public GoodsQnaVO gqnaOne(GoodsQnaVO gqna) throws Exception {
			// TODO Auto-generated method stub
			return adminDao.gqnaOne(gqna);
		}
		
		//������ ��ǰ ���� �亯 ���
		@Override
		public int updateGqna(GoodsQnaVO gqna) throws Exception {
			// TODO Auto-generated method stub
			return adminDao.updateGqna(gqna);
		}
		
		//������ ��ǰ ���� ����Ʈ(�亯 �Ϸ�)
		@Override
		public List<GoodsQnaVO> gqnaListSuc(GoodsQnaVO gqna) throws Exception {
			// TODO Auto-generated method stub
			return adminDao.gqnaListSuc(gqna);
		}
		
		//������ ��ǰ ���� �亯 Ȯ�� �� ����
		@Override
		public int updateGqnaSuc(GoodsQnaVO gqna) throws Exception {
			// TODO Auto-generated method stub
			return adminDao.updateGqnaSuc(gqna);
		}
		//������ �ֹ�����
		@Override
		public List<AdminOrderVO> getOrderWaitList() throws Exception {
			// TODO Auto-generated method stub
			return adminDao.getOderWaitList();
		}
		
		//������ �ֹ����� (���� ���)
		@Override
		public List<AdminOrderVO> getOrderCancleList() throws Exception {
			// TODO Auto-generated method stub
			return adminDao.getOrderCancleList();
		}
		//������ ��� ����Ʈ
		@Override
		public List<AdminBannerVO> getBannerList() throws Exception {
			// TODO Auto-generated method stub
			return adminDao.getBannerList();
		}
		
		//������ ��� ���
		@Override
		public int insertBanner(AdminBannerVO banner) throws Exception {
			// TODO Auto-generated method stub
			return adminDao.insertBanner(banner);
		}
		
		//������ ��� ��
		@Override
		public AdminBannerVO getBanner(AdminBannerVO banner) throws Exception {
			// TODO Auto-generated method stub
			return adminDao.getBanner(banner);
		}
		
		//������ ��� ����
		@Override
		public int deleteBanner(AdminBannerVO banner) throws Exception {
			// TODO Auto-generated method stub
			return adminDao.deleteBanner(banner);
		}
		
		//������ ��� ���� (�̹��� ������)
		@Override
		public int updateBanner1(AdminBannerVO banner) throws Exception {
			// TODO Auto-generated method stub
			return adminDao.updateBanner1(banner);
		}
		
		//������ ��� ���� (�̹��� ������)
		@Override
		public int updateBanner2(AdminBannerVO banner) throws Exception {
			// TODO Auto-generated method stub
			return adminDao.updateBanner2(banner);
		}

		
		
}
