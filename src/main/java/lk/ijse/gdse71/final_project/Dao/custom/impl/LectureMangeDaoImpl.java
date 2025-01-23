package lk.ijse.gdse71.final_project.Dao.custom.impl;

import lk.ijse.gdse71.final_project.Dao.CrudUtil;
import lk.ijse.gdse71.final_project.Dao.custom.LectureManageDao;
import lk.ijse.gdse71.final_project.dto.LectureManageDto;
import lk.ijse.gdse71.final_project.entity.LectureManage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LectureMangeDaoImpl implements LectureManageDao {
    public String getNextId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select LectureManagement_id from lecturemanagement order by LectureManagement_id desc limit 1;");

        while (resultSet.next()){
            return resultSet.getString(1);

        }
        return "LM001";
    }

    public boolean save(LectureManage lectureManage) throws SQLException {
        return CrudUtil.execute("insert into lecturemanagement values(?,?,?,?);",
                lectureManage.getLectureManageId(),
                lectureManage.getLectureId(),
                lectureManage.getClassroomId(),
                lectureManage.getScheduleId()
        );

    }

    public String getLectureId(String scheduleId) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select Lecture_id from lecturemanagement where Schedule_id = ?;",scheduleId);

        while (resultSet.next()){
           return resultSet.getString(1);
        }
        return null;
    }

    public String getLectureMangeId(String scheduleId) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select LectureManagement_id from lecturemanagement where Schedule_id = ?;",scheduleId);

        while (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    public boolean Update(LectureManage lectureManage) throws SQLException {
        return CrudUtil.execute("UPDATE lecturemanagement SET Lecture_id = ?, Classroom_id = ?, Schedule_id = ? WHERE LectureManagement_id = ?;",
                lectureManage.getLectureId(),
                lectureManage.getClassroomId(),
                lectureManage.getScheduleId(),
                lectureManage.getLectureManageId()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<LectureManage> getAll() throws SQLException {
        return null;
    }


}
