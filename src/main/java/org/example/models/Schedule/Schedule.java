package org.example.models.Schedule;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
public class Schedule {
    private int id;
    private int staffId;
    private String dayOfWeek;
    private Time startTime;
    private Time endTime;

}

