package lk.ijse.gdse71.final_project.bo.custom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ClassroomBO extends SuperBO {
     ObservableList<String> getAllClassroomIds() throws SQLException;
}
