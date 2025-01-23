package lk.ijse.gdse71.final_project.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.dto.ExpenseDto;
import lk.ijse.gdse71.final_project.dto.tm.ExpenseTm;

import java.sql.SQLException;

public interface ExpensesBO extends SuperBO{
     ObservableList<ExpenseTm> getAll() throws SQLException;
     String getNextId() throws SQLException;
     boolean save(ExpenseDto expenseDto) throws SQLException;
     boolean Update(ExpenseDto expenseDto) throws SQLException;
     boolean delete(String id) throws SQLException;
}
