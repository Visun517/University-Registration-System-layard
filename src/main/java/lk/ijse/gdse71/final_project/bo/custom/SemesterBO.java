package lk.ijse.gdse71.final_project.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.dto.SubjectDto;

import java.sql.SQLException;

public interface SemesterBO extends SuperBO{
     ObservableList<String> getAllSemesterIds() throws SQLException;
     boolean save(SubjectDto subjectDto) throws SQLException;
     boolean Update(SubjectDto subjectDto) throws SQLException;
     boolean delete(String id) throws SQLException;

}
