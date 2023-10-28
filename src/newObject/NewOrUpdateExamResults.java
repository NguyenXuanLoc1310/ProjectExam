package newObject;

import org.bson.Document;

public class NewOrUpdateExamResults {
    private int StudentRollNo;
    private int ExamID;
    private int SubjectID;
    private int Score;

    public int getStudentRollNo() {
        return StudentRollNo;
    }

    public void setStudentRollNo(int StudentRollNo) {
        this.StudentRollNo = StudentRollNo;
    }

    public int getExamID() {
        return ExamID;
    }

    public void setExamID(int ExamID) {
        this.ExamID = ExamID;
    }

    public int getSubjectID() {
        return SubjectID;
    }

    public void setSubjectID(int SubjectID) {
        this.SubjectID = SubjectID;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }

    public NewOrUpdateExamResults(int StudentRollNo, int ExamID, int SubjectID, int Score) {
        this.StudentRollNo = StudentRollNo;
        this.ExamID = ExamID;
        this.SubjectID = SubjectID;
        this.Score = Score;
    }

    public NewOrUpdateExamResults() {
    }

    

    public Document toDocument() {
        return new Document("StudentRollNo", StudentRollNo)
                .append("ExamID", ExamID)
                .append("SubjectID", SubjectID)
                .append("Score", Score);
    }

    public static NewOrUpdateExamResults fromDocument(Document document) {
        int StudentRollNo = document.getInteger("StudentRollNo");
        int ExamID = document.getInteger("ExamID");
        int SubjectID = document.getInteger("SubjectID");
        int Score = document.getInteger("Score");

        NewOrUpdateExamResults results = new NewOrUpdateExamResults();
        results.setStudentRollNo(StudentRollNo);
        results.setExamID(ExamID);
        results.setSubjectID(SubjectID);
        results.setScore(Score);

        return results;
    }
}
