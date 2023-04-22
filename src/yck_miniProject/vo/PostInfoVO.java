package yck_miniProject.vo;

public class PostInfoVO {
    private String title;
    private String nickName;
    private String pfImg;
    private int viewCount;
    private int commentCount;

    public PostInfoVO(String title, String nickName, String pfImg, int viewCount, int commentCount) {
        this.title = title;
        this.nickName = nickName;
        this.pfImg = pfImg;
        this.viewCount = viewCount;
        this.commentCount = commentCount;
    }
    public PostInfoVO() {

    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPfImg() {
        return pfImg;
    }

    public void setPfImg(String pfImg) {
        this.pfImg = pfImg;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
