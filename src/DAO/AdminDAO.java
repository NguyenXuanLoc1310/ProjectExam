package DAO;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.util.ArrayList;
import java.util.List;
import newObject.NewOrUpdateAdmin;
import static com.mongodb.client.model.Filters.eq;

public class AdminDAO {

    private static final String DATABASE_NAME = "Project2";
    private static final String COLLECTION_NAME = "admin";

    public class DatabaseException extends Exception {

        public DatabaseException(String message) {
            super(message);
        }
    }

    public boolean addNew(NewOrUpdateAdmin newAdmin) throws DatabaseException {
        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            // Không cần tạo đối tượng mới Admin, sử dụng newAdmin đã được truyền vào phương thức.
            Document adminDocument = newAdmin.toDocument();

            collection.insertOne(adminDocument);

            return true;
        } catch (Exception e) {
            throw new DatabaseException("Error while adding a new admin: " + e.getMessage());
        }
    }

    public List<NewOrUpdateAdmin> findAllAdmins() {
        List<NewOrUpdateAdmin> admins = new ArrayList<>();

        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            collection.find().forEach(document -> admins.add(NewOrUpdateAdmin.fromDocument(document)));
        } catch (Exception e) {
            // Handle the exception
        }

        return admins;
    }

    public boolean updateAdmin(int adminID, NewOrUpdateAdmin updatedAdmin) {
        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Bson filter = eq("adminID", adminID);

            NewOrUpdateAdmin admin = new NewOrUpdateAdmin(adminID, updatedAdmin.getUserName(), updatedAdmin.getPassword(), 
                updatedAdmin.getName(), updatedAdmin.getContact());

            collection.replaceOne(filter, admin.toDocument());

            return true;
        } catch (Exception e) {
            // Handle the exception
        }

        return false;
    }

    public boolean deleteAdmin(int adminID) throws DatabaseException {
        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Bson filter = eq("adminID", adminID);

            long count = collection.countDocuments(filter);

            if (count == 0) {
                return false;
            } else {
                collection.deleteOne(filter);
                return true;
            }
        } catch (Exception e) {
            throw new DatabaseException("Error while deleting the admin: " + e.getMessage());
        }
    }
}
