package lk.ijse.gdse71.final_project.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.DAOFactory;
import lk.ijse.gdse71.final_project.Dao.custom.PaymentDao;
import lk.ijse.gdse71.final_project.Dao.custom.RegistrationDao;
import lk.ijse.gdse71.final_project.bo.custom.PaymentProssecingBO;
import lk.ijse.gdse71.final_project.db.DBConnection;
import lk.ijse.gdse71.final_project.dto.PaymentDto;
import lk.ijse.gdse71.final_project.dto.RegistrationDto;
import lk.ijse.gdse71.final_project.dto.tm.PaymentTm;
import lk.ijse.gdse71.final_project.entity.Payment;
import lk.ijse.gdse71.final_project.entity.Registartion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentProssecingBOImpl implements PaymentProssecingBO {

    PaymentDao paymentDao =(PaymentDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);
    RegistrationDao registrationDao = (RegistrationDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.REGISTRATION);


    @Override
    public ObservableList<PaymentTm> getAllPayments() throws SQLException {
        return paymentDao.getAllPayments();
    }

    @Override
    public String getNextPaymentId() throws SQLException {
        String id =  paymentDao.getNextPaymentId();
        String subString = id.substring(1);
        int nextId = Integer.parseInt(subString)+1;
        return String.format("P%03d",nextId);
    }

    @Override
    public boolean paymentSave(PaymentDto paymentDto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

            boolean isSaved = paymentDao.paymentSave(new Payment(paymentDto.getPaymentId(),paymentDto.getStudentId(),paymentDto.getStatus(),paymentDto.getPayType(),paymentDto.getReferenceNum(),paymentDto.getAmount(),paymentDto.getPaymentDate()));

            if (isSaved){
                System.out.println(isSaved);
                boolean isPaymentReduce = registrationDao.paymentReduce(paymentDto.getStudentId(),paymentDto.getAmount());

                if (isPaymentReduce){
                    connection.commit();
                    return true;
                }

            }
            connection.rollback();
            return false;

        }catch (Exception e){
            e.printStackTrace();
            connection.rollback();
            return false;

        }finally {

            connection.setAutoCommit(true);
        }
    }

    @Override
    public boolean paymentDelete(String paymentId, String studentId, double amount) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

            boolean isDelete = paymentDao.paymentDelete(paymentId,studentId,amount);
            if (isDelete){

                boolean isPaymentReduce = registrationDao.addPayment(studentId,amount);

                if (isPaymentReduce){
                    connection.commit();
                    return true;
                }

            }
            connection.rollback();
            return false;

        }catch (Exception e){
            e.printStackTrace();
            connection.rollback();
            return false;

        }finally {

            connection.setAutoCommit(true);
        }
    }

    @Override
    public boolean paymentUpdate(PaymentDto paymentDto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

            double amountDue = paymentDao.getAmountDue(paymentDto.getStudentId());

            double balance = paymentDto.getAmount() - amountDue  ;


            boolean isUpdate = paymentDao.paymentUpdate(new Payment(paymentDto.getPaymentId(),
                    paymentDto.getStudentId(),
                    paymentDto.getStatus(),
                    paymentDto.getPayType(),
                    paymentDto.getReferenceNum(),
                    paymentDto.getAmount(),paymentDto.getPaymentDate()),
                    balance);

            if (isUpdate){
                boolean isUpdateAmount;
                if (paymentDto.getAmount() < 0){
                    isUpdateAmount = registrationDao.addPayment(paymentDto.getStudentId(),balance);
                }else {
                    isUpdateAmount = registrationDao.paymentReduce(paymentDto.getStudentId(),balance);
                }
                if (isUpdateAmount){
                    connection.commit();
                    return true;
                }


            }
            connection.rollback();
            return false;
        }catch (Exception e){
            e.printStackTrace();
            connection.rollback();
            return false;

        }finally {

            connection.setAutoCommit(true);
        }    }

    @Override
    public double getAmountDuePayment(String studentId) throws SQLException {
        return paymentDao.getAmountDue(studentId);
    }

    @Override
    public ArrayList<RegistrationDto> getAllRegistrations() throws SQLException {
        ArrayList<RegistrationDto> registrations = new ArrayList<>();
        ArrayList<Registartion> registartions = registrationDao.getAllRegistrations();
        for (Registartion registartion : registartions) {
            registrations.add(new RegistrationDto(registartion.getRegistrationId(),
                    registartion.getStudentId(),
                    registartion.getCourseId(),
                    registartion.getRegistrationDate(),
                    registartion.getAmountDue()));
        }
        return registrations;
    }

    @Override
    public String getNextRegistrationId() throws SQLException {
        String id = registrationDao.getNextRegistrationId();
        String subString = id.substring(1);
        int nextId = Integer.parseInt(subString)+1;
        return String.format("R%03d",nextId);
    }

    @Override
    public boolean registerStudent(RegistrationDto registrationDto) throws SQLException {
        return registrationDao.registerStudent(new Registartion(registrationDto.getRegistrationId(),registrationDto.getStudentId(),registrationDto.getCourseId(),registrationDto.getRegistrationDate(),registrationDto.getAmountDue()));
    }

    @Override
    public boolean updateStudent(RegistrationDto registrationDto) throws SQLException {
        return registrationDao.updateStudent(new Registartion(registrationDto.getRegistrationId(),registrationDto.getStudentId(),registrationDto.getCourseId(),registrationDto.getRegistrationDate(),registrationDto.getAmountDue()));
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return registrationDao.delete(id);
    }

    @Override
    public boolean paymentReduce(String studentId, double amount) throws SQLException {
        return registrationDao.paymentReduce(studentId,amount);
    }

    @Override
    public boolean addPayment(String studentId, double amount) throws SQLException {
        return registrationDao.addPayment(studentId,amount);
    }

    @Override
    public double getAmountDueRegistration(String id) throws SQLException {
        return registrationDao.getAmountDue(id);
    }

}

