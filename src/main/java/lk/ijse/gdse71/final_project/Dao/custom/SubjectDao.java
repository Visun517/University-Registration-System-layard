package lk.ijse.gdse71.final_project.Dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.CrudDAO;
import lk.ijse.gdse71.final_project.dto.SubjectDto;
import lk.ijse.gdse71.final_project.entity.Subject;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SubjectDao extends CrudDAO<Subject> {
     String getSemsterName(String id) throws SQLException;
     ObservableList<String> getAllSubjectIds() throws SQLException;
     String getSubjectName(String id) throws SQLException;
}
