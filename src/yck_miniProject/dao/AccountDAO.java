package yck_miniProject.dao;

import yck_miniProject.util.Common;
import yck_miniProject.vo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;

    // 🔑(마이페이지) 회원 정보 +(등급 아이콘, 총 게시글 수, 총 댓글 수)
    public List<MyPageVO> getMemberInfoByNumber(int memberNumber) {
        List<MyPageVO> list = new ArrayList<>();

        String sql = "SELECT m.PF_IMG, g.GRADE_ICON_URL, m.REG_DATE, m.NICKNAME, m.EMAIL, m.JOB, m.YEAR," +
                " (SELECT COUNT(p.MEMBER_NUM_FK) FROM POST_TB p WHERE p.MEMBER_NUM_FK = ?) AS mpc," +
                " (SELECT COUNT(r.MEMBER_NUM_FK) FROM REPLY_TB r WHERE r.MEMBER_NUM_FK = ?) AS mrc" +
                " FROM MEMBERS_TB m" +
                " JOIN GRADE_TB g ON m.GRADE_NUM_FK = g.GRADE_NUM_PK" +
                " WHERE m.MEMBER_NUM_PK = ?";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, memberNumber);
            pstmt.setInt(2, memberNumber);
            pstmt.setInt(3, memberNumber);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String pfImg = rs.getString("PF_IMG");
                String gradeIconUrl = rs.getString("GRADE_ICON_URL");
                Date regDate = rs.getDate("REG_DATE");
                String nickName = rs.getString("NICKNAME");
                String email = rs.getString("EMAIL");
                String job = rs.getString("JOB");
                int year = rs.getInt("YEAR");
                int myPostCount = rs.getInt("mpc");
                int myReplyCount = rs.getInt("mrc");

                MyPageVO vo = new MyPageVO();
                vo.setPfImg(pfImg);
                vo.setGradeIconUrl(gradeIconUrl);
                vo.setRegDate(regDate);
                vo.setNickName(nickName);
                vo.setEmail(email);
                vo.setJob(job);
                vo.setYear(year);
                vo.setMyPostCount(myPostCount);
                vo.setMyReplyCount(myReplyCount);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 🔑(마이페이지) 회원 기술 스택
    public List<TechStackVO> getMemberTechStackByNumber(int memberNumber) {
        List<TechStackVO> list = new ArrayList<>();

        String sql = "SELECT ts.STACK_ICON_URL" +
                " FROM MEMBER_TS_TB mts JOIN TECH_STACK_TB ts" +
                " ON ts.STACK_NUM_PK = mts.STACK_NUM_FK" +
                " WHERE MEMBER_NUM_FK = ?";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, memberNumber);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String stackIconUrl = rs.getString("STACK_ICON_URL");

                TechStackVO vo = new TechStackVO();
                vo.setStackIconUrl(stackIconUrl);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 🔑(마이페이지) 회원의 최근 게시글 5개 (카테고리, 제목, 본문, 날짜)
    public List<MyPageVO> getMemberLatestPosts(int memberNumber) {
        List<MyPageVO> list = new ArrayList<>();
        String sql = "SELECT *" +
                " FROM (" +
                " SELECT p.TITLE, p.CONTENT, b.BOARD_NAME, p.WRITE_DATE " +
                " FROM POST_TB p " +
                " JOIN BOARD_TB b ON p.BOARD_NUM_FK = b.BOARD_NUM_PK " +
                " JOIN MEMBERS_TB m ON p.MEMBER_NUM_FK = m.MEMBER_NUM_PK " +
                " WHERE m.MEMBER_NUM_PK = ? " +
                " ORDER BY p.WRITE_DATE DESC) " +
                " WHERE ROWNUM <=5";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, memberNumber);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                MyPageVO vo = new MyPageVO();
                vo.setPostTitle(rs.getString("TITLE"));
                vo.setPostContent(rs.getString("CONTENT"));
                vo.setBoardName(rs.getString("BOARD_NAME"));
                vo.setWriteDate(rs.getDate("WRITE_DATE"));

                list.add(vo);
            }
            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 🔑(마이페이지) 회원의 최근 댓글 5개 (카테고리, 댓글내용, 게시글 제목, 날짜)
    public List<MyPageVO> getMemberLatestReplies(int memberNumber) {
        List<MyPageVO> list = new ArrayList<>();
        String sql = "SELECT *" +
                " FROM (" +
                " SELECT r.REPLY_CONTENT, p.TITLE, b.BOARD_NAME, p.WRITE_DATE" +
                " FROM REPLY_TB r" +
                " JOIN POST_TB p ON r.POST_NUM_FK = p.POST_NUM_PK" +
                " JOIN BOARD_TB b ON p.BOARD_NUM_FK = b.BOARD_NUM_PK" +
                " JOIN MEMBERS_TB m ON p.MEMBER_NUM_FK = m.MEMBER_NUM_PK" +
                " WHERE m.MEMBER_NUM_PK = ?" +
                " ORDER BY p.WRITE_DATE DESC" +
                ")" +
                " WHERE ROWNUM <=5";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, memberNumber);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                MyPageVO vo = new MyPageVO();
                vo.setReplyContent(rs.getString("REPLY_CONTENT"));
                vo.setPostTitle(rs.getString("TITLE"));
                vo.setBoardName(rs.getString("BOARD_NAME"));
                vo.setWriteDate(rs.getDate("WRITE_DATE"));
                list.add(vo);
            }
            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 🔒(마이페이지) 회원정보 기본 (프로필사진, 가입일, 닉네임, 이메일, 직업, 연차)
    public List<MembersVO> getMemberInfoBasicByNumber(int memberNumber) {
        List<MembersVO> list = new ArrayList<>();

        String sql = "SELECT m.PF_IMG, m.REG_DATE, m.NICKNAME, m.EMAIL, m.JOB, m.YEAR" +
                " FROM MEMBERS_TB m" +
                " JOIN GRADE_TB g ON m.GRADE_NUM_FK = g.GRADE_NUM_PK" +
                " WHERE m.MEMBER_NUM_PK = ?";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, memberNumber);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String pfImg = rs.getString("PF_IMG");
                Date regDate = rs.getDate("REG_DATE");
                String nickName = rs.getString("NICKNAME");
                String email = rs.getString("EMAIL");
                String job = rs.getString("JOB");
                int year = rs.getInt("YEAR");

                MembersVO vo = new MembersVO();
                vo.setPfImg(pfImg);
                vo.setRegDate(regDate);
                vo.setNickName(nickName);
                vo.setEmail(email);
                vo.setJob(job);
                vo.setYear(year);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 🔒(마이페이지) 회원 등급 아이콘
    public String getMemberGradeIcon(int memberNumber) {
        String gradeIcon = null;
        String sql = "SELECT g.GRADE_ICON_URL" +
                " FROM GRADE_TB g JOIN MEMBERS_TB m" +
                " ON g.GRADE_NUM_PK = m.GRADE_NUM_FK" +
                " WHERE m.MEMBER_NUM_PK = ?";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, memberNumber);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                gradeIcon = rs.getString("GRADE_ICON_URL");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return gradeIcon;
    }

    // 🔒(마이페이지) 회원의 전체 게시글 수
    public int getMemberPostCount(int memberNumber) {
        int count = 0;
        String sql = "SELECT COUNT(p.MEMBER_NUM_FK) AS mpn FROM POST_TB p WHERE p.MEMBER_NUM_FK = ?";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, memberNumber);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                count = rs.getInt("mpn");
            }
            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    // 🔒(마이페이지) 회원의 전체 댓글 수
    public int getMemberReplyCount(int memberNumber) {
        int count = 0;
        String sql = "SELECT COUNT(r.MEMBER_NUM_FK) AS mrc FROM REPLY_TB r WHERE r.MEMBER_NUM_FK = ?";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, memberNumber);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                count = rs.getInt("mrc");
            }
            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    // 🔑(마이페이지 > 내 게시글 관리) 회원의 모든 게시글
    public List<MyPageVO> getMemberAllPosts(int memberNumber) {
        List<MyPageVO> list = new ArrayList<>();
        String sql = "SELECT p.TITLE, p.CONTENT, b.BOARD_NAME, p.WRITE_DATE" +
                " FROM POST_TB p" +
                " JOIN BOARD_TB b ON p.BOARD_NUM_FK = b.BOARD_NUM_PK" +
                " JOIN MEMBERS_TB m ON p.MEMBER_NUM_FK = m.MEMBER_NUM_PK" +
                " WHERE m.MEMBER_NUM_PK = ? " +
                " ORDER BY p.WRITE_DATE DESC";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, memberNumber);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                MyPageVO vo = new MyPageVO();
                vo.setPostTitle(rs.getString("TITLE"));
                vo.setPostContent(rs.getString("CONTENT"));
                vo.setBoardName(rs.getString("BOARD_NAME"));
                vo.setWriteDate(rs.getDate("WRITE_DATE"));
                list.add(vo);
            }
            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 🔑(마이페이지 > 내 댓글 관리) 회원의 모든 댓글
    public List<MyPageVO> getMemberAllReplies(int memberNumber) {
        List<MyPageVO> list = new ArrayList<>();
        String sql = "SELECT r.REPLY_CONTENT, p.TITLE, b.BOARD_NAME, p.WRITE_DATE" +
                " FROM REPLY_TB r" +
                " JOIN POST_TB p ON r.POST_NUM_FK = p.POST_NUM_PK" +
                " JOIN BOARD_TB b ON p.BOARD_NUM_FK = b.BOARD_NUM_PK" +
                " JOIN MEMBERS_TB m ON p.MEMBER_NUM_FK = m.MEMBER_NUM_PK" +
                " WHERE m.MEMBER_NUM_PK = ?" +
                " ORDER BY p.WRITE_DATE DESC";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, memberNumber);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                MyPageVO vo = new MyPageVO();
                vo.setReplyContent(rs.getString("REPLY_CONTENT"));
                vo.setPostTitle(rs.getString("TITLE"));
                vo.setBoardName(rs.getString("BOARD_NAME"));
                vo.setWriteDate(rs.getDate("WRITE_DATE"));
                list.add(vo);
            }
            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    // 👤(회원가입) 기본 정보 저장
    public void createMember(int gradeNumber, String email, String password, String nickName, String job, int year, String pfImg) {
        String sql = "INSERT INTO MEMBERS_TB (MEMBER_NUM_PK, GRADE_NUM_FK, EMAIL, PWD, NICKNAME, JOB, YEAR, PF_IMG)" +
                " VALUES (seq_MEMBER_NUM.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, gradeNumber);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setString(4, nickName);
            pstmt.setString(5, job);
            pstmt.setInt(6, year);
            pstmt.setString(7, pfImg);
            pstmt.executeUpdate();

            Common.close(pstmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 👤(회원가입) 회원의 기술 스택 저장
    public void createMemberTechStack(int memberNumber, int stackNumber) {
        String sql = "INSERT INTO MEMBER_TS_TB (MEMBER_NUM_FK, STACK_NUM_FK) VALUES (?, ?)";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, memberNumber);
            pstmt.setInt(2, stackNumber);
            pstmt.executeUpdate();

            Common.close(pstmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔐회원 정보 read (등급 아이콘 & 기술 스택 제외)
    public List<MembersVO> readMemberInfoByNumber(int memberNumber) {
        List<MembersVO> list = new ArrayList<>();
        String sql = "SELECT m.PF_IMG, m.REG_DATE, m.EMAIL, m.PWD, m.NICKNAME, m.JOB, m.YEAR" +
                " FROM MEMBERS_TB m" +
                " WHERE m.MEMBER_NUM_PK = ?";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, memberNumber);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                MembersVO vo = new MembersVO();
                vo.setPfImg(rs.getString("PF_IMG"));
                vo.setRegDate(rs.getDate("REG_DATE"));
                vo.setEmail(rs.getString("EMAIL"));
                vo.setPwd(rs.getString("PWD"));
                vo.setNickName(rs.getString("NICKNAME"));
                vo.setJob(rs.getString("JOB"));
                vo.setYear(rs.getInt("YEAR"));
                list.add(vo);
            }
            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 🔐회원 정보 변경 : 이메일
    public void updateMemberEmail(String memberEmail, int memberNumber) {
        String sql = "UPDATE MEMBERS_TB SET EMAIL = ?" +
                " WHERE MEMBER_NUM_PK = ?";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberEmail);
            pstmt.setInt(2, memberNumber);
            pstmt.execute();

            Common.close(pstmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔐회원 정보 변경 : 비밀번호
    public void updateMemberPassword(String memberPassword, int memberNumber) {
        String sql = "UPDATE MEMBERS_TB SET PWD = ?" +
                " WHERE MEMBER_NUM_PK = ?";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberPassword);
            pstmt.setInt(2, memberNumber);
            pstmt.execute();

            Common.close(pstmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔐회원 정보 변경 : 닉네임
    public void updateMemberNickname(String memberNickname, int memberNumber) {
        String sql = "UPDATE MEMBERS_TB SET NICKNAME = ?" +
                " WHERE MEMBER_NUM_PK = ?";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberNickname);
            pstmt.setInt(2, memberNumber);
            pstmt.execute();

            Common.close(pstmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔐회원 정보 변경 : 직업
    public void updateMemberJob(String memberJob, int memberNumber) {
        String sql = "UPDATE MEMBERS_TB SET JOB = ?" +
                " WHERE MEMBER_NUM_PK = ?";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberJob);
            pstmt.setInt(2, memberNumber);
            pstmt.execute();

            Common.close(pstmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔐회원 정보 변경 : 연차
    public void updateMemberYear(int memberYear, int memberNumber) {
        String sql = "UPDATE MEMBERS_TB SET YEAR = ?" +
                " WHERE MEMBER_NUM_PK = ?";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, memberYear);
            pstmt.setInt(2, memberNumber);
            pstmt.execute();

            Common.close(pstmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔐회원 정보 변경 : 기술 스택 삭제
    public void deteleMemberTechStack(int memberNumber, int memberTechStackNumber) {
        String sql = "DELETE FROM MEMBER_TS_TB WHERE MEMBER_NUM_FK = ? AND STACK_NUM_FK = ?";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, memberNumber);
            pstmt.setInt(2, memberTechStackNumber);
            pstmt.executeUpdate();

            Common.close(pstmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔐회원 탈퇴시 isWithdrawn 변경
    public void updateMemberIsWithdrawn(String memberIsWithdrawn, int memberNumber) {
        String sql = "UPDATE MEMBERS_TB SET IS_WITHDRAWN = ? WHERE MEMBER_NUM_PK = ?";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberIsWithdrawn);
            pstmt.setInt(2, memberNumber);
            pstmt.execute();

            Common.close(pstmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}