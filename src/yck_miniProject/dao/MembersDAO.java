package yck_miniProject.dao;

import yck_miniProject.util.Common;
import yck_miniProject.vo.MembersVO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MembersDAO {
    public List<MembersVO> listMembers() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<MembersVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT * FROM MEMBERS_TB";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int memberNum = rs.getInt("MEMBER_NUM_PK");
                int gradeNum = rs.getInt("GRADE_NUM_FK");
                String email = rs.getString("EMAIL");
                String pwd = rs.getString("PWD");
                String nickName = rs.getString("NICKNAME");
                String job = rs.getString("JOB");
                int year = rs.getInt("YEAR");
                Date regDate = rs.getDate("REG_DATE");
                String pfImg = rs.getString("PF_IMG");

                MembersVO vo = new MembersVO();
                vo.setMemberNum(memberNum);
                vo.setGradeNum(gradeNum);
                vo.setEmail(email);
                vo.setPwd(pwd);
                vo.setNickName(nickName);
                vo.setJob(job);
                vo.setYear(year);
                vo.setRegDate(regDate);
                vo.setPfImg(pfImg);

                list.add(vo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

