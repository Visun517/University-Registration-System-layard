package lk.ijse.gdse71.final_project.Dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.CrudUtil;
import lk.ijse.gdse71.final_project.Dao.custom.ClassroomDao;
import lk.ijse.gdse71.final_project.entity.Classroom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassroomDaoImpl implements ClassroomDao {
    public  ObservableList<String> getAllClassroomIds() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select classroom_id from classroom;");
        ObservableList<String> ids = FXCollections.observableArrayList();

        while (resultSet.next()){
            String id = resultSet.getString(1);
            ids.add(id);
        }
        return ids;
    }

    @Override
    public String getNextId() throws SQLException {
        return "";
    }

    @Override
    public ArrayList<Classroom> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean save(Classroom dto) throws SQLException {
        return false;
    }

    @Override
    public boolean Update(Classroom dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }
}
