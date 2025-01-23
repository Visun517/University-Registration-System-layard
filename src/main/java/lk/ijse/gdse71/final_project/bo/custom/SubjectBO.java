package lk.ijse.gdse71.final_project.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.dto.SubjectDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SubjectBO extends SuperBO{
     ArrayList<SubjectDto> getAll() throws SQLException;
     String getNextId() throws SQLException;
     String getSemsterName(String id) throws SQLException;
     ObservableList<String> getAllSubjectIds() throws SQLException;
     String getSubjectName(String id) throws SQLException;
}
