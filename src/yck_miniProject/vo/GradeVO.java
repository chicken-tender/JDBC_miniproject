package yck_miniProject.vo;

public class GradeVO {
    private int gradeNum;
    private String gradeIconUrl;
    private String gradeName;

    public GradeVO(int gradeNum, String gradeIconUrl, String gradeName) {
        this.gradeNum = gradeNum;
        this.gradeIconUrl = gradeIconUrl;
        this.gradeName = gradeName;
    }

    public GradeVO() {

    }

    public int getGradeNum() {
        return gradeNum;
    }

    public void setGradeNum(int gradeNum) {
        this.gradeNum = gradeNum;
    }

    public String getGradeIconUrl() {
        return gradeIconUrl;
    }

    public void setGradeIconUrl(String gradeIconUrl) {
        this.gradeIconUrl = gradeIconUrl;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }
}

