package forever.young.user.service;

import java.util.List;

import forever.young.user.vo.Board_noticeVO;

public interface Board_noticeService {
   //�Խù� �� ���� ��ȸ
   Board_noticeVO getBoard(Board_noticeVO vo) throws Exception;
   //�Խù� ��ȸ�� ����
   public void updateHit(int notice_serial);
   //�Խù� ����Ʈ ��ȸ
   public List<Board_noticeVO> getBoardList(Board_noticeVO vo) throws Exception;
}
