package yck_miniProject;

import yck_miniProject.dao.MembersDAO;
import yck_miniProject.vo.MembersVO;

import java.util.List;

public class JdbcMain {
    public static void main(String[] args) {
        MembersDAO select = new MembersDAO();
        List<MembersVO> list = select.listMembers();
        select.printMembers(list);

    }
}
