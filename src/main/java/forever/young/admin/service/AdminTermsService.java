package forever.young.admin.service;

import java.util.List;

import forever.young.admin.vo.AdminTermsVO;

public interface AdminTermsService {
	
	// �������Ʈ
	public List<AdminTermsVO> getTermslist();
	
	// �������
	public int updateTerms(AdminTermsVO admintermsvo);

	// ������
	public int insertTerms(AdminTermsVO admintermsvo);

	// �������
	public int deleteTerms(AdminTermsVO admintermsvo);
	
	// �������
	public AdminTermsVO getTerms(AdminTermsVO admintermsvo);


}
