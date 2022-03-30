package forever.young.user.service;

import java.util.List;

import org.springframework.web.bind.support.SessionStatus;

import forever.young.user.vo.EmailSendVO;
import forever.young.user.vo.UserDetailVO;
import forever.young.user.vo.UserVO;
import forever.young.user.vo.User_shipping_listVO;

public interface UserService {

	// ȸ�� ���� ó��
	void register(UserVO userVo) throws Exception;

	// �α��� ó��
	UserVO login(UserVO userVo) throws Exception;

	// �α׾ƿ� ó��
	void logout(SessionStatus sessionStatus);

	// ȸ������ ���̵� �ߺ� üũ
	boolean idCheck(UserVO userVo);

	public UserVO getUser(UserVO userVo);

	// �̸��� �ߺ�Ȯ��
	boolean emailCheck(UserVO userVo);

	UserVO loginGetUser(UserVO userVo);

	/* ���̵� ���� �������� */
	UserVO getUserInfo(String user_id);

	/* �̸��� Ű �����ֱ� */
	EmailSendVO getSendEmailKey();

	void newPw(UserVO userVo);

	/* ���̵� ã�� */
	String idFind(UserVO userVo);

	/* ��й�ȣ ã�� */
	String pwFind(UserVO userVo);

	List<User_shipping_listVO> shipping(String user_id);

	public void insertUserDetail(UserDetailVO userVo);
}
