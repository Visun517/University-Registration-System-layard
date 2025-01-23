package lk.ijse.gdse71.final_project.Dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.CrudUtil;
import lk.ijse.gdse71.final_project.Dao.custom.SemesterDao;
import lk.ijse.gdse71.final_project.dto.SemesterDto;
import lk.ijse.gdse71.final_project.entity.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SemesterDaoImpl implements SemesterDao {
    public ObservableList<String> getAllSemesterIds() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select Semester_id from semester;");
        ObservableList<String> ids = FXCollections.observableArrayList();

        while (resultSet.next()){
          String id = resultSet.getString(1);
          ids.add(id);
        }
        return ids;
    }

    public boolean save(Subject subject) throws SQLException {
        return CrudUtil.execute("insert into subject values(?,?,?,?);",
                subject.getSubjectId(),
                subject.getSubjectName(),
                subject.getSubDesc(),
                subject.getSemesterId()
        );
    }

    public boolean Update(Subject subject) throws SQLException {
        return CrudUtil.execute("UPDATE subject SET Subject_name = ?, Sub_desc = ?,Semester_id = ? WHERE Subject_id = ?;",
                subject.getSubjectName(),
                subject.getSubDesc(),
                subject.getSemesterId(),
                subject.getSubjectId()
        );
    }

    @Override
    public String getNextId() throws SQLException {
        return "";
    }

    @Override
    public ArrayList<Subject> getAll() throws SQLException {
        return null;
    }

    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("delete from subject where Subject_id = ? ;",id);
    }
}
