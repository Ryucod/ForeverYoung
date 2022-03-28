package forever.young.user.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forever.young.user.vo.FaqVO;
import forever.young.user.vo.Pagination;


@Repository
public class FaqDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	/* ����Ʈ ��ü ��� �ҷ�����*/
	public List<FaqVO> getBoard_faqList(Pagination pagination) {
		System.out.println("faq����Ʈ �����ò���");
		return sqlSession.selectList("faqDAO.getFAQList", pagination);
	}
	
	/* �Խñ� �� ���� �������� */
	public int getFaqListCnt() {
		return sqlSession.selectOne("faqDAO.getFaqListCnt");
	}
	
	/* faq �󼼱� �ϳ� �ҷ�����*/
	
	

}
