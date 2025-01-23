package lk.ijse.gdse71.final_project.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.dto.ExamDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExamBO extends SuperBO{
     ArrayList<ExamDto> getAll() throws SQLException;
     String getNextId() throws SQLException;
     boolean save(ExamDto examDto) throws SQLException;
     ObservableList<String> getAllExamIds() throws SQLException;
     boolean Update(ExamDto examDto) throws SQLException;
     boolean delete(String id) throws SQLException;
}
