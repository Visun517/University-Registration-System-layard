package lk.ijse.gdse71.final_project.Dao.custom.impl;

import lk.ijse.gdse71.final_project.Dao.CrudUtil;
import lk.ijse.gdse71.final_project.Dao.custom.RegistrationDao;
import lk.ijse.gdse71.final_project.dto.RegistrationDto;
import lk.ijse.gdse71.final_project.entity.Registartion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegistrationDaoImpl implements RegistrationDao {

    public ArrayList<Registartion> getAllRegistrations() throws SQLException {

        ResultSet resultSet = CrudUtil.execute("select * from registration");
        ArrayList<Registartion> registrations = new ArrayList<>();

        while (resultSet.next()){
            Registartion registration = new Registartion(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4),
                    resultSet.getDouble(5)
            );
            registrations.add(registration);
        }
        return registrations;
    }

    public String getNextRegistrationId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select Registration_id from registration order by Registration_id desc limit 1;");

        while (resultSet.next()){
            return  resultSet.getString(1);

        }
        return "R001";
    }

    public boolean registerStudent(Registartion registration) throws SQLException {
        System.out.println(registration.getStudentId());
        return CrudUtil.execute("INSERT INTO registration (Registration_id, Student_id, Course_id, Registration_date,Amount_due) VALUES (?,?,?,?,?);",
                registration.getRegistrationId(),
                registration.getStudentId(),
                registration.getCourseId(),
                registration.getRegistrationDate(),
                registration.getAmountDue()

        );
    }

    public boolean updateStudent(Registartion registration) throws SQLException {
        return CrudUtil.execute("UPDATE registration SET Student_id = ?, Course_id = ?,Registration_date = ?,Amount_due = ? WHERE Registration_id = ?;",
                registration.getStudentId(),
                registration.getCourseId(),
                registration.getRegistrationDate(),
                registration.getAmountDue(),
                registration.getRegistrationId()
        );
    }

    @Override
    public String getNextId() throws SQLException {
        return "";
    }

    @Override
    public ArrayList<Registartion> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean save(Registartion dto) throws SQLException {
        return false;
    }

    @Override
    public boolean Update(Registartion dto) throws SQLException {
        return false;
    }

    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE FROM registration WHERE Registration_id = ? ;",id);
    }

    public boolean paymentReduce(String studentId, double amount) throws SQLException {
        return CrudUtil.execute("UPDATE registration  SET Amount_due = Amount_due - ? WHERE Student_id = ?;",
                amount,
                studentId
        );
    }

    public boolean addPayment(String studentId, double amount) throws SQLException {
        return CrudUtil.execute("UPDATE registration  SET Amount_due = Amount_due + ? WHERE Student_id = ?;",
                amount,
                studentId
        );
    }

    public double getAmountDue(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select Amount_due from registration where Student_id = ?;",id);

        while (resultSet.next()){
            return resultSet.getDouble(1);
        }
        return 0;
    }
}
