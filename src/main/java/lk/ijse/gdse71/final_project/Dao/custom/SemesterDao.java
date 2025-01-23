package lk.ijse.gdse71.final_project.Dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.CrudDAO;
import lk.ijse.gdse71.final_project.dto.SemesterDto;
import lk.ijse.gdse71.final_project.dto.SubjectDto;
import lk.ijse.gdse71.final_project.entity.Subject;

import java.sql.SQLException;

public interface SemesterDao extends CrudDAO<Subject> {
     ObservableList<String> getAllSemesterIds() throws SQLException;


}
