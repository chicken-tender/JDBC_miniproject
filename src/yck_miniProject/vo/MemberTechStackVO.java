package yck_miniProject.vo;

public class MemberTechStackVO {
    private int memberNum;
    private int stackNum;

    public MemberTechStackVO(int memberNum, int stackNum) {
        this.memberNum = memberNum;
        this.stackNum = stackNum;
    }

    public MemberTechStackVO() {

    }

    public int getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(int memberNum) {
        this.memberNum = memberNum;
    }

    public int getStackNum() {
        return stackNum;
    }

    public void setStackNum(int stackNum) {
        this.stackNum = stackNum;
    }
}
