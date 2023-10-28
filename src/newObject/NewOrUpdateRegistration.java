package newObject;


import org.bson.Document;

public class NewOrUpdateRegistration {
    private int registrationID;
    private int studentRollNo;
    private int examID;
    private int adminID;

    public int getRegistrationID() {
        return registrationID;
    }

    public void setRegistrationID(int registrationID) {
        this.registrationID = registrationID;
    }

    public int getStudentRollNo() {
        return studentRollNo;
    }

    public void setStudentRollNo(int studentRollNo) {
        this.studentRollNo = studentRollNo;
    }

    public int getExamID() {
        return examID;
    }

    public void setExamID(int examID) {
        this.examID = examID;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public NewOrUpdateRegistration() {
    }

    public NewOrUpdateRegistration(int registrationID, int studentRollNo, int examID, int adminID) {
        this.registrationID = registrationID;
        this.studentRollNo = studentRollNo;
        this.examID = examID;
        this.adminID = adminID;
    }


    public Document toDocument() {
        return new Document("registrationID", registrationID)
                .append("studentRollNo", studentRollNo)
                .append("examID", examID)
                .append("adminID", adminID);
    }

    public static NewOrUpdateRegistration fromDocument(Document document) {
        int registrationID = document.getInteger("registrationID");
        int studentRollNo = document.getInteger("studentRollNo");
        int examID = document.getInteger("examID");
        int adminID = document.getInteger("adminID");

        NewOrUpdateRegistration registration = new NewOrUpdateRegistration();
        registration.setRegistrationID(registrationID);
        registration.setStudentRollNo(studentRollNo);
        registration.setExamID(examID);
        registration.setAdminID(adminID);

        return registration;
    }
}
