package lk.ijse.gdse71.final_project.bo.custom;

import lk.ijse.gdse71.final_project.dto.AdminDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AdminBO extends SuperBO {
     AdminDto checkAdmin(String userName) throws SQLException;
     String getNextId() throws SQLException;
     ArrayList<AdminDto> getAll() throws SQLException;
     boolean save(AdminDto adminDto) throws SQLException;
     boolean Update(AdminDto adminDto) throws SQLException;
     boolean delete(String id) throws SQLException;
     String getAdminPass(String pass) throws SQLException;

}
