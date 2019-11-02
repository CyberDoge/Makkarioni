package com.example.Makkaroni.models;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

public class Meeting {
    @Id
    public String id;
    private Date date;
    private List<Integer> users;
    private float[] coordinates;

    public Meeting(Date date, List<Integer> users, float... coordinates) {
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

    public List<Integer> getUsers() {
        return users;
    }
}