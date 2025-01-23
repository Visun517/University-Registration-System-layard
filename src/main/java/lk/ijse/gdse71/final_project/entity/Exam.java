package lk.ijse.gdse71.final_project.entity;


import lombok.*;

import java.sql.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Exam {
    private String examId;
    private String subjectId;
    private String desc;
    private Date date;
}
