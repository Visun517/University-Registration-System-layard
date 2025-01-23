package lk.ijse.gdse71.final_project.Dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.CrudUtil;
import lk.ijse.gdse71.final_project.Dao.custom.QueryDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDAOImpl implements QueryDAO {

    public ObservableList<String> getStudentCourse() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT s.* FROM student s LEFT JOIN registration r ON s.Student_id = r.Student_id WHERE r.Student_id IS NULL;");
        ObservableList<String> ids = FXCollections.observableArrayList();

        while (resultSet.next()){
            String id = resultSet.getString(1);
            ids.add(id);
        }
        return  ids;
    }
}
