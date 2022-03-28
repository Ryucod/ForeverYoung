package forever.young.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.SessionStatus;

import forever.young.user.dao.UserDAO;
import forever.young.user.vo.UserVO;

@Service
public class InfoModifyServiceImpl implements InfoModifyService {
	@Autowired
	private UserDAO userDAO;
	
	//ȸ������ ���� ������ �Է°� ä���
	@Override
	public UserVO getUserData(UserVO userVo) {
		return userDAO.getUser(userVo);
	}
	//ȸ������ ����
	@Override
	public void updateUser(UserVO userVo) {
		userDAO.updateUser(userVo);

	}
	//ȸ�� Ż��
	@Override
	public void deleteUser(UserVO userVo) {
		userDAO.deleteUser(userVo);
		
		
		
	}

}
