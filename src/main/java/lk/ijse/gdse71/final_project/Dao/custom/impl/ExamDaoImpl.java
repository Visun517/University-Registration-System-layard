package lk.ijse.gdse71.final_project.Dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.CrudUtil;
import lk.ijse.gdse71.final_project.Dao.custom.ExamDao;
import lk.ijse.gdse71.final_project.dto.ExamDto;
import lk.ijse.gdse71.final_project.entity.Exam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExamDaoImpl implements ExamDao {

    public ArrayList<Exam> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select * from exam;");
        ArrayList<Exam> exams = new ArrayList<>();

        while (resultSet.next()){
            Exam exam = new Exam(
                    resultSet.getNString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4)
            );
            exams.add(exam);
        }
        return exams;
    }

    public String getNextId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select Exam_id from exam order by Exam_id desc limit 1;");

        while (resultSet.next()){
            return resultSet.getString(1);

        }
        return "E001";
    }

    public boolean save(Exam exam) throws SQLException {
//        System.out.println(examDto.getDesc());
        return CrudUtil.execute("insert into exam values(?,?,?,?)",
                                    exam.getExamId(),
                                    exam.getSubjectId(),
                                    exam.getDesc(),
                                    exam.getDate()
                );
    }

    public ObservableList<String> getAllExamIds() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select Exam_id from exam;");
        ObservableList<String> examIds = FXCollections.observableArrayList();

        while (resultSet.next()){
            String ids = resultSet.getString(1);
            examIds.add(ids);
        }
        return  examIds;
    }

    public boolean Update(Exam exam) throws SQLException {
        return CrudUtil.execute("UPDATE exam SET Subject_id = ?, Description = ?, Date = ? WHERE Exam_id = ?;",
                exam.getSubjectId(),
                exam.getDesc(),
                exam.getDate(),
                exam.getExamId()
        );
    }

    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("delete from exam where Exam_id = ?",id);
    }
}
