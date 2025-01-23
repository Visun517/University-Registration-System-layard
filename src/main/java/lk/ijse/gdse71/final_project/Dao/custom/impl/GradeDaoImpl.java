package lk.ijse.gdse71.final_project.Dao.custom.impl;

import lk.ijse.gdse71.final_project.Dao.CrudUtil;
import lk.ijse.gdse71.final_project.Dao.custom.GradeDao;
import lk.ijse.gdse71.final_project.dto.GradeDto;
import lk.ijse.gdse71.final_project.entity.Grade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GradeDaoImpl implements GradeDao {
    public ArrayList<Grade> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select * from grade;");
        ArrayList<Grade> grades = new ArrayList<>();

        while (resultSet.next()){
            Grade grade = new Grade(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
            grades.add(grade);
        }
        return grades;
    }

    public String getNextId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select Grade_id from grade order by Grade_id desc limit 1;");

        while (resultSet.next()){
            return resultSet.getString(1);

        }
        return "G001";
    }

    public boolean save(Grade grade) throws SQLException {
        return CrudUtil.execute("insert into grade values(?,?,?,?,?);",
                grade.getGradeId(),
                grade.getExamId(),
                grade.getGrade(),
                grade.getDesc(),
                grade.getStudentId()
        );
    }

    public boolean Update(Grade grade) throws SQLException {
        return CrudUtil.execute("UPDATE grade SET Exam_id = ?, Grade = ?, Description = ?,student_id = ? where Grade_id = ?;",
                grade.getExamId(),
                grade.getGrade(),
                grade.getDesc(),
                grade.getStudentId(),
                grade.getGradeId()
        );
    }

    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("delete from grade where Grade_id = ?;",id);
    }
}
