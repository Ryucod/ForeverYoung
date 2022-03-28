package forever.young.user.service;

import java.util.List;

import forever.young.user.vo.GoodsQnaVO;
import forever.young.user.vo.ReviewVO;
import forever.young.user.vo.User_order_listVO;

public interface ReviewService {
	//���� ��� ��������
	List<ReviewVO> getGoodsReview(GoodsQnaVO vo);
//	String getReviewCount(int goodsSerial);
	//���� �ۼ��� �ش� ��ǰ�� �ø��� �������
	public User_order_listVO reviewWrite(Integer order_details_serial);
	//���� �ۼ�
	public int insertReview(ReviewVO vo);
	//�ۼ� ������ ���� ���� ����
	public Integer count(String user_id);
	//�ۼ� ������ ���� ����Ʈ �̾Ƴ���
	public List<User_order_listVO> reviewList(String user_id);
	//�ۼ� �Ϸ�� ���� ����Ʈ ����ϱ�
	public List<ReviewVO> reviewCompleteList(String user_id);
	//�����ϱ� ���� ������ ���� ����
	public ReviewVO updateReviewData(ReviewVO vo);
	//�������� ó��
	public Integer updateReview(ReviewVO vo);
	//�������� ó��
	public Integer deleteReview(int review_serial);
	//�ۼ� �Ϸ��� ���� �� ����
	public Integer reviewCount(String user_id);
	
	String getReviewCount(int goodsSerial);
}
