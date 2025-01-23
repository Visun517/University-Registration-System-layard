package lk.ijse.gdse71.final_project.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.DAOFactory;
import lk.ijse.gdse71.final_project.Dao.custom.SemesterDao;
import lk.ijse.gdse71.final_project.bo.custom.SemesterBO;
import lk.ijse.gdse71.final_project.dto.SubjectDto;
import lk.ijse.gdse71.final_project.entity.Subject;

import java.sql.SQLException;

public class SemesterBOImpl implements SemesterBO {

    SemesterDao semesterDao = (SemesterDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SEMESTER);

    @Override
    public ObservableList<String> getAllSemesterIds() throws SQLException {
        return semesterDao.getAllSemesterIds();
    }

    @Override
    public boolean save(SubjectDto subjectDto) throws SQLException {
        return semesterDao.save(new Subject(subjectDto.getSubjectId(),subjectDto.getSubjectName(),subjectDto.getSubDesc(),subjectDto.getSemesterId()));
    }

    @Override
    public boolean Update(SubjectDto subjectDto) throws SQLException {
        return semesterDao.Update(new Subject(subjectDto.getSubjectId(),subjectDto.getSubjectName(),subjectDto.getSubDesc(),subjectDto.getSemesterId()));
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return semesterDao.delete(id);
    }
}
