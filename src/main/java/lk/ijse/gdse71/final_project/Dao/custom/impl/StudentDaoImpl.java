package lk.ijse.gdse71.final_project.Dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.CrudUtil;
import lk.ijse.gdse71.final_project.Dao.custom.StudentDao;
import lk.ijse.gdse71.final_project.dto.StudentDto;
import lk.ijse.gdse71.final_project.entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDaoImpl implements StudentDao {

    public String getNextId() throws SQLException {
       ResultSet resultSet = CrudUtil.execute("select Student_id from student order by Student_id desc limit 1;");

       while (resultSet.next()){
           return resultSet.getString(1);

       }
       return "S001";
    }

    public ArrayList<Student> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select * from student");

        ArrayList<Student> students = new ArrayList<>();

        while (resultSet.next()){
            Student student = new Student(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
            students.add(student);
        }
        return students;
    }

    public boolean save(Student student) throws SQLException {
        System.out.println("model "+student.toString());

        return  CrudUtil.execute(" INSERT INTO student VALUES(?,?,?,?,?,?);"
                ,student.getStudent_id(),
                student.getName(),
                student.getEmail(),
                student.getAddress(),
                student.getGender(),
                null);
    }

    public boolean Update(Student student) throws SQLException {

        return CrudUtil.execute("UPDATE student SET Name = ?,Email = ?,Address = ?,Gender = ?, add_notes = ? WHERE Student_id = ?;",
                student.getName(),
                student.getEmail(),
                student.getAddress(),
                student.getGender(),
                null,
                student.getStudent_id()
                );
    }

    public boolean delete(String id) throws SQLException {

        return  CrudUtil.execute("DELETE FROM student WHERE Student_id = ?",id);
    }

    public  ObservableList<String> getAllStudentId() throws SQLException {

        ResultSet resultSet = CrudUtil.execute("select Student_id from student;");

        ObservableList<String> ids = FXCollections.observableArrayList();

        while (resultSet.next()){
            String id = resultSet.getString(1);
            ids.add(id);
        }
        return  ids;
    }

    public String getStudentName(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select Name from student where Student_id = ?;",id);

        while (resultSet.next()){
            return resultSet.getString(1);
        }

        return null;
    }

    public String getStudentNote(String id) throws SQLException {

        ResultSet resultSet = CrudUtil.execute("select add_notes from student where Student_id = ?;",id);

        while (resultSet.next()){
            return resultSet.getString(1);
        }

        return null;
    }

    public boolean saveNote(String id,String note) throws SQLException {
        return CrudUtil.execute("UPDATE student SET add_notes = ? WHERE Student_id = ?;",note,id);
    }

    public String getStudentMail(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select Email from student where student_id = ?;",id);

        while (resultSet.next()){
            return  resultSet.getString(1);
        }
        return null;
    }

    public ObservableList<String> getAllStudentIds() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select Student_id from student;");
        ObservableList<String> ids = FXCollections.observableArrayList();

        while (resultSet.next()){
            String id = resultSet.getString(1);
            ids.add(id);
        }
        return ids;
    }
}

