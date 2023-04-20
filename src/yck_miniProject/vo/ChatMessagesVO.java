package yck_miniProject.vo;

public class ChatMessagesVO {
    private int chatNum;
    private int mentor;
    private int mentee;

    public ChatMessagesVO(int chatNum, int mentor, int mentee) {
        this.chatNum = chatNum;
        this.mentor = mentor;
        this.mentee = mentee;
    }

    public int getChatNum() {
        return chatNum;
    }

    public void setChatNum(int chatNum) {
        this.chatNum = chatNum;
    }

    public int getMentor() {
        return mentor;
    }

    public void setMentor(int mentor) {
        this.mentor = mentor;
    }

    public int getMentee() {
        return mentee;
    }

    public void setMentee(int mentee) {
        this.mentee = mentee;
    }
}
