package newObject;

import lombok.Getter;
import lombok.Setter;
import org.bson.Document;

    @Getter
    @Setter
public class NewOrUpdateSubjects {
    private int subjectID;
    private String subjectName;

    public NewOrUpdateSubjects(int subjectID, String subjectName) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
    }

    NewOrUpdateSubjects() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }



    public Document toDocument() {
        return new Document("subjectID", subjectID)
                .append("subjectName", subjectName);
    }
     public static NewOrUpdateSubjects fromDocument(Document document) {
        int subjectID = document.getInteger("subjectID");
        String subjectName = document.getString("subjectName");
        return new NewOrUpdateSubjects(subjectID, subjectName);
    }

    void setSubjectName(String nextLine) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setSubjectID(int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
