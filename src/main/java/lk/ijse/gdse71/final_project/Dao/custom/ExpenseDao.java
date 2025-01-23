package lk.ijse.gdse71.final_project.Dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse71.final_project.Dao.CrudDAO;
import lk.ijse.gdse71.final_project.Dao.SuperDAO;
import lk.ijse.gdse71.final_project.dto.ExpenseDto;
import lk.ijse.gdse71.final_project.dto.tm.ExpenseTm;
import lk.ijse.gdse71.final_project.entity.Expenses;

import java.sql.SQLException;

public interface ExpenseDao extends CrudDAO<Expenses> {

}
