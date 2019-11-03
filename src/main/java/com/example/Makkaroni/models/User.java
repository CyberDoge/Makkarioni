package com.example.Makkaroni.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.stream.Stream;

public class User {
    @Id
    private String id;
    @JsonIgnore
    private String username;
    private String firstName;
    private String lastName;
    private String about;
    private Float[] coordinates;
    private Grade grade;

    public User(String id, String username, String firstName, String lastName, String about, Float[] coordinates, int grade) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.about = about;
        this.coordinates = coordinates;
        if (grade == 0) {
            this.grade = Grade.Dude;
        } else {
            setGrade(grade);
        }
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAbout() {
        return about;
    }

    public Float[] getCoordinates() {
        return coordinates;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = Stream.of(Grade.values()).filter(g -> g.id == grade).findFirst()
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST));
    }
}