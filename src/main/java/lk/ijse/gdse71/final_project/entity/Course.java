package lk.ijse.gdse71.final_project.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Course {
    private String courseId;
    private String courseName;
    private int duration;
    private double payment;
}
