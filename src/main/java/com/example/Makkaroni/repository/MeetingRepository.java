package com.example.Makkaroni.repository;

import com.example.Makkaroni.models.Meeting;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MeetingRepository extends MongoRepository<Meeting, String> {
    public Meeting findMeetingById(String id);
    public List<Meeting> findAll();
}
