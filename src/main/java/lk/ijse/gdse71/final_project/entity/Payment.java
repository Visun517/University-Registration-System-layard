package lk.ijse.gdse71.final_project.entity;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Payment {
    private String paymentId;
    private String studentId;
    private String status;
    private String payType;
    private String referenceNum;
    private double amount;
    private Date paymentDate;
}
