package lk.ijse.gdse71.final_project.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.DAOFactory;
import lk.ijse.gdse71.final_project.Dao.custom.LectureManageDao;
import lk.ijse.gdse71.final_project.Dao.custom.ScheduleDao;
import lk.ijse.gdse71.final_project.bo.custom.LectureSchedulBo;
import lk.ijse.gdse71.final_project.db.DBConnection;
import lk.ijse.gdse71.final_project.dto.LectureManageDto;
import lk.ijse.gdse71.final_project.dto.SchedulDto;
import lk.ijse.gdse71.final_project.entity.LectureManage;
import lk.ijse.gdse71.final_project.entity.Schedule;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class LectureScheduleBoImpl implements LectureSchedulBo {

    LectureManageDao lectureManageDao = (LectureManageDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.LECTUREMANAGE);
    ScheduleDao scheduleDao = (ScheduleDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SCHEDULE);

    @Override
    public String getLectureMangeId(String scheduleId) throws SQLException {
        return lectureManageDao.getLectureMangeId(scheduleId);
    }

    @Override
    public String getNextId() throws SQLException {
        String id =  lectureManageDao.getNextId();
        String subString = id.substring(2);
        int nextId = Integer.parseInt(subString)+1;
        return String.format("LM%03d",nextId);
    }
    @Override
    public String getNextScheduleId() throws SQLException {
        return scheduleDao.getNextId();
    }

    @Override
    public String getLectureId(String scheduleId) throws SQLException {
        return lectureManageDao.getLectureId(scheduleId);
    }

    @Override
    public boolean save(LectureManageDto lectureManageDto) throws SQLException {
        return lectureManageDao.save(new LectureManage(lectureManageDto.getLectureManageId(),lectureManageDto.getLectureId(),lectureManageDto.getClassroomId(),lectureManageDto.getScheduleId()));    }

    @Override
    public ObservableList<String> getSchedulIds() throws SQLException {
        return scheduleDao.getSchedulIds();
    }

    @Override
    public Date getDate(String id) throws SQLException {
        return scheduleDao.getDate(id);
    }

    @Override
    public ArrayList<SchedulDto> getAllShedule() throws SQLException {
        ArrayList<SchedulDto> schedulDtos = new ArrayList<>();
        ArrayList<Schedule> schedules = scheduleDao.getAll();

        for (Schedule schedule : schedules) {
            schedulDtos.add(new SchedulDto(schedule.getSchedulId(),schedule.getCourseId(),schedule.getClassRoomId(),schedule.getStartTime(),schedule.getEndTime(),schedule.getWeekday(),schedule.getDate()));
        }
        return schedulDtos;
    }

    @Override
    public boolean saveSchedule(SchedulDto scheduleDto) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

            boolean isSaved = scheduleDao.save(new Schedule(scheduleDto.getSchedulId(),scheduleDto.getCourseId(),scheduleDto.getClassRoomId(),scheduleDto.getStartTime(),scheduleDto.getEndTime(),scheduleDto.getWeekday(),scheduleDto.getDate()));

            if (isSaved){

                boolean isLectureManeSaved = lectureManageDao.save(new LectureManage(
                        scheduleDto.getLectureManageDto().getLectureManageId(),
                        scheduleDto.getLectureManageDto().getLectureId(),
                        scheduleDto.getLectureManageDto().getClassroomId(),
                        scheduleDto.getLectureManageDto().getScheduleId()
                ));

                if (isLectureManeSaved){
                    connection.commit();
                    return true;
                }

            }
            connection.rollback();
            return false;

        }catch (Exception e){
            e.printStackTrace();
            connection.rollback();
            return false;

        }finally {

            connection.setAutoCommit(true);
        }
    }

    @Override
    public boolean Update(SchedulDto scheduleDto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

            boolean isUpdate = scheduleDao.Update(new Schedule(scheduleDto.getSchedulId(),scheduleDto.getCourseId(),scheduleDto.getClassRoomId(),scheduleDto.getStartTime(),scheduleDto.getEndTime(),scheduleDto.getWeekday(),scheduleDto.getDate()));

            System.out.println(isUpdate+"is update");

            if (isUpdate){

                System.out.println(isUpdate+"is update 1");

                boolean isLectureManeSaved = lectureManageDao.Update(new LectureManage(
                        scheduleDto.getLectureManageDto().getLectureManageId(),
                        scheduleDto.getLectureManageDto().getLectureId(),
                        scheduleDto.getLectureManageDto().getClassroomId(),
                        scheduleDto.getLectureManageDto().getScheduleId()

                ));

                if (isLectureManeSaved){
                    connection.commit();
                    return true;
                }

            }
            connection.rollback();
            return false;

        }catch (Exception e){
            e.printStackTrace();
            connection.rollback();
            return false;

        }finally {

            connection.setAutoCommit(true);
        }
    }

    @Override
    public boolean delete(String scheduleId) throws SQLException {
        return scheduleDao.delete(scheduleId);
    }
}
