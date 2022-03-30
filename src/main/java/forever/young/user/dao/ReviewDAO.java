package forever.young.user.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forever.young.user.vo.GoodsQnaVO;
import forever.young.user.vo.ReviewVO;
import forever.young.user.vo.User_order_listVO;

@Repository
public class ReviewDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	//���� ��� �������� 
	public List<ReviewVO> getGoodsReview(GoodsQnaVO vo){
		return sqlSession.selectList("reviewDAO.getGoodsReview", vo);
	}
	
	public String getReviewCount(int goodsSerial) {
		return sqlSession.selectOne("reviewDAO.getReviewCount", goodsSerial);
	}
	//�����ۼ� �� �� �ش� ��ǰ ������
	public User_order_listVO reviewWrite(Integer order_details_serial) {
		
		return sqlSession.selectOne("reviewDAO.reviewWrite", order_details_serial);
	}
	//���� �ۼ�
	public int insertReview(ReviewVO vo) {
		
		return sqlSession.insert("reviewDAO.reviewInsert", vo);
	}
	//�ۼ������� ���� ���� ī��Ʈ
	public Integer count(String user_id) {
		
		return sqlSession.selectOne("reviewDAO.count", user_id);
	}
	//�������������� �ۼ� ������ ���� ����Ʈ �̾Ƴ���
	public List<User_order_listVO> reviewList(String user_id){
		
		return sqlSession.selectList("reviewDAO.myreviewList", user_id);
	}
	//�ۼ��Ϸ�� ���� ����Ʈ
	public List<ReviewVO> reviewCompleteList(String user_id) {
		
		return sqlSession.selectList("reviewDAO.reviewCompleteList", user_id);
	}
	//�ۼ� �Ϸ��� ���� �� ����
	public Integer reviewCount(String user_id) {
		return sqlSession.selectOne("reviewDAO.reviewCount", user_id);
	}
	//�����ϱ� ���� ����� ������
	public ReviewVO updateReviewData(ReviewVO vo) {
		System.out.println("---> MyBatis�� �����ϱ� ���� ����� ������");
		return sqlSession.selectOne("reviewDAO.updateData", vo);
	}
	//�������� ó��
	public Integer updateReview(ReviewVO vo) {
		System.out.println("----> MyBatis�� �������� ó��");
		return sqlSession.update("reviewDAO.updateReview", vo);
	}
	//���� ����
	public Integer deleteReview(int review_serial) {
		System.out.println("----> MyBatis�� ���� ���� ���� ó��");
		return sqlSession.delete("reviewDAO.reviewDelete", review_serial);
	}
	//����������� ������ ������
	public List<ReviewVO> getReviewDel(ReviewVO vo) {
		return sqlSession.selectList("reviewDAO.reviewForDelete", vo);
	}
}
