package lk.ijse.gdse71.final_project.Dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.CrudDAO;
import lk.ijse.gdse71.final_project.dto.AdminDto;
import lk.ijse.gdse71.final_project.dto.tm.AdminTm;
import lk.ijse.gdse71.final_project.entity.Admin;

import java.sql.SQLException;

public interface AdminDao extends CrudDAO<Admin> {
     AdminDto checkAdmin(String userName) throws SQLException;
     String getAdminPass(String pass) throws SQLException;
}
