package lk.ijse.gdse71.final_project.Dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO {
     String getNextId() throws SQLException;
     ArrayList<T> getAll() throws SQLException;
     boolean save(T dto) throws SQLException;
     boolean Update(T dto) throws SQLException;
     boolean delete(String id) throws SQLException;
}
