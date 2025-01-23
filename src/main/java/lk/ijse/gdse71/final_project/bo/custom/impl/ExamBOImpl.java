package lk.ijse.gdse71.final_project.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.DAOFactory;
import lk.ijse.gdse71.final_project.Dao.custom.ExamDao;
import lk.ijse.gdse71.final_project.bo.custom.ExamBO;
import lk.ijse.gdse71.final_project.dto.ExamDto;
import lk.ijse.gdse71.final_project.entity.Exam;

import java.sql.SQLException;
import java.util.ArrayList;

public class ExamBOImpl implements ExamBO {

    ExamDao examDao = (ExamDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EXAM);

    @Override
    public ArrayList<ExamDto> getAll() throws SQLException {
        ArrayList<ExamDto> examDtos = new ArrayList<>();
        ArrayList<Exam> all = examDao.getAll();

        for (Exam exam : all) {
            examDtos.add(new ExamDto(exam.getExamId(),exam.getSubjectId(),exam.getDesc(),exam.getDate()));
        }
        return examDtos;
    }

    @Override
    public String getNextId() throws SQLException {
        String id =  examDao.getNextId();
        String subString = id.substring(1);
        int nextId = Integer.parseInt(subString)+1;
        return String.format("E%03d",nextId);
    }

    @Override
    public boolean save(ExamDto examDto) throws SQLException {
        return examDao.save(new Exam(examDto.getExamId(),examDto.getSubjectId(),examDto.getDesc(),examDto.getDate()));
    }

    @Override
    public ObservableList<String> getAllExamIds() throws SQLException {
        return examDao.getAllExamIds();
    }

    @Override
    public boolean Update(ExamDto examDto) throws SQLException {
        return examDao.Update(new Exam(examDto.getExamId(),examDto.getSubjectId(),examDto.getDesc(),examDto.getDate()));
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return examDao.delete(id);
    }
}
