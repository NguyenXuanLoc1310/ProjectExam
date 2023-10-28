package DAO;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.util.ArrayList;
import java.util.List;
import newObject.NewOrUpdateExams;
import static com.mongodb.client.model.Filters.eq;

public class ExamsDAO {

    private static final String DATABASE_NAME = "Project2";
    private static final String COLLECTION_NAME = "exams";

    public class DatabaseException extends Exception {

        public DatabaseException(String message) {
            super(message);
        }
    }

    public boolean addNew(NewOrUpdateExams newExam) throws DatabaseException {
        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            // Không cần tạo đối tượng mới Exams, sử dụng newExam đã được truyền vào phương thức.
            Document examDocument = newExam.toDocument();

            collection.insertOne(examDocument);

            return true;
        } catch (Exception e) {
            throw new DatabaseException("Error while adding a new exam: " + e.getMessage());
        }
    }

    public List<NewOrUpdateExams> findAllExams() {
        List<NewOrUpdateExams> exams = new ArrayList<>();

        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            collection.find().forEach(document -> exams.add(NewOrUpdateExams.fromDocument(document)));
        } catch (Exception e) {
            // Handle the exception
        }

        return exams;
    }

    public boolean updateExam(int examID, NewOrUpdateExams updatedExam) {
        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Bson filter = eq("examID", examID);

            NewOrUpdateExams exam = new NewOrUpdateExams(examID, updatedExam.getExamDate(), updatedExam.getStartTime(), 
                updatedExam.getExaminerID(), updatedExam.getAdminID(), updatedExam.getDuration(),
                updatedExam.getRoom(), updatedExam.getSubjectID());

            collection.replaceOne(filter, exam.toDocument());

            return true;
        } catch (Exception e) {
            // Handle the exception
        }

        return false;
    }

    public boolean deleteExam(int examID) throws DatabaseException {
        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Bson filter = eq("examID", examID);

            long count = collection.countDocuments(filter);

            if (count == 0) {
                return false;
            } else {
                collection.deleteOne(filter);
                return true;
            }
        } catch (Exception e) {
            throw new DatabaseException("Error while deleting the exam: " + e.getMessage());
        }
    }
}
