package lk.ijse.gdse71.final_project.Dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.SuperDAO;

import java.sql.SQLException;

public interface QueryDAO extends SuperDAO {
     ObservableList<String> getStudentCourse() throws SQLException;
}
