package lk.ijse.gdse71.final_project.entity;

import lombok.*;

import java.sql.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Registartion {
    private String registrationId;
    private String studentId;
    private String courseId;
    private Date registrationDate;
    private double amountDue;
}
