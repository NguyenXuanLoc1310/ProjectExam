package DAO;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.util.ArrayList;
import java.util.List;
import newObject.NewOrUpdateExaminers;
import static com.mongodb.client.model.Filters.eq;


public class ExaminersDAO {

    private static final String DATABASE_NAME = "Project2";
    private static final String COLLECTION_NAME = "examiners";

    public class DatabaseException extends Exception {

        public DatabaseException(String message) {
            super(message);
        }
    }

    public boolean addNew(NewOrUpdateExaminers newExaminer) throws DatabaseException {
        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            // Không cần tạo đối tượng mới Examiner, sử dụng newExaminer đã được truyền vào phương thức.
            Document examinerDocument = newExaminer.toDocument();

            collection.insertOne(examinerDocument);

            return true;
        } catch (Exception e) {
            throw new DatabaseException("Error while adding a new examiner: " + e.getMessage());
        }
    }

    public List<NewOrUpdateExaminers> findAllExaminers() {
        List<NewOrUpdateExaminers> examiners = new ArrayList<>();

        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            collection.find().forEach(document -> examiners.add(NewOrUpdateExaminers.fromDocument(document)));
        } catch (Exception e) {
            // Xử lý ngoại lệ
        }

        return examiners;
    }

    public boolean updateExaminer(int examinerID, NewOrUpdateExaminers updatedExaminer) {
        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Bson filter = eq("examinerID", examinerID);

            NewOrUpdateExaminers examiner = new NewOrUpdateExaminers(examinerID, updatedExaminer.getName(), updatedExaminer.getContact());

            collection.replaceOne(filter, examiner.toDocument());

            return true;
        } catch (Exception e) {
            // Xử lý ngoại lệ
        }

        return false;
    }

    public boolean deleteExaminer(int examinerID) throws DatabaseException {
        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Bson filter = eq("examinerID", examinerID);

            long count = collection.countDocuments(filter);

            if (count == 0) {
                return false;
            } else {
                collection.deleteOne(filter);
                return true;
            }
        } catch (Exception e) {
            throw new DatabaseException("Error while deleting the examiner: " + e.getMessage());
        }
    }
}
