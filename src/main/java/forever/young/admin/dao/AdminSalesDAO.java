package forever.young.admin.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forever.young.admin.vo.AdminSalesVO;

@Repository
public class AdminSalesDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	// ���ø���
	public List<AdminSalesVO> getTodaySales(AdminSalesVO adminsalesvo) {
		return sqlSession.selectList("AdminSalesDAO.getTodaySales");
	}

	// �ϸ���
	public List<AdminSalesVO> getDaySales(AdminSalesVO adminsalesvo) {
		return sqlSession.selectList("AdminSalesDAO.getDaySales");
	}

	// ��ǰ�� ����
	public List<AdminSalesVO> getSales(AdminSalesVO adminsalesvo) {
		return sqlSession.selectList("AdminSalesDAO.getSales");
	}

	// ������
	public List<AdminSalesVO> getMonthSales(AdminSalesVO adminsalesvo) {
		return sqlSession.selectList("AdminSalesDAO.getMonthSales");
	}
	
}
