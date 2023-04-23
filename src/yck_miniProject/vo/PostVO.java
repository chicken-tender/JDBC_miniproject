package yck_miniProject.vo;

import java.sql.Date;

public class PostVO {
    private int postNum;
    private int boardNum;
    private int memberNum;
    private String title;
    private String content;
    private int viewCount;
    private int likeCount;
    private Date writeDate;
    private String imgUrl;
    private String tag;
    private String nickname;
    private String boardName;
    private String pfImg;


    public PostVO(int postNum, int boardNum, int memberNum, String title, String content, int viewCount, int likeCount, Date writeDate, String imgUrl, String tag, String nickname,String boardName, String pfImg) {
        this.postNum = postNum;
        this.boardNum = boardNum;
        this.memberNum= memberNum;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.writeDate = writeDate;
        this.imgUrl = imgUrl;
        this.tag = tag;
        this.nickname = nickname;
        this.boardName = boardName;
        this.pfImg = pfImg;
    }

    public PostVO() {

    }

    public PostVO(String message) {
        this.content = message;
    }


    public int getPostNum() {
        return postNum;
    }

    public void setPostNum(int postNum) {
        this.postNum = postNum;
    }

    public int getBoardNum() {
        return boardNum;
    }

    public void setBoardNum(int boardNum) {
        this.boardNum = boardNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public Date getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Date writeDate) {
        this.writeDate = writeDate;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setMemberNum(int memberNum) {
        this.memberNum = memberNum;
    }

    public int getMemberNum() {
        return memberNum;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getPfImg() {
        return pfImg;
    }

    public void setPfImg(String pfImg) {
        this.pfImg = pfImg;
    }
}


