package newObject;

import org.bson.Document;

public class NewOrUpdateExaminers {
    private int examinerID;
    private String name;
    private String contact;

    public int getExaminerID() {
        return examinerID;
    }

    public void setExaminerID(int examinerID) {
        this.examinerID = examinerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public NewOrUpdateExaminers() {
    }

    public NewOrUpdateExaminers(int examinerID, String name, String contact) {
        this.examinerID = examinerID;
        this.name = name;
        this.contact = contact;
    }

    

    public Document toDocument() {
        return new Document("examinerID", examinerID)
                .append("name", name)
                .append("contact", contact);
    }

    public static NewOrUpdateExaminers fromDocument(Document document) {
        int examinerID = document.getInteger("examinerID");
        String name = document.getString("name");
        String contact = document.getString("contact");

        return new NewOrUpdateExaminers(examinerID, name, contact);
    }
}
