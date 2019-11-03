package com.example.Makkaroni.models;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

public class Meeting {
    @Id
    private String id;
    private Date date;
    private List<String> users;
    private float[] coordinates;

    public Meeting(Date date, List<String> users, float... coordinates) {
        this.date = date;
        this.users = users;
        this.coordinates = coordinates;
    }

    public Date getDate() {
        return date;
    }

    public float[] getCoordinates() {
        return coordinates;
    }

    public List<String> getUsers() {
        return users;
    }

    public void updateMeeting(Meeting meeting) {
        if (meeting.users != null)
            this.users = meeting.users;
        if (meeting.coordinates != null)
            this.coordinates = meeting.coordinates;
        if (meeting.date != null)
            this.date = meeting.date;
    }
}