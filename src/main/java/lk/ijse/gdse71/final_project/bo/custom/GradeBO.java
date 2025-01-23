package lk.ijse.gdse71.final_project.bo.custom;

import lk.ijse.gdse71.final_project.dto.GradeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GradeBO extends SuperBO{
     ArrayList<GradeDto> getAll() throws SQLException;
     String getNextId() throws SQLException;
     boolean save(GradeDto gradeDto) throws SQLException;
     boolean Update(GradeDto gradeDto) throws SQLException;
     boolean delete(String id) throws SQLException;
}
