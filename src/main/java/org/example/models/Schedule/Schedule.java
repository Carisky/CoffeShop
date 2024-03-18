package org.example.models.Schedule;

import lombok.*;

import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Schedule {
    private int id;
    private int staffId;
    private String dayOfWeek;
    private Time startTime;
    private Time endTime;

}

