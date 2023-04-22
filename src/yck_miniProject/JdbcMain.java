package yck_miniProject;
import yck_miniProject.dao.AccountDAO;
import yck_miniProject.dao.MainDAO;
import yck_miniProject.vo.MembersVO;
import yck_miniProject.vo.TopWriterVO;
import java.util.List;

// 이 곳은 DAO가 정상적으로 작동하는지 테스트 하는 클래스 입니다.
public class JdbcMain {
    public static void main(String[] args) {

        MainDAO mdao = new MainDAO();
        List<TopWriterVO> t = mdao.getTopWriters();
        System.out.println(mdao.getTotalMemberCount());
    }
}
