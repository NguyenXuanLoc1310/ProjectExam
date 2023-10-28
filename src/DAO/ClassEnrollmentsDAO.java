package DAO;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.util.ArrayList;
import java.util.List;
import newObject.NewOrUpdateClassEnrollments;
import static com.mongodb.client.model.Filters.eq;

public class ClassEnrollmentsDAO {

    private static final String DATABASE_NAME = "Project2";
    private static final String COLLECTION_NAME = "class_enrollments";

    public class DatabaseException extends Exception {

        public DatabaseException(String message) {
            super(message);
        }
    }

    public boolean addNew(NewOrUpdateClassEnrollments newEnrollment) throws DatabaseException {
        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Document enrollmentDocument = newEnrollment.toDocument();

            collection.insertOne(enrollmentDocument);

            return true;
        } catch (Exception e) {
            throw new DatabaseException("Error while adding a new class enrollment: " + e.getMessage());
        }
    }

    public List<NewOrUpdateClassEnrollments> findAllEnrollments() {
        List<NewOrUpdateClassEnrollments> enrollments = new ArrayList<>();

        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            collection.find().forEach(document -> enrollments.add(NewOrUpdateClassEnrollments.fromDocument(document)));
        } catch (Exception e) {
            // Handle the exception
        }

        return enrollments;
    }

    public boolean updateEnrollment(int enrollmentID, NewOrUpdateClassEnrollments updatedEnrollment) throws DatabaseException {
    try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
        MongoDatabase database = client.getDatabase(DATABASE_NAME);
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

        Bson filter = eq("enrollmentID", enrollmentID);

        NewOrUpdateClassEnrollments enrollment = new NewOrUpdateClassEnrollments(
            enrollmentID, 
            updatedEnrollment.getStudentRollNo(),
            updatedEnrollment.getClassID()
        );

        collection.replaceOne(filter, enrollment.toDocument());

        return true;
    } catch (Exception e) {
        throw new DatabaseException("Error while updating the class enrollment: " + e.getMessage());
    }
}


   public boolean deleteEnrollment(int enrollmentID) throws DatabaseException {
    try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
        MongoDatabase database = client.getDatabase(DATABASE_NAME);
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

        Bson filter = eq("enrollmentID", enrollmentID);

        long count = collection.countDocuments(filter);

        if (count == 0) {
            return false;
        } else {
            collection.deleteOne(filter);
            return true;
        }
    } catch (Exception e) {
        throw new DatabaseException("Error while deleting the class enrollment: " + e.getMessage());
    }
   }
}

