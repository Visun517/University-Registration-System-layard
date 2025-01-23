package lk.ijse.gdse71.final_project.Dao.custom.impl;

import lk.ijse.gdse71.final_project.Dao.CrudUtil;
import lk.ijse.gdse71.final_project.Dao.custom.AttendenceDao;
import lk.ijse.gdse71.final_project.dto.AttendenceDto;
import lk.ijse.gdse71.final_project.entity.Attendence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttendenceDaoImpl implements AttendenceDao {

    @Override
    public ArrayList<Attendence> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select * from attendance");
        ArrayList<Attendence> attendences = new ArrayList<>();

        while (resultSet.next()){
            Attendence attendence = new Attendence(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4),
                    resultSet.getString(5)

                    );
            attendences.add(attendence);
        }
        return attendences;
    }


    @Override
    public String getNextId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select Attendance_id from attendance order by Attendance_id desc limit 1;");

        while (resultSet.next()){
            return resultSet.getString(1);
        }
        return "A001";
    }
    @Override
    public boolean save(Attendence attendence) throws SQLException {
        return CrudUtil.execute(" insert into attendance values(?,?,?,?,?);",
                attendence.getAttendenceId(),
                attendence.getSchedulId(),
                attendence.getStudentId(),
                attendence.getClassDate(),
                attendence.getRemark()
        );
    }
    @Override
    public boolean Update(Attendence attendence) throws SQLException {
        return CrudUtil.execute("UPDATE attendance SET Schedule_id = ?, Student_id = ?,Class_date = ?,Remark = ? WHERE Attendance_id = ?;",
                attendence.getSchedulId(),
                attendence.getStudentId(),
                attendence.getClassDate(),
                attendence.getRemark(),
                attendence.getAttendenceId()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException {
       return CrudUtil.execute("DELETE FROM attendance WHERE Attendance_id = ?;",id);
    }
}
