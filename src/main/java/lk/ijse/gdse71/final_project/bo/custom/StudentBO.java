package lk.ijse.gdse71.final_project.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.dto.StudentDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentBO extends SuperBO{
     String getNextId() throws SQLException;
     ArrayList<StudentDto> getAll() throws SQLException;
     boolean save(StudentDto studentDto) throws SQLException;
     boolean Update(StudentDto studentDto) throws SQLException;
     boolean delete(String id) throws SQLException;
     ObservableList<String> getAllStudentId() throws SQLException;
     String getStudentName(String id) throws SQLException;
     String getStudentNote(String id) throws SQLException;
     boolean saveNote(String id,String note) throws SQLException;
     String getStudentMail(String id) throws SQLException;
     ObservableList<String> getStudentCourse() throws SQLException;
     ObservableList<String> getAllStudentIds() throws SQLException;
}
