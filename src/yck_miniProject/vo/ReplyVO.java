package yck_miniProject.vo;

import java.sql.Date;

public class ReplyVO {
    private int replyNum;
    private int postNum;
    private int memberNum;
    private String replyContent;
    private Date writeDate;
    private String nickname;
    private String pfImg;

    public ReplyVO(int replyNum, int postNum, int memberNum, String replyContent, Date writeDate, String nickname, String pfImg) {
        this.replyNum = replyNum;
        this.postNum = postNum;
        this.memberNum = memberNum;
        this.replyContent = replyContent;
        this.writeDate = writeDate;
        this.nickname = nickname;
        this.pfImg=pfImg;
    }

    public ReplyVO() {

    }

    public int getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(int replyNum) {
        this.replyNum = replyNum;
    }

    public int getPostNum() {
        return postNum;
    }

    public void setPostNum(int postNum) {
        this.postNum = postNum;
    }

    public int getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(int memberNum) {
        this.memberNum = memberNum;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Date getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Date writeDate) {
        this.writeDate = writeDate;
    }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPfImg() {
        return pfImg;
    }

    public void setPfImg(String pfImg) {
        this.pfImg = pfImg;
    }
}
