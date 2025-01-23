package lk.ijse.gdse71.final_project.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.DAOFactory;
import lk.ijse.gdse71.final_project.Dao.custom.QueryDAO;
import lk.ijse.gdse71.final_project.Dao.custom.StudentDao;
import lk.ijse.gdse71.final_project.bo.custom.StudentBO;
import lk.ijse.gdse71.final_project.dto.StudentDto;
import lk.ijse.gdse71.final_project.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentBOImpl implements StudentBO {

    StudentDao studentDao = (StudentDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.STUDENT);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUERY);

    @Override
    public String getNextId() throws SQLException {
        String id = studentDao.getNextId();
        String subString = id.substring(1);
        int nextId = Integer.parseInt(subString)+1;
        return String.format("S%03d",nextId);
    }

    @Override
    public ArrayList<StudentDto> getAll() throws SQLException {
        ArrayList<StudentDto> studentDtos = new ArrayList<>();
        ArrayList<Student> students = studentDao.getAll();

        for (Student student : students) {
            studentDtos.add(new StudentDto(student.getStudent_id(),student.getName(),student.getEmail(),student.getAddress(),student.getGender()));
        }
        return studentDtos;

    }

    @Override
    public boolean save(StudentDto studentDto) throws SQLException {
        return studentDao.save(new Student(studentDto.getStudent_id(),studentDto.getName(),studentDto.getEmail(),studentDto.getAddress(),studentDto.getGender()));
    }

    @Override
    public boolean Update(StudentDto studentDto) throws SQLException {
        return studentDao.Update(new Student(studentDto.getStudent_id(),studentDto.getName(),studentDto.getEmail(),studentDto.getAddress(),studentDto.getGender()));
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return studentDao.delete(id);
    }

    @Override
    public ObservableList<String> getAllStudentId() throws SQLException {
        return studentDao.getAllStudentIds();
    }

    @Override
    public String getStudentName(String id) throws SQLException {
        return studentDao.getStudentName(id);
    }

    @Override
    public String getStudentNote(String id) throws SQLException {
        return studentDao.getStudentNote(id);
    }

    @Override
    public boolean saveNote(String id, String note) throws SQLException {
        return studentDao.saveNote(id,note);
    }

    @Override
    public String getStudentMail(String id) throws SQLException {
        return studentDao.getStudentMail(id);
    }

    @Override
    public ObservableList<String> getStudentCourse() throws SQLException {
        return queryDAO.getStudentCourse();
    }

    @Override
    public ObservableList<String> getAllStudentIds() throws SQLException {
        return studentDao.getAllStudentIds();
    }
}
