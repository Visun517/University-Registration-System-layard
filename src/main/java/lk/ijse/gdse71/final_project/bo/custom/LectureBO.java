package lk.ijse.gdse71.final_project.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.dto.LectureDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LectureBO extends SuperBO{
     ArrayList<LectureDto> getAll() throws SQLException;
     String getNextId() throws SQLException;
     boolean Save(LectureDto lectureDto) throws SQLException;
     boolean Update(LectureDto lectureDto) throws SQLException;
     boolean delete(String id) throws SQLException;
     ObservableList<String> getAllLecturesIds() throws SQLException;
     String getLectureName(String id) throws SQLException;
}
