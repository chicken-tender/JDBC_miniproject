package yck_miniProject.vo;

import java.util.Date;

public class MembersVO {
    private int memberNum;
    private int gradeNum;
    private String email;
    private String pwd;
    private String nickName;
    private String job;
    private int year;
    private Date regDate;
    private String pfImg;
    private String isWithDrawn;

    public MembersVO(int memberNum, int gradeNum, String email, String pwd, String nickName, String job, int year, Date regDate, String pfImg, String isWithDrawn) {
        this.memberNum = memberNum;
        this.gradeNum = gradeNum;
        this.email = email;
        this.pwd = pwd;
        this.nickName = nickName;
        this.job = job;
        this.year = year;
        this.regDate = regDate;
        this.pfImg = pfImg;
        this.isWithDrawn = isWithDrawn;
    }
    public MembersVO() {

    }

    public int getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(int memberNum) {
        this.memberNum = memberNum;
    }

    public int getGradeNum() {
        return gradeNum;
    }

    public void setGradeNum(int gradeNum) {
        this.gradeNum = gradeNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getPfImg() {
        return pfImg;
    }

    public void setPfImg(String pfImg) {
        this.pfImg = pfImg;
    }

    public String getIsWithDrawn() {
        return isWithDrawn;
    }

    public void setIsWithDrawn(String isWithDrawn) {
        this.isWithDrawn = isWithDrawn;
    }
}
