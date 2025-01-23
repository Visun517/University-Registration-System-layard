package lk.ijse.gdse71.final_project.Dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.CrudDAO;
import lk.ijse.gdse71.final_project.dto.StudentDto;
import lk.ijse.gdse71.final_project.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentDao extends CrudDAO<Student> {
     String getStudentName(String id) throws SQLException;
     String getStudentNote(String id) throws SQLException;
     boolean saveNote(String id,String note) throws SQLException;
     String getStudentMail(String id) throws SQLException;
     ObservableList<String> getAllStudentIds() throws SQLException;

}
