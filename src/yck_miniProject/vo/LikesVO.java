package yck_miniProject.vo;

public class LikesVO {
    private int memberNum;
    private int postNum;

    public LikesVO(int memberNum, int postNum) {
        this.memberNum = memberNum;
        this.postNum = postNum;
    }

    public int getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(int memberNum) {
        this.memberNum = memberNum;
    }

    public int getPostNum() {
        return postNum;
    }

    public void setPostNum(int postNum) {
        this.postNum = postNum;
    }
}
