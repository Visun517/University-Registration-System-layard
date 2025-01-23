package lk.ijse.gdse71.final_project.Dao.custom;

import lk.ijse.gdse71.final_project.Dao.CrudDAO;
import lk.ijse.gdse71.final_project.dto.LectureManageDto;
import lk.ijse.gdse71.final_project.entity.LectureManage;

import java.sql.SQLException;

public interface LectureManageDao extends CrudDAO<LectureManage> {
     String getLectureId(String scheduleId) throws SQLException;
     String getLectureMangeId(String scheduleId) throws SQLException;
}
