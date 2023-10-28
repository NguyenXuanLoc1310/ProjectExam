package DAO;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.util.ArrayList;
import java.util.List;
import newObject.NewOrUpdateSubjects;
import static com.mongodb.client.model.Filters.eq;

public class SubjectsDAO {

    private static final String DATABASE_NAME = "Project2";
    private static final String COLLECTION_NAME = "subjects";

    public class DatabaseException extends Exception {

        public DatabaseException(String message) {
            super(message);
        }
    }

    public boolean addNew(NewOrUpdateSubjects newSubject) throws DatabaseException {
        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            // Không cần tạo đối tượng mới Subject, sử dụng newSubject đã được truyền vào phương thức.
            Document subjectDocument = newSubject.toDocument();

            collection.insertOne(subjectDocument);

            return true;
        } catch (Exception e) {
            throw new DatabaseException("Error while adding a new subject: " + e.getMessage());
        }
    }

    public List<NewOrUpdateSubjects> findAllSubjects() {
        List<NewOrUpdateSubjects> subjects = new ArrayList<>();

        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            collection.find().forEach(document -> subjects.add(NewOrUpdateSubjects.fromDocument(document)));
        } catch (Exception e) {
            // Handle the exception
        }

        return subjects;
    }

    public boolean updateSubject(int subjectID, NewOrUpdateSubjects updatedSubject) {
        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Bson filter = eq("subjectID", subjectID);

            NewOrUpdateSubjects subject = new NewOrUpdateSubjects(subjectID, updatedSubject.getSubjectName());

            collection.replaceOne(filter, subject.toDocument());

            return true;
        } catch (Exception e) {
            // Handle the exception
        }

        return false;
    }

    public boolean deleteSubject(int subjectID) throws DatabaseException {
        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Bson filter = eq("subjectID", subjectID);

            long count = collection.countDocuments(filter);

            if (count == 0) {
                return false;
            } else {
                collection.deleteOne(filter);
                return true;
            }
        } catch (Exception e) {
            throw new DatabaseException("Error while deleting the subject: " + e.getMessage());
        }
    }
}
