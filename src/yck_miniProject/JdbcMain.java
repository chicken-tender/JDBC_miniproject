package yck_miniProject;

import yck_miniProject.dao.AccountDAO;
import yck_miniProject.vo.MembersVO;

import java.util.List;

public class JdbcMain {
    public static void main(String[] args) {
        AccountDAO select = new AccountDAO();
        List<MembersVO> list = select.listMembers();
        select.printMembers(list);

    }
}
