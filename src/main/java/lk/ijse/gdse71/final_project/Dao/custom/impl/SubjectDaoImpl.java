package lk.ijse.gdse71.final_project.Dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.CrudUtil;
import lk.ijse.gdse71.final_project.Dao.custom.SubjectDao;
import lk.ijse.gdse71.final_project.dto.SubjectDto;
import lk.ijse.gdse71.final_project.entity.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SubjectDaoImpl implements SubjectDao {
    public ArrayList<Subject> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select * from subject");
        ArrayList<Subject> subjects = new ArrayList<>();

        while (resultSet.next()){
            Subject subject = new Subject(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            subjects.add(subject);
        }
        return subjects;
    }

    public String getNextId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select Subject_id from subject order by Subject_id desc limit 1;");

        while (resultSet.next()){
            return resultSet.getString(1);

        }
        return "SUB001";
    }

    public String getSemsterName(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select Semester_name from semester where Semester_id = ?;",id);

        while (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    public ObservableList<String> getAllSubjectIds() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select Subject_id from subject;");
        ObservableList<String> ids = FXCollections.observableArrayList();

        while (resultSet.next()){
            String id = resultSet.getString(1);
            ids.add(id);
        }
      return ids;
    }

    public String getSubjectName(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select Subject_name from subject where subject_id = ?;",id);

        while (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }



    @Override
    public boolean save(Subject dto) throws SQLException {
        return false;
    }

    @Override
    public boolean Update(Subject dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }
}
