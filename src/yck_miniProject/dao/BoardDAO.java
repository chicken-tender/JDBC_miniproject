package yck_miniProject.dao;

import yck_miniProject.util.Common;
import yck_miniProject.vo.BoardVO;
import yck_miniProject.vo.PostVO;
import yck_miniProject.vo.ReplyVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BoardDAO {
    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;


    // ✨일반 게시판 글 목록 (한 페이지당 10개씩)
    public List<PostVO> generalPostList(int pageNum, int boardNum) {
        int numPerPage = 10; // 페이지 당 보여주는 항목 개수
        List<PostVO> list = new ArrayList<>();
        int startRow = (pageNum - 1) * numPerPage + 1;
        int endRow = pageNum * numPerPage;

        String sql = "SELECT P.POST_NUM_PK, P.TITLE, M.NICKNAME, P.WRITE_DATE, P.VIEW_COUNT, P.LIKE_COUNT " +
                "FROM ( " +
                "  SELECT POST_NUM_PK, TITLE, MEMBER_NUM_FK, WRITE_DATE, VIEW_COUNT, LIKE_COUNT, " +
                "  ROW_NUMBER() OVER (ORDER BY WRITE_DATE DESC) AS RN " +
                "  FROM POST_TB " +
                "  WHERE BOARD_NUM_FK = ? " +
                ") P " +
                "JOIN MEMBERS_TB M ON P.MEMBER_NUM_FK = M.MEMBER_NUM_PK " +
                "WHERE P.RN BETWEEN ? AND ? " +
                "ORDER BY WRITE_DATE DESC";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, boardNum);
            pstmt.setInt(2, startRow);
            pstmt.setInt(3, endRow);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                PostVO pv = new PostVO();
                pv.setPostNum(rs.getInt("POST_NUM_PK"));
                pv.setTitle(rs.getString("TITLE"));
                pv.setWriteDate(rs.getDate("WRITE_DATE"));
                pv.setNickname(rs.getString("NICKNAME"));
                pv.setViewCount(rs.getInt("VIEW_COUNT"));
                pv.setLikeCount(rs.getInt("LIKE_COUNT"));
                list.add(pv);
            }

            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    //✨ 베스트 게시판 글 목록 (추천수 100개 이상 게시물)
    public List<PostVO> bestPostList(int pageNum) {
        int numPerPage = 10;
        int startRow = (pageNum - 1) * numPerPage + 1;
        int endRow = pageNum * numPerPage;

        List<PostVO> list = new ArrayList<>();

        String sql = "SELECT P.POST_NUM_PK, P.TITLE, M.NICKNAME, P.WRITE_DATE, P.VIEW_COUNT, P.LIKE_COUNT " +
                "FROM ( " +
                "  SELECT POST_NUM_PK, TITLE, MEMBER_NUM_FK, WRITE_DATE, VIEW_COUNT, LIKE_COUNT, " +
                "  ROW_NUMBER() OVER (ORDER BY WRITE_DATE DESC) AS RN " +
                "  FROM POST_TB " +
                "  WHERE BOARD_NUM_FK = 2 AND LIKE_COUNT >= 100 " +
                ") P " +
                "JOIN MEMBERS_TB M ON P.MEMBER_NUM_FK = M.MEMBER_NUM_PK " +
                "WHERE P.RN BETWEEN ? AND ? " +
                "ORDER BY WRITE_DATE DESC";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, startRow);
            pstmt.setInt(2, endRow);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                PostVO pv = new PostVO();
                pv.setPostNum(rs.getInt("POST_NUM_PK"));
                pv.setTitle(rs.getString("TITLE"));
                pv.setWriteDate(rs.getDate("WRITE_DATE"));
                pv.setNickname(rs.getString("NICKNAME"));
                pv.setViewCount(rs.getInt("VIEW_COUNT"));
                pv.setLikeCount(rs.getInt("LIKE_COUNT"));
                list.add(pv);
            }

            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    // ✨정보공유 게시판 글 목록 (추천수 100개 이하)
    public List<PostVO> infoSharingPostList(int pageNum) {
        int numPerPage = 10;
        int startRow = (pageNum - 1) * numPerPage + 1;
        int endRow = pageNum * numPerPage;

        List<PostVO> list = new ArrayList<>();

        String sql = "SELECT P.POST_NUM_PK, P.TITLE, M.NICKNAME, P.WRITE_DATE, P.VIEW_COUNT, P.LIKE_COUNT " +
                "FROM ( " +
                "  SELECT POST_NUM_PK, TITLE, MEMBER_NUM_FK, WRITE_DATE, VIEW_COUNT, LIKE_COUNT, " +
                "  ROW_NUMBER() OVER (ORDER BY WRITE_DATE DESC) AS RN " +
                "  FROM POST_TB " +
                "  WHERE BOARD_NUM_FK = 2 AND LIKE_COUNT < 100 " +
                ") P " +
                "JOIN MEMBERS_TB M ON P.MEMBER_NUM_FK = M.MEMBER_NUM_PK " +
                "WHERE P.RN BETWEEN ? AND ? " +
                "ORDER BY WRITE_DATE DESC";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, startRow);
            pstmt.setInt(2, endRow);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                PostVO pv = new PostVO();
                pv.setPostNum(rs.getInt("POST_NUM_PK"));
                pv.setTitle(rs.getString("TITLE"));
                pv.setWriteDate(rs.getDate("WRITE_DATE"));
                pv.setNickname(rs.getString("NICKNAME"));
                pv.setViewCount(rs.getInt("VIEW_COUNT"));
                pv.setLikeCount(rs.getInt("LIKE_COUNT"));
                list.add(pv);
            }

            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    // ✨포트폴리오 게시판 글 목록 (한 페이지당 6개씩)
    public List<PostVO> portfolioList(int pageNum) {
        int numPerPage = 6;
        List<PostVO> list = new ArrayList<>();
        int startRow = (pageNum - 1) * numPerPage + 1;
        int endRow = pageNum * numPerPage;

        String sql = "SELECT POST_NUM_PK, TITLE, IMG_URL " +
                "FROM ( " +
                "   SELECT POST_NUM_PK, TITLE, IMG_URL, WRITE_DATE, ROW_NUMBER() OVER(ORDER BY WRITE_DATE DESC) AS RNUM " +
                "   FROM POST_TB " +
                "   WHERE BOARD_NUM_FK = 4 " +
                ") " +
                "WHERE RNUM BETWEEN ? AND ? " +
                "ORDER BY WRITE_DATE DESC";


        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);


            pstmt.setInt(1, startRow);
            pstmt.setInt(2, endRow);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                PostVO pv = new PostVO();
                pv.setPostNum(rs.getInt("POST_NUM_PK"));
                pv.setTitle(rs.getString("TITLE"));
                pv.setImgUrl(rs.getString("IMG_URL"));
                list.add(pv);
            }

            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    // ✨해당 게시판 게시글 검색
    public List<PostVO> searchPosts(String keyword, int pageNum, int boardNum) {
        int numPerPage = 10;
        List<PostVO> result = new ArrayList<>();
        int startRow = (pageNum - 1) * numPerPage + 1;
        int endRow = pageNum * numPerPage;

        List<PostVO> list = new ArrayList<>();
        String sql = "SELECT P.POST_NUM_PK, P.TITLE, M.NICKNAME, P.VIEW_COUNT, P.LIKE_COUNT, P.WRITE_DATE " +
                "FROM POST_TB P " +
                "JOIN MEMBERS_TB M ON P.MEMBER_NUM_FK = M.MEMBER_NUM_PK " +
                "JOIN BOARD_TB B ON P.BOARD_NUM_FK = B.BOARD_NUM_PK " +
                "WHERE (P.TITLE LIKE ? OR P.CONTENT LIKE ? OR P.TAG LIKE ?) AND P.BOARD_NUM_FK = ? " +
                "ORDER BY P.POST_NUM_PK DESC";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            pstmt.setString(3, "%" + keyword + "%");
            pstmt.setInt(4, boardNum);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                PostVO pv = new PostVO();

                pv.setPostNum(rs.getInt("POST_NUM_PK"));
                pv.setTitle(rs.getString("TITLE"));
                pv.setNickname(rs.getString("NICKNAME"));
                pv.setViewCount(rs.getInt("VIEW_COUNT"));
                pv.setLikeCount(rs.getInt("LIKE_COUNT"));
                pv.setWriteDate(rs.getDate("WRITE_DATE"));

                list.add(pv);
            }
            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    // ✨상세글 보기 (게시판 이름, 제목, 프로필사진, 작성자닉네임, 내용, 태그, 이미지, 작성날짜, 조회수, 추천수)
    public List<PostVO> viewPostDetail(int boardNum, int postNum) {
        String sql = "SELECT B.BOARD_NAME, P.TITLE, M.PF_IMG, M.NICKNAME, P.CONTENT, P.TAG, P.IMG_URL, P.WRITE_DATE, P.VIEW_COUNT, P.LIKE_COUNT " +
                "FROM POST_TB P " +
                "JOIN BOARD_TB B ON P.BOARD_NUM_FK = B.BOARD_NUM_PK " +
                "JOIN MEMBERS_TB M ON P.MEMBER_NUM_FK = M.MEMBER_NUM_PK " +
                "WHERE P.BOARD_NUM_FK = ? AND P.POST_NUM_PK = ? " +
                "ORDER BY POST_NUM_PK DESC";

        List<PostVO> list = new ArrayList<PostVO>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardNum);
            pstmt.setInt(2, postNum);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                PostVO post = new PostVO();
                post.setBoardName(rs.getString("BOARD_NAME"));
                post.setTitle(rs.getString("TITLE"));
                post.setNickname(rs.getString("NICKNAME"));
                post.setImgUrl(rs.getString("PF_IMG"));
                post.setContent(rs.getString("CONTENT"));
                post.setTag(rs.getString("TAG"));
                post.setImgUrl(rs.getString("IMG_URL"));
                post.setWriteDate(rs.getDate("WRITE_DATE"));
                post.setViewCount(rs.getInt("VIEW_COUNT"));
                post.setLikeCount(rs.getInt("LIKE_COUNT"));
                list.add(post);
            }

            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    // ✨ 게시글 작성
    public void writePost(PostVO post) {
        String sql = "INSERT INTO POST_TB (TITLE, CONTENT, TAG, IMG_URL, VIEW_COUNT, LIKE_COUNT, WRITE_DATE, BOARD_NUM_FK) " +
                "VALUES (?, ?, ?, ?, 0, 0, SYSDATE, (SELECT BOARD_NUM FROM BOARD_TB WHERE BOARD_NAME = ?))";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getContent());
            pstmt.setString(3, post.getTag());
            pstmt.setString(4, post.getImgUrl());
            pstmt.setString(5, post.getBoardName());
            pstmt.executeUpdate();

            Common.close(pstmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    // ✨ 게시글 수정
    public void updatePost(PostVO post) {
        String sql = "UPDATE POST_TB SET TITLE = ?, CONTENT = ?, TAG = ?, IMG_URL = ? " +
                "WHERE POST_NUM_PK = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getContent());
            pstmt.setString(3, post.getTag());
            pstmt.setString(4, post.getImgUrl());
            pstmt.setInt(5, post.getPostNum());
            pstmt.executeUpdate();

            Common.close(pstmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // ✨ 게시글 삭제
    public void deletePost(int postNum) {
        String sql = "DELETE FROM POST_TB WHERE POST_NUM_PK = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, postNum);
            pstmt.executeUpdate();

            Common.close(pstmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // ✨게시글 조회수 증가
    public int increaseViews(int postNum) {
        String sql = "UPDATE POST_TB SET VIEW_COUNT = VIEW_COUNT + 1 WHERE POST_NUM_PK = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        int result = 0;
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, postNum);
            result = pstmt.executeUpdate();

            Common.close(pstmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    // ✨게시글 추천수 증가
    public int increaseLikes(int postNum) {
        String sql = "UPDATE POST_TB SET LIKE_COUNT = LIKE_COUNT + 1 WHERE POST_NUM_PK = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        int result = 0;
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, postNum);
            result = pstmt.executeUpdate();

            Common.close(pstmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    // ✨댓글 보기(프로필이미지, 댓글작성자 닉네임, 내용, 작성날짜)
    public List<ReplyVO> viewReply(int postNum, int startIndex, int endIndex) {
        String sql = "SELECT M.PF_IMG, M.NICKNAME, R.REPLY_CONTENT, R.WRITE_DATE " +
                "FROM REPLY_TB R " +
                "JOIN MEMBERS_TB M ON R.MEMBER_NUM_FK = M.MEMBER_NUM_PK " +
                "WHERE R.POST_NUM_FK = ?" +
                "AND R.REPLY_NUM_PK BETWEEN ? AND ? " +
                "ORDER BY R.REPLY_NUM_PK ASC";

        List<ReplyVO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, postNum);
            pstmt.setInt(2, startIndex);
            pstmt.setInt(3, endIndex);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ReplyVO rv = new ReplyVO();
                rv.setPfImg(rs.getString("PF_IMG"));
                rv.setNickname(rs.getString("NICKNAME"));
                rv.setReplyContent(rs.getString("REPLY_CONTENT"));
                rv.setWriteDate(rs.getDate("WRITE_DATE"));
                list.add(rv);
            }

            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    // ✨댓글 작성
    public void insertReply(int postNum, int memberNum, String content) {
        String sql = "INSERT INTO REPLY_TB (POST_NUM_FK, MEMBER_NUM_FK, REPLY_CONTENT, WRITE_DATE) " +
                "VALUES (?, ?, ?, SYSDATE)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, postNum);
            pstmt.setInt(2, memberNum);
            pstmt.setString(3, content);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // ✨댓글 수정
    public void updateReply(int replyNum, String content) {
        String sql = "UPDATE REPLY_TB SET REPLY_CONTENT = ? WHERE REPLY_NUM_PK = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, content);
            pstmt.setInt(2, replyNum);
            pstmt.executeUpdate();

            Common.close(pstmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // ✨댓글 삭제
    public void deleteReply(int replyNum) {
        String sql = "DELETE FROM REPLY_TB WHERE REPLY_NUM_PK = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;


        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, replyNum);
            pstmt.executeUpdate();

            Common.close(pstmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // ✨게시판 목록 표시
    public List<BoardVO> boardList() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<BoardVO> boardList = new ArrayList<>();

        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT BOARD_NAME FROM BOARD_TB";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String boardName = rs.getString("BOARD_NAME");

                BoardVO vo = new BoardVO(boardName);
                boardList.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return boardList;

    }

}




