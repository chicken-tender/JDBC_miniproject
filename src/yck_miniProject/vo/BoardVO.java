package yck_miniProject.vo;

public class BoardVO {
    private int boardNum;
    private String boardName;

    public BoardVO(int boardNum, String boardName) {
        this.boardNum = boardNum;
        this.boardName = boardName;
    }

    public int getBoardNum() {
        return boardNum;
    }

    public void setBoardNum(int boardNum) {
        this.boardNum = boardNum;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }
}
