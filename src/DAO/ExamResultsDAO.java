package DAO;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.util.ArrayList;
import java.util.List;
import newObject.NewOrUpdateExamResults;
import static com.mongodb.client.model.Filters.eq;

public class ExamResultsDAO {

    private static final String DATABASE_NAME = "Project2";
    private static final String COLLECTION_NAME = "exam_results";

    public class DatabaseException extends Exception {

        public DatabaseException(String message) {
            super(message);
        }
    }

    public boolean addNew(NewOrUpdateExamResults newExamResult) throws DatabaseException {
        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Document examResultDocument = newExamResult.toDocument();

            collection.insertOne(examResultDocument);

            return true;
        } catch (Exception e) {
            throw new DatabaseException("Error while adding a new exam result: " + e.getMessage());
        }
    }

    public List<NewOrUpdateExamResults> findAllExamResults() {
        List<NewOrUpdateExamResults> examResults = new ArrayList<>();

        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            collection.find().forEach(document -> examResults.add(NewOrUpdateExamResults.fromDocument(document)));
        } catch (Exception e) {
            // Handle the exception
        }

        return examResults;
    }

       public boolean updateExamResult(int resultID, NewOrUpdateExamResults updatedExamResult) {
        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Bson filter = eq("resultID", resultID);

            NewOrUpdateExamResults examResult = new NewOrUpdateExamResults(resultID, resultID, resultID, resultID);
               

            collection.replaceOne(filter, examResult.toDocument());

            return true;
        } catch (Exception e) {
            // Handle the exception
        }

        return false;
    }


    public boolean deleteExamResult(int resultID) throws DatabaseException {
        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Bson filter = eq("resultID", resultID);

            long count = collection.countDocuments(filter);

            if (count == 0) {
                return false;
            } else {
                collection.deleteOne(filter);
                return true;
            }
        } catch (Exception e) {
            throw new DatabaseException("Error while deleting the exam result: " + e.getMessage());
        }
    }
}
