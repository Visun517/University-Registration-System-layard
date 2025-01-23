package lk.ijse.gdse71.final_project.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.custom.CourseDao;
import lk.ijse.gdse71.final_project.Dao.custom.impl.CourseDaoImpl;
import lk.ijse.gdse71.final_project.bo.custom.CourseBO;
import lk.ijse.gdse71.final_project.dto.CourseDto;
import lk.ijse.gdse71.final_project.entity.Course;

import java.sql.SQLException;
import java.util.ArrayList;

public class CourseBOImpl implements CourseBO {
    CourseDao courseDao = new CourseDaoImpl();
    @Override
    public ObservableList<String> getAllCoursseIds() throws SQLException {
        return courseDao.getAllCoursseIds();
    }

    @Override
    public String getCourseName(String id) throws SQLException {
        return courseDao.getCourseName(id);
    }

    @Override
    public ArrayList<CourseDto> getAllCourses() throws SQLException {
        ArrayList<CourseDto> courseDtos = new ArrayList<>();
        ArrayList<Course> courses = courseDao.getAll();

        for (Course course : courses) {
            courseDtos.add(new CourseDto(course.getCourseId(), course.getCourseName(), course.getDuration(), course.getPayment()));
        }
        return courseDtos;
    }

    @Override
    public boolean savaCourse(CourseDto courseDto) throws SQLException {
        return courseDao.save(new Course(courseDto.getCourseId(), courseDto.getCourseName(), courseDto.getDuration(), courseDto.getPayment()));
    }

    @Override
    public String getNextCourseId() throws SQLException {
        String id =  courseDao.getNextId();
        String subString = id.substring(1);
        int nextId = Integer.parseInt(subString)+1;
        return String.format("C%03d",nextId);
    }

    @Override
    public boolean deleteCourse(String id) throws SQLException {
        return courseDao.delete(id);
    }

    @Override
    public boolean updatecourse(CourseDto courseDto) throws SQLException {

        return courseDao.Update(new Course(courseDto.getCourseId(), courseDto.getCourseName(), courseDto.getDuration(), courseDto.getPayment()));
    }

    @Override
    public double getPayment(String id) throws SQLException {
        return courseDao.getPayment(id);
    }
}
