package forever.young.user.dao;

import java.util.List;

import forever.young.user.vo.EmailSendVO;
import forever.young.user.vo.UserDetailVO;
import forever.young.user.vo.UserVO;
import forever.young.user.vo.User_shipping_listVO;

public interface UserDAO {
	
	//ȸ������
	void register(UserVO userVo) throws Exception;
	//�α���
	UserVO login(UserVO userVo) throws Exception;
	
	UserVO getUser(UserVO userVo) ;
	
	//�̸��� üũȮ��
	UserVO checkEmail(UserVO userVo);
	
	//ȸ������
	void updateUser(UserVO userVo);
	
	//ȸ������
	void deleteUser(UserVO userVo);
	
	 public void newPw(UserVO user);
	 public EmailSendVO getSendEmailKey();
	 public UserVO getUserInfo(String user_id);
	 public String pwFind(UserVO user);
	 public String idFind(UserVO user);
	 public List<User_shipping_listVO> shipping(String user_id);
	 public void insertUserDetail(UserDetailVO user);
	 
	 public List<UserVO> getTotalUser(UserVO userVo);
}
