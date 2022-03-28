package forever.young.admin.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forever.young.admin.vo.AdminTermsVO;

@Repository
public class AdminTermsDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	// �������Ʈ
	public List<AdminTermsVO> getTermslist() {
		return sqlSession.selectList("AdminTermsDAO.getTermslist");
	}
	
	// �������
	public int updateTerms(AdminTermsVO admintermsvo) {
		return sqlSession.update("AdminTermsDAO.updateTerms", admintermsvo);
	}
	
	// ������
	public int insertTerms(AdminTermsVO admintermsvo) {
		return sqlSession.insert("AdminTermsDAO.insertTerms", admintermsvo);
	}
	
	// �������
	public int deleteTerms(AdminTermsVO admintermsvo) {
		return sqlSession.delete("AdminTermsDAO.deleteTerms", admintermsvo);
	}
	
	// �������
	public AdminTermsVO getTerms(AdminTermsVO admintermsvo) {
		return sqlSession.selectOne("AdminTermsDAO.getTerms", admintermsvo);
	}

}
