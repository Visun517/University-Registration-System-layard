package lk.ijse.gdse71.final_project.Dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.CrudDAO;
import lk.ijse.gdse71.final_project.entity.Classroom;

import java.sql.SQLException;

public interface ClassroomDao extends CrudDAO<Classroom> {
    ObservableList<String> getAllClassroomIds() throws SQLException;
}
