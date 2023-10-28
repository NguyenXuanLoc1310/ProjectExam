package DAO;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.util.ArrayList;
import java.util.List;
import newObject.NewOrUpdateClasses;
import static com.mongodb.client.model.Filters.eq;

public class ClassesDAO {

    private static final String DATABASE_NAME = "Project2";
    private static final String COLLECTION_NAME = "classes";

    public class DatabaseException extends Exception {

        public DatabaseException(String message) {
            super(message);
        }
    }

    public boolean addNew(NewOrUpdateClasses newClass) throws DatabaseException {
        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Document classDocument = newClass.toDocument();

            collection.insertOne(classDocument);

            return true;
        } catch (Exception e) {
            throw new DatabaseException("Error while adding a new class: " + e.getMessage());
        }
    }

    public List<NewOrUpdateClasses> findAllClasses() {
        List<NewOrUpdateClasses> classes = new ArrayList<>();

        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            collection.find().forEach(document -> classes.add(NewOrUpdateClasses.fromDocument(document)));
        } catch (Exception e) {
            // Handle the exception
        }

        return classes;
    }

    public boolean updateClass(int classID, NewOrUpdateClasses updatedClass) {
        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Bson filter = eq("classID", classID);

            NewOrUpdateClasses classObj = new NewOrUpdateClasses(classID, DATABASE_NAME, COLLECTION_NAME);

            collection.replaceOne(filter, classObj.toDocument());

            return true;
        } catch (Exception e) {
            // Handle the exception
        }

        return false;
    }

    public boolean deleteClass(int classID) throws DatabaseException {
        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Bson filter = eq("classID", classID);

            long count = collection.countDocuments(filter);

            if (count == 0) {
                return false;
            } else {
                collection.deleteOne(filter);
                return true;
            }
        } catch (Exception e) {
            throw new DatabaseException("Error while deleting the class: " + e.getMessage());
        }
    }
}
