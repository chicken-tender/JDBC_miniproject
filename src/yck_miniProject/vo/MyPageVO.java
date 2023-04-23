package yck_miniProject.vo;

import java.util.Date;

public class MyPageVO {
    private String pfImg;
    private String gradeIconUrl;
    private Date regDate;
    private String nickName;
    private String email;
    private String job;
    private int year;
    private int myPostCount;
    private int myReplyCount;
    private String boardName;
    private String postTitle;
    private String postContent;
    private String replyContent;
    private Date writeDate;

    public MyPageVO(String pfImg, String gradeIconUrl, Date regDate, String nickName, String email, String job, int year, int myPostCount, int myReplyCount) {
        this.pfImg = pfImg;
        this.gradeIconUrl = gradeIconUrl;
        this.regDate = regDate;
        this.nickName = nickName;
        this.email = email;
        this.job = job;
        this.year = year;
        this.myPostCount = myPostCount;
        this.myReplyCount = myReplyCount;
    }
    public MyPageVO() {
    }



    public String getPfImg() {
        return pfImg;
    }

    public void setPfImg(String pfImg) {
        this.pfImg = pfImg;
    }

    public String getGradeIconUrl() {
        return gradeIconUrl;
    }

    public void setGradeIconUrl(String gradeIconUrl) {
        this.gradeIconUrl = gradeIconUrl;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMyPostCount() {
        return myPostCount;
    }

    public void setMyPostCount(int myPostCount) {
        this.myPostCount = myPostCount;
    }

    public int getMyReplyCount() {
        return myReplyCount;
    }

    public void setMyReplyCount(int myReplyCount) {
        this.myReplyCount = myReplyCount;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
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
}
