package lk.ijse.gdse71.final_project.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Admin {
    private String adminId;
    private String userName;
    private String email;
    private String password;
    private String role;
}
