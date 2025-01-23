package lk.ijse.gdse71.final_project.Dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.CrudUtil;
import lk.ijse.gdse71.final_project.Dao.custom.ExpenseDao;
import lk.ijse.gdse71.final_project.dto.ExpenseDto;
import lk.ijse.gdse71.final_project.entity.Expenses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExpenseDaoImpl implements ExpenseDao {
    public ArrayList<Expenses> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select * from expense;");
        ArrayList<Expenses> expense = new ArrayList<>();

        while (resultSet.next()){
            Expenses expense1 = new Expenses(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getDate(4),
                    resultSet.getString(5)
            );
            expense.add(expense1);
        }
        return expense;
    }

    public String getNextId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select expense_id from expense order by expense_id desc limit 1;");

        while (resultSet.next()){
            String id = resultSet.getString(1);
            String subString = id.substring(1);
            int nextId = Integer.parseInt(subString)+1;
            return String.format("E%03d",nextId);
        }
        return "E001";
    }

    public boolean save(Expenses expense) throws SQLException {
        return CrudUtil.execute("insert into expense values(?,?,?,?,?)",
                expense.getExpenseId(),
                expense.getDescription(),
                expense.getAmount(),
                expense.getExpenseDate(),
                expense.getCategory()
        );
    }

    public boolean Update(Expenses expense) throws SQLException {
        return CrudUtil.execute("UPDATE expense  SET description = ?, amount = ?, expense_date = ?,category = ? WHERE expense_id = ?;",
                expense.getDescription(),
                expense.getAmount(),
                expense.getExpenseDate(),
                expense.getCategory(),
                expense.getExpenseId()
         );
    }

    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("delete from expense where expense_id = ?;",id);
    }
}
