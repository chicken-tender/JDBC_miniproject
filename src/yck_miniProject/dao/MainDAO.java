package yck_miniProject.dao;

import yck_miniProject.util.Common;
import yck_miniProject.vo.TopWriterVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;

    // ✨글을 많이 작성한 상위 5명의 회원정보 get
    public List<TopWriterVO> getTopWriters() {
        List<TopWriterVO> list = new ArrayList<>();

        try {
            conn = Common.getConnection();
            String sql = "SELECT * FROM (" +
                    "  SELECT PF_IMG, NICKNAME, COUNT(POST_NUM_PK) AS c" +
                    "  FROM MEMBERS_TB m JOIN POST_TB p" +
                    "  ON m.MEMBER_NUM_PK = p.MEMBER_NUM_FK" +
                    "  GROUP BY PF_IMG, NICKNAME" +
                    "  ORDER BY c DESC)" +
                    "  WHERE ROWNUM <= 5";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                String nickName = rs.getString("NICKNAME");
                String pfImg = rs.getString("PF_IMG");
                int count = rs.getInt("c");

                TopWriterVO vo = new TopWriterVO();
                vo.setNickname(nickName);
                vo.setPfImg(pfImg);
                vo.setCount(count);
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
    // ✨전체 회원 수 get
    public int getTotalMemberCount() {
        int count = 0;
        try {
            conn = Common.getConnection();
            String sql = "SELECT COUNT(MEMBER_NUM_PK) AS c FROM MEMBERS_TB";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                count = rs.getInt("c");
            }

            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}
