package lk.ijse.gdse71.final_project.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.DAOFactory;
import lk.ijse.gdse71.final_project.Dao.custom.SubjectDao;
import lk.ijse.gdse71.final_project.bo.custom.SubjectBO;
import lk.ijse.gdse71.final_project.dto.SubjectDto;
import lk.ijse.gdse71.final_project.entity.Subject;

import java.sql.SQLException;
import java.util.ArrayList;

public class SubjectBOImpl implements SubjectBO {

    SubjectDao subjectDao = (SubjectDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUBJECT);

    @Override
    public ArrayList<SubjectDto> getAll() throws SQLException {
        ArrayList<SubjectDto> subjectDtos = new ArrayList<>();
        ArrayList<Subject> subjects = subjectDao.getAll();

        for (Subject subject : subjects) {
            subjectDtos.add(new SubjectDto(subject.getSubjectId(),subject.getSubjectName(),subject.getSubDesc(),subject.getSemesterId()));
        }
        return subjectDtos;
    }

    @Override
    public String getNextId() throws SQLException {
        String id =  subjectDao.getNextId();
        String subString = id.substring(3);
        int nextId = Integer.parseInt(subString)+1;
        return String.format("SUB%03d",nextId);
    }

    @Override
    public String getSemsterName(String id) throws SQLException {
        return subjectDao.getSemsterName(id);
    }

    @Override
    public ObservableList<String> getAllSubjectIds() throws SQLException {
        return subjectDao.getAllSubjectIds();
    }

    @Override
    public String getSubjectName(String id) throws SQLException {
        return subjectDao.getSubjectName(id);
    }
}
