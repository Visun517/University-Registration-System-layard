package lk.ijse.gdse71.final_project.Dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.CrudDAO;
import lk.ijse.gdse71.final_project.dto.ExamDto;
import lk.ijse.gdse71.final_project.entity.Exam;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExamDao extends CrudDAO<Exam> {
     ObservableList<String> getAllExamIds() throws SQLException;

}
