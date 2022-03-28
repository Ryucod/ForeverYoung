package forever.young.user.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forever.young.user.vo.Board_noticeVO;

@Repository
public class Board_noticeDAO {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   /* �Խñ� �󼼳��� ��������*/
   public Board_noticeVO getBoard(Board_noticeVO vo) throws Exception {
      
      return (Board_noticeVO) sqlSessionTemplate.selectOne("Board_noticeDAO.getBoard", vo);
   }
   /* �������� �Խñ� ��� ��������*/
   public List<Board_noticeVO> getBoardList(Board_noticeVO vo) {
     
      
      return sqlSessionTemplate.selectList("Board_noticeDAO.getBoardNoticeList", vo);
   }
   /* ��ȸ�� ������Ʈ */
   public void updateHit(int notice_serial) {
      
      
      sqlSessionTemplate.update("Board_noticeDAO.noticeHitUp", notice_serial);
   }
}