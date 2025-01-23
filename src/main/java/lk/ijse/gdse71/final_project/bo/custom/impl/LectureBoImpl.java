package lk.ijse.gdse71.final_project.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.DAOFactory;
import lk.ijse.gdse71.final_project.Dao.custom.LectureDao;
import lk.ijse.gdse71.final_project.bo.custom.LectureBO;
import lk.ijse.gdse71.final_project.dto.LectureDto;
import lk.ijse.gdse71.final_project.entity.Lecture;

import java.sql.SQLException;
import java.util.ArrayList;

public class LectureBoImpl implements LectureBO {

    LectureDao lectureDao = (LectureDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.LECTURE);

    @Override
    public ArrayList<LectureDto> getAll() throws SQLException {
        ArrayList<LectureDto> lectureDtos = new ArrayList<>();
        ArrayList<Lecture> lectures = lectureDao.getAll();

        for (Lecture lecture : lectures) {
            lectureDtos.add(new LectureDto(lecture.getLectureId(), lecture.getSubjectId(), lecture.getLectureTitle(), lecture.getName()));
        }
        return lectureDtos;
    }

    @Override
    public String getNextId() throws SQLException {
        String id = lectureDao.getNextId();
        String subString = id.substring(1);
        int nextId = Integer.parseInt(subString)+1;
        return String.format("L%03d",nextId);
    }

    @Override
    public boolean Save(LectureDto lectureDto) throws SQLException {
        return lectureDao.save(new Lecture(lectureDto.getLectureId(), lectureDto.getSubjectId(), lectureDto.getLecTitle(), lectureDto.getName()));
    }

    @Override
    public boolean Update(LectureDto lectureDto) throws SQLException {
        return lectureDao.Update(new Lecture(
                lectureDto.getLectureId(),
                lectureDto.getSubjectId(),
                lectureDto.getLecTitle(),
                lectureDto.getName())
        );
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return lectureDao.delete(id);
    }

    @Override
    public ObservableList<String> getAllLecturesIds() throws SQLException {
        return lectureDao.getAllLecturesIds();
    }

    @Override
    public String getLectureName(String id) throws SQLException {
        return lectureDao.getLectureName(id);
    }
}
