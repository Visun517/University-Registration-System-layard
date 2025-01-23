package lk.ijse.gdse71.final_project.bo.custom;
import javafx.collections.ObservableList;

import lk.ijse.gdse71.final_project.dto.LectureManageDto;
import lk.ijse.gdse71.final_project.dto.SchedulDto;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public interface LectureSchedulBo extends SuperBO{
      String getLectureMangeId(String scheduleId) throws SQLException;
      String getNextId() throws SQLException;
      boolean save(LectureManageDto lectureManageDto) throws SQLException;
      ObservableList<String> getSchedulIds() throws SQLException;
      Date getDate(String id) throws SQLException;
      ArrayList<SchedulDto> getAllShedule() throws SQLException;
      boolean saveSchedule(SchedulDto scheduleDto) throws SQLException;
      boolean Update(SchedulDto scheduleDto) throws SQLException;
      boolean delete(String scheduleId) throws SQLException;
      String getNextScheduleId() throws SQLException;
      String getLectureId(String scheduleId) throws SQLException;



}
