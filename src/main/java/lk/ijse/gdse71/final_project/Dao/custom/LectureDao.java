package lk.ijse.gdse71.final_project.Dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.CrudDAO;
import lk.ijse.gdse71.final_project.Dao.SuperDAO;
import lk.ijse.gdse71.final_project.dto.LectureDto;
import lk.ijse.gdse71.final_project.entity.Lecture;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LectureDao extends CrudDAO<Lecture> {
     ObservableList<String> getAllLecturesIds() throws SQLException;
     String getLectureName(String id) throws SQLException;
}
