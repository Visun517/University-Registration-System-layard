package lk.ijse.gdse71.final_project.Dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.CrudDAO;
import lk.ijse.gdse71.final_project.dto.PaymentDto;
import lk.ijse.gdse71.final_project.dto.tm.PaymentTm;
import lk.ijse.gdse71.final_project.entity.Payment;

import java.sql.SQLException;

public interface PaymentDao extends CrudDAO<Payment> {
     ObservableList<PaymentTm> getAllPayments() throws SQLException;
     String getNextPaymentId() throws SQLException;
     boolean paymentSave(Payment payment) throws SQLException;
     boolean paymentDelete(String paymentId, String studentId, double amount) throws SQLException;
     boolean paymentUpdate(Payment payment, double balance) throws SQLException;
     double getAmountDue(String studentId) throws SQLException;
}
