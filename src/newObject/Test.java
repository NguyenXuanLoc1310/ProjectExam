package newObject;

import DAO.ExamsDAO;

/**
 *
 * @author Loc Nguyen
 */
public class Test {

    public static void main(String[] args) throws ExamsDAO.DatabaseException {
        ExamsDAO dao = new ExamsDAO();
        NewOrUpdateExams newExam = new NewOrUpdateExams();
        newExam.setAdminID(1);
        newExam.setDuration(4);
        newExam.setExamID(1);
        newExam.setExaminerID(2);
        dao.addNew(newExam);
        
    }

}
