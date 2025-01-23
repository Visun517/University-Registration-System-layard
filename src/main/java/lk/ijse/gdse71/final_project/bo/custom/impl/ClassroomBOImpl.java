package lk.ijse.gdse71.final_project.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.custom.ClassroomDao;
import lk.ijse.gdse71.final_project.Dao.custom.impl.ClassroomDaoImpl;
import lk.ijse.gdse71.final_project.bo.custom.ClassroomBO;

import java.sql.SQLException;

public class ClassroomBOImpl implements ClassroomBO {

    ClassroomDao classroomDao = new ClassroomDaoImpl();
    @Override
    public ObservableList<String> getAllClassroomIds() throws SQLException {
        return classroomDao.getAllClassroomIds();
    }
}
