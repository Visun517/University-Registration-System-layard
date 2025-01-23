package lk.ijse.gdse71.final_project.Dao;

import lk.ijse.gdse71.final_project.Dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getInstance(){
        if(daoFactory==null){
            daoFactory=new DAOFactory();
        }
        return daoFactory;
    }
    public  enum DAOType{
        ADMIN,ATTENDENCE,CLASSROOM,COURSE,EXAM,EXPENSES,GRADE,LECTURE,LECTUREMANAGE,SCHEDULE,PAYMENT,REGISTRATION,SEMESTER,STUDENT,SUBJECT,QUERY
    }
    public SuperDAO getDAO(DAOType type){

        switch (type){
            case ADMIN:
                return new AdminDaoImpl();
            case ATTENDENCE:
                return new AttendenceDaoImpl();
            case CLASSROOM:
                return new ClassroomDaoImpl();
            case COURSE:
                return new CourseDaoImpl();
            case EXAM:
                return new ExamDaoImpl();
            case EXPENSES:
                return new ExpenseDaoImpl();
            case GRADE:
                return new GradeDaoImpl();
            case LECTURE:
                return new LectureDaoImpl();
            case LECTUREMANAGE:
                return new LectureMangeDaoImpl();
            case SCHEDULE:
                return new ShedulDaoImpl();
            case PAYMENT:
                return new PaymentDaoImpl();
            case REGISTRATION:
                return new RegistrationDaoImpl();
            case SEMESTER:
                return new SemesterDaoImpl();
            case STUDENT:
                return new StudentDaoImpl();
            case SUBJECT:
                return new SubjectDaoImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }
}
