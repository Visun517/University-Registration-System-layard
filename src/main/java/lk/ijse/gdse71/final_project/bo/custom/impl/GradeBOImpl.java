package lk.ijse.gdse71.final_project.bo.custom.impl;

import lk.ijse.gdse71.final_project.Dao.DAOFactory;
import lk.ijse.gdse71.final_project.Dao.custom.GradeDao;
import lk.ijse.gdse71.final_project.bo.custom.GradeBO;
import lk.ijse.gdse71.final_project.dto.GradeDto;
import lk.ijse.gdse71.final_project.entity.Grade;

import java.sql.SQLException;
import java.util.ArrayList;

public class GradeBOImpl implements GradeBO {

    GradeDao gradeDao = (GradeDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.GRADE);
    @Override
    public ArrayList<GradeDto> getAll() throws SQLException {
        ArrayList<GradeDto> gradeDtos = new ArrayList<>();
        ArrayList<Grade> grade =gradeDao.getAll();

        for (Grade grade1 : grade){
            gradeDtos.add(new GradeDto(grade1.getGradeId(),grade1.getExamId(),grade1.getGrade(),grade1.getDesc(),grade1.getStudentId()));
        }
        return gradeDtos;
    }

    @Override
    public String getNextId() throws SQLException {
        String id =  gradeDao.getNextId();
        String subString = id.substring(1);
        int nextId = Integer.parseInt(subString)+1;
        return String.format("G%03d",nextId);
    }

    @Override
    public boolean save(GradeDto gradeDto) throws SQLException {
        return gradeDao.save(new Grade(gradeDto.getGradeId(),gradeDto.getExamId(),gradeDto.getGrade(),gradeDto.getDesc(),gradeDto.getStudentId()));
    }

    @Override
    public boolean Update(GradeDto gradeDto) throws SQLException {
        return gradeDao.Update(new Grade(gradeDto.getGradeId(),gradeDto.getExamId(),gradeDto.getGrade(),gradeDto.getDesc(),gradeDto.getStudentId()));
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return gradeDao.delete(id);
    }
}
