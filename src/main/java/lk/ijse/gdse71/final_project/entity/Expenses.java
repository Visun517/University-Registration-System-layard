package lk.ijse.gdse71.final_project.entity;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Expenses {
    private String expenseId;
    private String description;
    private double amount;
    private Date expenseDate;
    private String category;
}
