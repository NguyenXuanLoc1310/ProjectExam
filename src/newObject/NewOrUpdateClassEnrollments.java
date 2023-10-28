package newObject;

import org.bson.Document;
import java.util.Date;

public class NewOrUpdateClassEnrollments {

    public NewOrUpdateClassEnrollments(int enrollmentID, int studentRollNo, int classID) {
    }
    private int EnrollmentID;
    private int ClassID;
    private int StudentRollNo;
    private Date EnrollmentDate;

    public int getEnrollmentID() {
        return EnrollmentID;
    }

    public void setEnrollmentID(int EnrollmentID) {
        this.EnrollmentID = EnrollmentID;
    }

    public int getClassID() {
        return ClassID;
    }

    public void setClassID(int ClassID) {
        this.ClassID = ClassID;
    }

    public int getStudentRollNo() {
        return StudentRollNo;
    }

    public void setStudentRollNo(int StudentRollNo) {
        this.StudentRollNo = StudentRollNo;
    }

    public Date getEnrollmentDate() {
        return EnrollmentDate;
    }

    public void setEnrollmentDate(Date EnrollmentDate) {
        this.EnrollmentDate = EnrollmentDate;
    }

    

    public Document toDocument() {
        return new Document("EnrollmentID", EnrollmentID)
                .append("ClassID", ClassID)
                .append("StudentRollNo", StudentRollNo)
                .append("EnrollmentDate", EnrollmentDate);
    }

    public static NewOrUpdateClassEnrollments fromDocument(Document document) {
        int EnrollmentID = document.getInteger("EnrollmentID");
        int ClassID = document.getInteger("ClassID");
        int StudentRollNo = document.getInteger("StudentRollNo");
        Date EnrollmentDate = document.getDate("EnrollmentDate");

        NewOrUpdateClassEnrollments enrollment = new NewOrUpdateClassEnrollments(ClassID, StudentRollNo, ClassID);
        enrollment.setEnrollmentID(EnrollmentID);
        enrollment.setClassID(ClassID);
        enrollment.setStudentRollNo(StudentRollNo);
        enrollment.setEnrollmentDate(EnrollmentDate);

        return enrollment;
    }
}
