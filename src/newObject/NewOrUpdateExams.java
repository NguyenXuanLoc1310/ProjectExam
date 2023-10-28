package newObject;

import lombok.Getter;
import lombok.Setter;
import org.bson.Document;
@Getter
@Setter
public class NewOrUpdateExams {
    private int examID;
    private String examDate;
    private String startTime;
    private int examinerID;
    private int adminID;
    private int duration;
    private String room;

    public NewOrUpdateExams() {
    }
    private int subjectID;

    public int getExamID() {
        return examID;
    }

    public void setExamID(int examID) {
        this.examID = examID;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getExaminerID() {
        return examinerID;
    }

    public void setExaminerID(int examinerID) {
        this.examinerID = examinerID;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public NewOrUpdateExams(int examID, String examDate, String startTime, int examinerID, int adminID, int duration, String room, int subjectID) {
        this.examID = examID;
        this.examDate = examDate;
        this.startTime = startTime;
        this.examinerID = examinerID;
        this.adminID = adminID;
        this.duration = duration;
        this.room = room;
        this.subjectID = subjectID;
    }



    public Document toDocument() {
        return new Document("examID", examID)
                .append("examDate", examDate)
                .append("startTime", startTime)
                .append("examinerID", examinerID)
                .append("adminID", adminID)
                .append("duration", duration)
                .append("room", room)
                .append("subjectID", subjectID);
    }

    public static NewOrUpdateExams fromDocument(Document document) {
        int examID = document.getInteger("examID");
        String examDate = document.getString("examDate");
        String startTime = document.getString("startTime");
        int examinerID = document.getInteger("examinerID");
        int adminID = document.getInteger("adminID");
        int duration = document.getInteger("duration");
        String room = document.getString("room");
        int subjectID = document.getInteger("subjectID");

        NewOrUpdateExams exams = new NewOrUpdateExams();
        exams.setExamID(examID);
        exams.setExamDate(examDate);
        exams.setStartTime(startTime);
        exams.setExaminerID(examinerID);
        exams.setAdminID(adminID);
        exams.setDuration(duration);
        exams.setRoom(room);
        exams.setSubjectID(subjectID);

        return exams;
    }
}
