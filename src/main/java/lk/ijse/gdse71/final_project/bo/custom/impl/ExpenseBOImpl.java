package lk.ijse.gdse71.final_project.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.DAOFactory;
import lk.ijse.gdse71.final_project.Dao.custom.ExpenseDao;
import lk.ijse.gdse71.final_project.bo.custom.ExpensesBO;
import lk.ijse.gdse71.final_project.dto.ExpenseDto;
import lk.ijse.gdse71.final_project.dto.tm.ExpenseTm;
import lk.ijse.gdse71.final_project.entity.Expenses;

import java.sql.SQLException;
import java.util.ArrayList;

public class ExpenseBOImpl implements ExpensesBO {
    ExpenseDao expenseDao = (ExpenseDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EXPENSES);

    @Override
    public ObservableList<ExpenseTm> getAll() throws SQLException {
        ObservableList<ExpenseTm> expenseTms = FXCollections.observableArrayList();
        ArrayList<Expenses> expenses = expenseDao.getAll();

        for (Expenses expense : expenses) {
            expenseTms.add(new ExpenseTm(expense.getExpenseId(), expense.getDescription(), expense.getAmount(), expense.getExpenseDate(), expense.getCategory()));
        }
        return expenseTms;
    }

    @Override
    public String getNextId() throws SQLException {
        return expenseDao.getNextId();
    }

    @Override
    public boolean save(ExpenseDto expenseDto) throws SQLException {
        return expenseDao.save(new Expenses(expenseDto.getExpenseId(),
                expenseDto.getDesc(),
                expenseDto.getAmount(),
                expenseDto.getDate(),
                expenseDto.getCategory()));
    }

    @Override
    public boolean Update(ExpenseDto expenseDto) throws SQLException {
        return expenseDao.Update(new Expenses(expenseDto.getExpenseId(),
                expenseDto.getDesc(),
                expenseDto.getAmount(),
                expenseDto.getDate(),
                expenseDto.getCategory()));
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return expenseDao.delete(id);
    }
}
