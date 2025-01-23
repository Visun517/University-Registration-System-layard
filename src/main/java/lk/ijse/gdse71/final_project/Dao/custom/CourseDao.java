package lk.ijse.gdse71.final_project.Dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.CrudDAO;
import lk.ijse.gdse71.final_project.dto.CourseDto;
import lk.ijse.gdse71.final_project.entity.Course;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CourseDao extends CrudDAO<Course> {
     ObservableList<String> getAllCoursseIds() throws SQLException;
     String getCourseName(String id) throws SQLException;
     double getPayment(String id) throws SQLException;
}
