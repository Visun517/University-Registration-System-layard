package lk.ijse.gdse71.final_project.Dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.CrudUtil;
import lk.ijse.gdse71.final_project.Dao.custom.LectureManageDao;
import lk.ijse.gdse71.final_project.Dao.custom.ScheduleDao;
import lk.ijse.gdse71.final_project.db.DBConnection;
import lk.ijse.gdse71.final_project.dto.SchedulDto;
import lk.ijse.gdse71.final_project.entity.Schedule;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShedulDaoImpl implements ScheduleDao {


    public ObservableList<String> getSchedulIds() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select * from schedule");
        ObservableList<String> ids = FXCollections.observableArrayList();

        while (resultSet.next()){
            String id = resultSet.getString(1);
            ids.add(id);
        }
        return ids;
    }

    public Date getDate(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select Date from schedule where Schedule_id =?;",id);

        while (resultSet.next()){
            return  resultSet.getDate(1);
        }
        return null;
    }

    public ArrayList<Schedule> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select * from schedule;");
        ArrayList<Schedule> schedules = new ArrayList<>();

        while (resultSet.next()){
            Schedule schedule = new Schedule(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getDate(7)

            );
            schedules.add(schedule);
        }
        return schedules;
    }

    public String getNextId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select Schedule_id from schedule order by Schedule_id desc limit 1;");

        while (resultSet.next()){
            String id = resultSet.getString(1);
            String subString = id.substring(2);
            int nextId = Integer.parseInt(subString)+1;
            return String.format("SC%03d",nextId);

        }
        return "SC001";
    }

    public boolean save(Schedule schedule) throws SQLException {
        return CrudUtil.execute("insert into schedule values(?,?,?,?,?,?,?);",
                schedule.getSchedulId(),
                schedule.getCourseId(),
                schedule.getClassRoomId(),
                schedule.getStartTime(),
                schedule.getEndTime(),
                schedule.getWeekday(),
                schedule.getDate()
        );
    }

    public boolean Update(Schedule schedule) throws SQLException {
        return CrudUtil.execute("UPDATE schedule SET Course_id = ?, Classroom_id = ?, Start_time = ?,End_time = ?,Week_day = ?,Date = ? WHERE Schedule_id = ?;",
                schedule.getCourseId(),
                schedule.getClassRoomId(),
                schedule.getStartTime(),
                schedule.getEndTime(),
                schedule.getWeekday(),
                schedule.getDate(),
                schedule.getSchedulId()
        );
    }

    public boolean delete(String scheduleId) throws SQLException {
        return CrudUtil.execute("DELETE FROM schedule WHERE Schedule_id = ?;", scheduleId);
    }
}
