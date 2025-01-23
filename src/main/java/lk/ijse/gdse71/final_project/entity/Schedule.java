package lk.ijse.gdse71.final_project.entity;
import lombok.*;
import java.sql.Date;


@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Schedule {
    private String schedulId;
    private String courseId;
    private String classRoomId;
    private String startTime;
    private String endTime;
    private String weekday;
    private Date date;

}
