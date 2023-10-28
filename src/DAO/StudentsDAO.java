package DAO;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;
import java.util.ArrayList;
import java.util.List;
import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import newObject.NewOrUpdateStudents;

public class StudentsDAO {

    private static final String DATABASE_NAME = "Project2";
    private static final String COLLECTION_NAME = "students";

    public class DatabaseException extends Exception {

        public DatabaseException(String message) {
            super(message);
        }
    }

    public boolean addNew(NewOrUpdateStudents newStudent) throws DatabaseException {
        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            // Không cần tạo đối tượng mới NewOrUpdateStudents, sử dụng newStudent đã được truyền vào phương thức.
            Document studentDocument = newStudent.toDocument();

            collection.insertOne(studentDocument);

            return true;
        } catch (Exception e) {
            throw new DatabaseException("Error while adding a new student: " + e.getMessage());
        }
    }

    public List<NewOrUpdateStudents> findAllStudents() {
        List<NewOrUpdateStudents> students = new ArrayList<>();

        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            collection.find().forEach(document -> students.add(NewOrUpdateStudents.fromDocument(document)));
        } catch (Exception e) {
            // Handle the exception
        }

        return students;
    }

    public boolean updateStudent(int rollNo, NewOrUpdateStudents updatedStudent) {
        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Bson filter = eq("rollNo", rollNo);

            NewOrUpdateStudents student = new NewOrUpdateStudents(rollNo, updatedStudent.getName(), updatedStudent.getGender(),
                    updatedStudent.getDateOfBirth(), updatedStudent.getEmail(), updatedStudent.getPhoneNo());

            collection.replaceOne(filter, student.toDocument());

            return true;
        } catch (Exception e) {
            // Handle the exception
        }

        return false;
    }

    public boolean deleteStudent(int rollNo) throws DatabaseException {
        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Bson filter = eq("rollNo", rollNo);

            long count = collection.countDocuments(filter);

            if (count == 0) {
                return false;
            } else {
                collection.deleteOne(filter);
                return true;
            }
        } catch (Exception e) {
            throw new DatabaseException("Error while deleting the student: " + e.getMessage());
        }
    }
}
