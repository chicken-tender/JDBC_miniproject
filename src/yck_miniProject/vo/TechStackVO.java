package yck_miniProject.vo;

public class TechStackVO {
    private int stackNum;
    private String stackIconUrl;
    private String stackName;

    public TechStackVO(int stackNum, String stackIconUrl, String stackName) {
    this.stackNum = stackNum;
    this.stackIconUrl = stackIconUrl;
    this.stackName = stackName;
    }

    public TechStackVO() {

    }

    public int getStackNum() {
        return stackNum;
    }

    public void setStackNum(int stackNum) {
        this.stackNum = stackNum;
    }

    public String getStackIconUrl() {
        return stackIconUrl;
    }

    public void setStackIconUrl(String stackIconUrl) {
        this.stackIconUrl = stackIconUrl;
    }

    public String getStackName() {
        return stackName;
    }

    public void setStackName(String stackName) {
        this.stackName = stackName;
    }
}


