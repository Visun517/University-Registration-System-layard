package lk.ijse.gdse71.final_project.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Subject {
    private String subjectId;
    private String subjectName;
    private String subDesc;
    private String semesterId;
}
