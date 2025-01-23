package lk.ijse.gdse71.final_project.bo.custom.impl;

import lk.ijse.gdse71.final_project.Dao.DAOFactory;
import lk.ijse.gdse71.final_project.Dao.custom.AdminDao;
import lk.ijse.gdse71.final_project.bo.custom.AdminBO;
import lk.ijse.gdse71.final_project.dto.AdminDto;
import lk.ijse.gdse71.final_project.entity.Admin;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminBOImpl implements AdminBO {

    AdminDao adminDao = (AdminDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ADMIN);

    @Override
    public AdminDto checkAdmin(String userName) throws SQLException {
        return adminDao.checkAdmin(userName);
    }

    @Override
    public String getNextId() throws SQLException {
        String id =  adminDao.getNextId();
        String subString = id.substring(1);
        int next = Integer.parseInt(subString) + 1;
        return String.format("A%03d", next);
    }

    @Override
    public ArrayList<AdminDto> getAll() throws SQLException {
        ArrayList<AdminDto> adminDtos = new ArrayList<>();
        ArrayList<Admin> admins = adminDao.getAll();

        for (Admin admin : admins) {
            adminDtos.add(new AdminDto(admin.getAdminId(), admin.getUserName(), admin.getEmail(), admin.getPassword(), admin.getRole()));
        }
        return adminDtos;
    }

    @Override
    public boolean save(AdminDto adminDto) throws SQLException {

        return adminDao.save(new Admin(
                                        adminDto.getAdminId(),
                                        adminDto.getUserName(),
                                        adminDto.getEmail(),
                                        adminDto.getPassword(),
                                        adminDto.getRole())
        );
    }

    @Override
    public boolean Update(AdminDto adminDto) throws SQLException {
        return adminDao.Update(new Admin(
                                        adminDto.getAdminId(),
                                        adminDto.getUserName(),
                                        adminDto.getEmail(),
                                        adminDto.getPassword(),
                                        adminDto.getRole()));
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return adminDao.delete(id);
    }

    @Override
    public String getAdminPass(String pass) throws SQLException {
        return adminDao.getAdminPass(pass);
    }
}
