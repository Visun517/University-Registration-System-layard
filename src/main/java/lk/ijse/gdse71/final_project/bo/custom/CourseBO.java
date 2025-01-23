package lk.ijse.gdse71.final_project.bo.custom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.CrudUtil;
import lk.ijse.gdse71.final_project.dto.CourseDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CourseBO extends SuperBO{
    public ObservableList<String> getAllCoursseIds() throws SQLException;
    public String getCourseName(String id) throws SQLException;
    public ArrayList<CourseDto> getAllCourses() throws SQLException;
    public boolean savaCourse(CourseDto courseDto) throws SQLException;
    public String getNextCourseId() throws SQLException;
    public boolean deleteCourse(String id) throws SQLException;
    public boolean updatecourse(CourseDto courseDto) throws SQLException ;
    public double getPayment(String id) throws SQLException;
}
