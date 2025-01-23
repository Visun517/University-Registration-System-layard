package lk.ijse.gdse71.final_project.entity;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
    private String student_id;
    private String name;
    private String email;
    private String address;
    private String gender;
}
