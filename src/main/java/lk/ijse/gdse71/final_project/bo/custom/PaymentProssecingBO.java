package lk.ijse.gdse71.final_project.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.dto.PaymentDto;
import lk.ijse.gdse71.final_project.dto.RegistrationDto;
import lk.ijse.gdse71.final_project.dto.tm.PaymentTm;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentProssecingBO extends SuperBO {
     ObservableList<PaymentTm> getAllPayments() throws SQLException;
     String getNextPaymentId() throws SQLException;
     boolean paymentSave(PaymentDto paymentDto) throws SQLException;
     boolean paymentDelete(String paymentId, String studentId, double amount) throws SQLException;
     boolean paymentUpdate(PaymentDto paymentDto) throws SQLException;
     double getAmountDuePayment(String studentId) throws SQLException;
     ArrayList<RegistrationDto> getAllRegistrations() throws SQLException;
     String getNextRegistrationId() throws SQLException;
     boolean registerStudent(RegistrationDto registrationDto) throws SQLException;
     boolean updateStudent(RegistrationDto registrationDto) throws SQLException;
     boolean delete(String id) throws SQLException;
     boolean paymentReduce(String studentId, double amount) throws SQLException;
     boolean addPayment(String studentId, double amount) throws SQLException;
     double getAmountDueRegistration(String id) throws SQLException;

}
