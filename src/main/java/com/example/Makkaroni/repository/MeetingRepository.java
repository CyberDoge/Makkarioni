package com.example.Makkaroni.repository;

import com.example.Makkaroni.models.Meeting;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MeetingRepository extends MongoRepository<Meeting, String> {
    public Meeting findMeetingById(String id);
    public List<Meeting> findAll();
    public List<Meeting> findAllByDateLessThanEqualAndDateGreaterThanEqual(Date startDate, Date endDate);
    public List<Meeting> findAllByDateBetween(Date startDate, Date endDate);
}
