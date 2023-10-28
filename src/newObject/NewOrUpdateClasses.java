package newObject;

import org.bson.Document;

public class NewOrUpdateClasses {
    private int classID;
    private String className;
    private String joinDate;

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public NewOrUpdateClasses(int classID, String className, String joinDate) {
        this.classID = classID;
        this.className = className;
        this.joinDate = joinDate;
    }

    public NewOrUpdateClasses() {
    }

   
    public Document toDocument() {
        return new Document("classID", classID)
                .append("className", className)
                .append("joinDate", joinDate);
    }

    public static NewOrUpdateClasses fromDocument(Document document) {
        int classID = document.getInteger("classID");
        String className = document.getString("className");
        String joinDate = document.getString("joinDate");

        NewOrUpdateClasses classes = new NewOrUpdateClasses();
        classes.setClassID(classID);
        classes.setClassName(className);
        classes.setJoinDate(joinDate);

        return classes;
    }
}
