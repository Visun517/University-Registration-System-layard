package lk.ijse.gdse71.final_project.bo.custom.impl;

import lk.ijse.gdse71.final_project.Dao.DAOFactory;
import lk.ijse.gdse71.final_project.Dao.custom.AttendenceDao;
import lk.ijse.gdse71.final_project.bo.custom.AttendenceBo;
import lk.ijse.gdse71.final_project.dto.AttendenceDto;
import lk.ijse.gdse71.final_project.entity.Attendence;

import java.sql.SQLException;
import java.util.ArrayList;

public class AttendenceBOImpl implements AttendenceBo {

    AttendenceDao attendenceDao = (AttendenceDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ATTENDENCE);

    @Override
    public ArrayList<AttendenceDto> getAll() throws SQLException {
        ArrayList<AttendenceDto> attendenceDtos = new ArrayList<>();
        ArrayList<Attendence> attendences = attendenceDao.getAll();

        for (Attendence attendence : attendences) {
            attendenceDtos.add(new AttendenceDto(attendence.getAttendenceId(), attendence.getSchedulId(), attendence.getStudentId(), attendence.getClassDate(), attendence.getRemark()));
        }
        return attendenceDtos;
    }

    @Override
    public String getNextId() throws SQLException {
        String id =  attendenceDao.getNextId();

        String subString = id.substring(1);
        int nextId = Integer.parseInt(subString)+1;
        return String.format("A%03d",nextId);
    }

    @Override
    public boolean save(AttendenceDto attendenceDto) throws SQLException {
        return attendenceDao.save(new Attendence(attendenceDto.getAttendenceId(),
                                                    attendenceDto.getSchedulId(),
                                                    attendenceDto.getStudentId(),
                                                    attendenceDto.getClassDate(),
                                                    attendenceDto.getRemark()));
    }

    @Override
    public boolean Update(AttendenceDto attendenceDto) throws SQLException {
        return attendenceDao.Update(new Attendence(attendenceDto.getAttendenceId(),
                                                        attendenceDto.getSchedulId(),
                                                        attendenceDto.getStudentId(),
                                                        attendenceDto.getClassDate(),
                                                        attendenceDto.getRemark()));
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return attendenceDao.delete(id);
    }
}
