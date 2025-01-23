package lk.ijse.gdse71.final_project.Dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.CrudDAO;
import lk.ijse.gdse71.final_project.dto.SchedulDto;
import lk.ijse.gdse71.final_project.entity.Schedule;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ScheduleDao extends CrudDAO<Schedule> {
     ObservableList<String> getSchedulIds() throws SQLException;
     Date getDate(String id) throws SQLException;

}
