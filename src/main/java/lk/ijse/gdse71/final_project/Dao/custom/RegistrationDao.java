package lk.ijse.gdse71.final_project.Dao.custom;

import lk.ijse.gdse71.final_project.Dao.CrudDAO;
import lk.ijse.gdse71.final_project.dto.RegistrationDto;
import lk.ijse.gdse71.final_project.entity.Registartion;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RegistrationDao extends CrudDAO<Registartion> {
     ArrayList<Registartion> getAllRegistrations() throws SQLException;
     String getNextRegistrationId() throws SQLException;
     boolean registerStudent(Registartion registration) throws SQLException;
     boolean updateStudent(Registartion registration) throws SQLException;
     boolean delete(String id) throws SQLException;
     boolean paymentReduce(String studentId, double amount) throws SQLException;
     boolean addPayment(String studentId, double amount) throws SQLException;
     double getAmountDue(String id) throws SQLException;
}
