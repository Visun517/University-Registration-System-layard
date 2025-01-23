package lk.ijse.gdse71.final_project.Dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.CrudUtil;
import lk.ijse.gdse71.final_project.Dao.custom.LectureDao;
import lk.ijse.gdse71.final_project.dto.LectureDto;
import lk.ijse.gdse71.final_project.entity.Lecture;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LectureDaoImpl implements LectureDao {
    public ArrayList<Lecture> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select * from lecture");
        ArrayList<Lecture> lectures = new ArrayList<>();

        while (resultSet.next()){
            Lecture lecture = new Lecture(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            lectures.add(lecture);
        }
        return lectures;
    }

    public String getNextId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select Lecture_id from lecture order by Lecture_id desc limit 1;");

        while (resultSet.next()){
            return resultSet.getString(1);

        }
        return "L001";
    }

    public boolean save(Lecture lecture) throws SQLException {
        return CrudUtil.execute("insert into lecture values(?,?,?,?)",
                lecture.getLectureId(),
                lecture.getSubjectId(),
                lecture.getLectureTitle(),
                lecture.getName()
        );
    }

    public boolean Update(Lecture lecture) throws SQLException {
        return CrudUtil.execute("UPDATE lecture SET Subject_id = ?, Lecture_title = ?,name = ? WHERE Lecture_id = ?;",
                lecture.getSubjectId(),
                lecture.getLectureTitle(),
                lecture.getName(),
                lecture.getLectureId()
        );
    }

    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("delete from lecture where Lecture_id =?;",id);
    }

    public ObservableList<String> getAllLecturesIds() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select Lecture_id from Lecture;");
        ObservableList<String> ids = FXCollections.observableArrayList();

        while (resultSet.next()){
            String id = resultSet.getString(1);
            ids.add(id);
        }
        return ids;
    }
    public  String getLectureName(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select name from lecture where lecture_id = ?;",id);

        while (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

}
