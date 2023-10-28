package DAO;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.util.ArrayList;
import java.util.List;
import newObject.NewOrUpdateRegistration;
import static com.mongodb.client.model.Filters.eq;

public class RegistrationDAO {

    private static final String DATABASE_NAME = "Project2";
    private static final String COLLECTION_NAME = "registration";

    public class DatabaseException extends Exception {

        public DatabaseException(String message) {
            super(message);
        }
    }

    public boolean addNew(NewOrUpdateRegistration newRegistration) throws DatabaseException {
        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Document registrationDocument = newRegistration.toDocument();

            collection.insertOne(registrationDocument);

            return true;
        } catch (Exception e) {
            throw new DatabaseException("Error while adding a new registration: " + e.getMessage());
        }
    }

    public List<NewOrUpdateRegistration> findAllRegistrations() {
        List<NewOrUpdateRegistration> registrations = new ArrayList<>();

        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            collection.find().forEach(document -> registrations.add(NewOrUpdateRegistration.fromDocument(document)));
        } catch (Exception e) {
            // Handle the exception
        }

        return registrations;
    }

    public boolean updateRegistration(int registrationID, NewOrUpdateRegistration updatedRegistration) {
        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Bson filter = eq("registrationID", registrationID);

            NewOrUpdateRegistration registration = new NewOrUpdateRegistration(registrationID, updatedRegistration.getStudentRollNo(),
                updatedRegistration.getExamID(), updatedRegistration.getAdminID());

            collection.replaceOne(filter, registration.toDocument());

            return true;
        } catch (Exception e) {
            // Handle the exception
        }

        return false;
    }

    public boolean deleteRegistration(int registrationID) throws DatabaseException {
        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Bson filter = eq("registrationID", registrationID);

            long count = collection.countDocuments(filter);

            if (count == 0) {
                return false;
            } else {
                collection.deleteOne(filter);
                return true;
            }
        } catch (Exception e) {
            throw new DatabaseException("Error while deleting the registration: " + e.getMessage());
        }
    }
}
