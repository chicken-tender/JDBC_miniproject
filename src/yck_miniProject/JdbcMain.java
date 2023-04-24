package yck_miniProject;
import yck_miniProject.dao.AccountDAO;
import yck_miniProject.dao.BoardDAO;
import yck_miniProject.dao.MainDAO;
import yck_miniProject.vo.*;

import java.util.List;
import java.util.Scanner;

// 이 곳은 DAO가 정상적으로 작동하는지 테스트 하는 클래스 입니다.
public class JdbcMain {
    public static void main(String[] args) {

        MainDAO mdao = new MainDAO();
        List<TopWriterVO> t = mdao.getTopWriters();
        System.out.println(mdao.getTotalMemberCount());

        BoardDAO boardDAO = new BoardDAO();
        boardDAO.updateBestBoard();

        List<PostVO> list = boardDAO.generalPostList(1,  5);
        System.out.println("번호 | 제목 | 작성자 | 작성일 | 조회수 | 추천수");
        for (PostVO e : list ) {
            System.out.println(e.getPostNum() + " | " + e.getTitle() + " | " + e.getNickname()
                    + " | " + e.getWriteDate() + " | " + e.getViewCount() + " | " + e.getLikeCount());
        }
    }

    }


