package lk.ijse.gdse71.final_project.Dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.CrudUtil;
import lk.ijse.gdse71.final_project.Dao.custom.CourseDao;
import lk.ijse.gdse71.final_project.dto.CourseDto;
import lk.ijse.gdse71.final_project.entity.Course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDaoImpl implements CourseDao {
    public ObservableList<String> getAllCoursseIds() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select Course_id from course;");

        ObservableList<String> ids = FXCollections.observableArrayList();

        while (resultSet.next()){
            String id = resultSet.getString(1);
            ids.add(id);
        }
        return ids;
    }

    public String getCourseName(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select Course_name from course where Course_id = ?",id);

        while (resultSet.next()){
                return resultSet.getString(1);
        }

        return null;
    }
    public double getPayment(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select Full_payment from course where Course_id = ?;",id);

        while (resultSet.next()){
            return resultSet.getDouble(1);
        }
        return 0;
    }

    @Override
    public String getNextId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select Course_id from course order by Course_id desc limit 1;");

        while (resultSet.next()){
            return resultSet.getString(1);

        }
        return "C001";
    }

    @Override
    public ArrayList<Course> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select * from course");
        ArrayList<Course> courses = new ArrayList<>();

        while (resultSet.next()){
            Course course = new Course(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4)
            );
            courses.add(course);
        }
        return courses;    }

    @Override
    public boolean save(Course dto) throws SQLException {
        return CrudUtil.execute("insert into course values(?,?,?,?);",
                dto.getCourseId(),
                dto.getCourseName(),
                dto.getDuration(),
                dto.getPayment()
        );
    }

    @Override
    public boolean Update(Course dto) throws SQLException {
        return CrudUtil.execute("UPDATE course SET Course_name = ?, Duration = ?,Full_payment = ? WHERE Course_id = ?;",
                dto.getCourseName(),
                dto.getDuration(),
                dto.getPayment(),
                dto.getCourseId()
        );    }

    @Override
    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE FROM course WHERE Course_id = ? ;",id);
    }
}
