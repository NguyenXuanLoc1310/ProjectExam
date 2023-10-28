package newObject;

import lombok.Getter;
import lombok.Setter;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;

@Getter
@Setter

public class NewOrUpdateStudents {
    @BsonId
  
    private int rollNo;
    private String name;
    private String gender;
    private String dateOfBirth;
    private String email;
    private String phoneNo;

    public NewOrUpdateStudents() {
    }

    public NewOrUpdateStudents(int rollNo, String name, String gender, String dateOfBirth, String email, String phoneNo) {
        this.rollNo = rollNo;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public Document toDocument() {
        return new Document("rollNo", rollNo)
                .append("name", name)
                .append("gender", gender)
                .append("dateOfBirth", dateOfBirth)
                .append("email", email)
                .append("phoneNo", phoneNo);
    }

    public static NewOrUpdateStudents fromDocument(Document document) {
        int rollNo = document.getInteger("rollNo");
        String name = document.getString("name");
        String gender = document.getString("gender");
        String dateOfBirth = document.getString("dateOfBirth");
        String email = document.getString("email");
        String phoneNo = document.getString("phoneNo");
        return new NewOrUpdateStudents(rollNo, name, gender, dateOfBirth, email, phoneNo);
    }

}
