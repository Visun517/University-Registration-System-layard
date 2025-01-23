package lk.ijse.gdse71.final_project.bo.custom;
import lk.ijse.gdse71.final_project.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public  enum BOType{
        ADMIN,ATTENDENCE,CLASSROOM,COURSE,EXAM,EXPENSE,GRADE,LECTURE,LECTUREMANAGE,PAYMENTPROSSECING,SEMSTER,STUDENT,SUBJECT
    }
    public SuperBO getBo(BOType type){

        switch (type){
            case ADMIN:
                return new AdminBOImpl();
            case ATTENDENCE:
                return new AttendenceBOImpl();
            case CLASSROOM:
                return new ClassroomBOImpl();
            case COURSE:
                return new CourseBOImpl();
            case EXAM:
                return new ExamBOImpl();
            case EXPENSE:
                return new ExpenseBOImpl();
            case GRADE:
                return new GradeBOImpl();
            case LECTURE:
                return new LectureBoImpl();
            case LECTUREMANAGE:
                return new LectureScheduleBoImpl();
            case PAYMENTPROSSECING:
                return new PaymentProssecingBOImpl();
            case SEMSTER:
                return new SemesterBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case SUBJECT:
                return  new SubjectBOImpl();
            default:
                return null;
        }
    }
}
