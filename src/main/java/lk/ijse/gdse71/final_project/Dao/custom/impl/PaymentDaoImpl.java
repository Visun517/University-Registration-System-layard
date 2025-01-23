package lk.ijse.gdse71.final_project.Dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.CrudUtil;
import lk.ijse.gdse71.final_project.Dao.custom.PaymentDao;
import lk.ijse.gdse71.final_project.Dao.custom.RegistrationDao;
import lk.ijse.gdse71.final_project.db.DBConnection;
import lk.ijse.gdse71.final_project.dto.PaymentDto;
import lk.ijse.gdse71.final_project.dto.tm.PaymentTm;
import lk.ijse.gdse71.final_project.entity.Payment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDaoImpl implements PaymentDao {

    public ObservableList<PaymentTm> getAllPayments() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select * from payment;");
        ObservableList<PaymentTm> paymentTms = FXCollections.observableArrayList();
        while (resultSet.next()){
            PaymentTm paymentTm = new PaymentTm(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getDouble(6),
                    resultSet.getDate(7)
            );
            paymentTms.add(paymentTm);
        }
        return paymentTms;
    }

    public String getNextPaymentId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select Payment_id from payment order by Payment_id desc limit 1;");

        while (resultSet.next()){
            return resultSet.getString(1);

        }
        return "P001";
    }

    public boolean paymentSave(Payment payment) throws SQLException {

        return CrudUtil.execute("insert into payment values(?,?,?,?,?,?,?);",
                payment.getPaymentId(),
                payment.getStudentId(),
                payment.getStatus(),
                payment.getPayType(),
                payment.getReferenceNum(),
                payment.getAmount(),
                payment.getPaymentDate()
        );
    }

    public boolean paymentDelete(String paymentId, String studentId, double amount) throws SQLException {
        return CrudUtil.execute("delete from payment where Payment_id = ? ",
                paymentId
        );
    }

    public boolean paymentUpdate(Payment payment, double balance) throws SQLException {
        return CrudUtil.execute("UPDATE payment SET Student_id =?, Status = ?,Pay_type = ?,Reference_num = ?,Amount = ?,payment_date = ? WHERE Payment_id = ?;",
                payment.getStudentId(),
                payment.getStatus(),
                payment.getPayType(),
                payment.getReferenceNum(),
                payment.getAmount(),
                payment.getPaymentDate(),
                payment.getPaymentId()
        );
    }

    public double getAmountDue(String studentId) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select Amount from payment where Student_id = ? order by Reference_num desc limit 1;",studentId);

        while (resultSet.next()){
            return resultSet.getDouble(1);
        }
        return 0;
    }

    @Override
    public String getNextId() throws SQLException {
        return "";
    }

    @Override
    public ArrayList<Payment> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean save(Payment dto) throws SQLException {
        return false;
    }

    @Override
    public boolean Update(Payment dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }
}
