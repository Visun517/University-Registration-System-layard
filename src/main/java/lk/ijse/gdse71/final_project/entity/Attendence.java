package lk.ijse.gdse71.final_project.entity;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Attendence {
    private String attendenceId;
    private String schedulId;
    private String studentId;
    private Date classDate;
    private String remark;
}
