package yck_miniProject.vo;

public class TopWriterVO {
    private String pfImg;
    private String nickname;
    private int count;

    public TopWriterVO(String pfImg, String nickname, int count) {
        this.pfImg = pfImg;
        this.nickname = nickname;
        this.count = count;
    }
    public TopWriterVO() {

    }
    public String getPfImg() {
        return pfImg;
    }

    public void setPfImg(String pfImg) {
        this.pfImg = pfImg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
