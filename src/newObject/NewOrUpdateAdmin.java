package newObject;

import lombok.Getter;
import lombok.Setter;
import org.bson.Document;
@Getter
@Setter
public class NewOrUpdateAdmin {
    private int adminID;
    private String userName;
    private String password;
    private String name;
    private String contact;

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

  public NewOrUpdateAdmin(int adminID, String userName, String password, String name, String contact) {
        this.adminID = adminID;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.contact = contact;
    }

    public NewOrUpdateAdmin() {
    }

    

    public Document toDocument() {
        return new Document("adminID", adminID)
                .append("userName", userName)
                .append("password", password)
                .append("name", name)
                .append("contact", contact);
    }

    public static NewOrUpdateAdmin fromDocument(Document document) {
        int adminID = document.getInteger("adminID");
        String userName = document.getString("userName");
        String password = document.getString("password");
        String name = document.getString("name");
        String contact = document.getString("contact");

        NewOrUpdateAdmin admin = new NewOrUpdateAdmin();
        admin.setAdminID(adminID);
        admin.setUserName(userName);
        admin.setPassword(password);
        admin.setName(name);
        admin.setContact(contact);

        return admin;
    }
}
