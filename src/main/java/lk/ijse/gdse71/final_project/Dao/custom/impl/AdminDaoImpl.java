package lk.ijse.gdse71.final_project.Dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.CrudUtil;
import lk.ijse.gdse71.final_project.Dao.custom.AdminDao;
import lk.ijse.gdse71.final_project.dto.AdminDto;
import lk.ijse.gdse71.final_project.dto.tm.AdminTm;
import lk.ijse.gdse71.final_project.entity.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDaoImpl implements AdminDao {

    public AdminDto checkAdmin(String userName) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM admin WHERE User_name = ?", userName);

        while (resultSet.next()){
            AdminDto adminDto = new AdminDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
            return adminDto;
        }
        return null;
    }

    public String getNextId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select Admin_id from admin order by Admin_id desc limit 1;");

        while (resultSet.next()){
            return resultSet.getString(1);
        }
        return "A001";
    }

    public ArrayList<Admin> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select  * from admin;");
        ArrayList<Admin> admins = new ArrayList<>();

        while (resultSet.next()){
            Admin admins1 = new Admin(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
            admins.add(admins1);
        }
        return admins;
    }

    public boolean save(Admin admin) throws SQLException {
        return CrudUtil.execute("insert into admin values(?,?,?,?,?)",
                admin.getAdminId(),
                admin.getUserName(),
                admin.getEmail(),
                admin.getPassword(),
                admin.getRole()
        );
    }

    public boolean Update(Admin admin) throws SQLException {
        return CrudUtil.execute("UPDATE admin SET User_name = ?, Email = ?, Password = ?,Role = ? WHERE Admin_id = ?;",
                admin.getUserName(),
                admin.getEmail(),
                admin.getPassword(),
                admin.getRole(),
                admin.getAdminId()
        );
    }

    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("delete from admin where Admin_id = ?;",id);
    }

    public String getAdminPass(String pass) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select Password from admin where Password = ?;",pass);

        while (resultSet.next()){
            String id  = resultSet.getString(1);
            return  id;
        }
        return null;
    }


}
