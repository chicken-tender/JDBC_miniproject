package yck_miniProject;
import yck_miniProject.dao.AccountDAO;
import yck_miniProject.dao.BoardDAO;
import yck_miniProject.dao.MainDAO;
import yck_miniProject.vo.MembersVO;
import yck_miniProject.vo.PostVO;
import yck_miniProject.vo.ReplyVO;
import yck_miniProject.vo.TopWriterVO;
import java.util.List;
import java.util.Scanner;

// 이 곳은 DAO가 정상적으로 작동하는지 테스트 하는 클래스 입니다.
public class JdbcMain {
    public static void main(String[] args) {

        MainDAO mdao = new MainDAO();
        List<TopWriterVO> t = mdao.getTopWriters();
        System.out.println(mdao.getTotalMemberCount());

        Scanner scanner = new Scanner(System.in);
        System.out.print("검색어를 입력하세요: ");
        String keyword = scanner.nextLine();
        System.out.print("검색 결과: ");
        BoardDAO dao = new BoardDAO();
        List<PostVO> result = dao.searchPosts(keyword, 1, 4); //'프로젝트'라고 검색해보기
        if (result.isEmpty()) {
            System.out.println("검색 결과가 없습니다.");
        } else {
            for (PostVO post : result) {
                System.out.println(post.getTitle() + " (" + post.getNickname() + ")");
            }
        }
//
//        BoardDAO bdao = new BoardDAO();
//        List<ReplyVO> postList = bdao.viewReply(14);
//            for (ReplyVO e : postList) {
//                System.out.println(e.getPfImg() + " | " + e.getNickname() +" | " + e.getReplyContent() + " | " + e.getWriteDate());
//            }
    }
}

