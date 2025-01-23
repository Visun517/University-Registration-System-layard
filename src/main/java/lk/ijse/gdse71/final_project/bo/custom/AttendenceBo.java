package lk.ijse.gdse71.final_project.bo.custom;

import lk.ijse.gdse71.final_project.Dao.CrudUtil;
import lk.ijse.gdse71.final_project.dto.AttendenceDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface AttendenceBo extends SuperBO {
     ArrayList<AttendenceDto> getAll() throws SQLException;
     String getNextId() throws SQLException;
     boolean save(AttendenceDto attendenceDto) throws SQLException;
     boolean Update(AttendenceDto attendenceDto) throws SQLException;
     boolean delete(String id) throws SQLException;
}
