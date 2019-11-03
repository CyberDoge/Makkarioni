package com.example.Makkaroni.controllers;

import com.example.Makkaroni.models.Meeting;
import com.example.Makkaroni.models.User;
import com.example.Makkaroni.repository.MeetingRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController()
public class MeetingController {
    private final MeetingRepository meetingRepository;

    public MeetingController(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    @GetMapping(path = "/api/meetings", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Meeting> getAllMeetings() {
        return meetingRepository.findAll();
    }

    @PostMapping(path = "/api/meetings", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addMeeting(@RequestBody Meeting meeting) {
        if (meeting == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("empty meeting");
        return ResponseEntity.status(HttpStatus.CREATED).body(meetingRepository.insert(meeting));
    }

    @DeleteMapping(path = "/api/meetings/{id}")
    public ResponseEntity deleteMeeting(@PathVariable String id) {
        meetingRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/api/meetings/{id}")
    public ResponseEntity updateMeeting(@PathVariable String id, @RequestBody Meeting meeting) {
        Meeting dbMeeting = meetingRepository.findMeetingById(id);
        if (dbMeeting == null) {
            return ResponseEntity.notFound().build();
        }
        dbMeeting.updateMeeting(meeting);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/api/meetings/{id}")
    public ResponseEntity joinToMeeting(@PathVariable String id, HttpSession session) {
        Meeting dbMeeting = meetingRepository.findMeetingById(id);
        if (dbMeeting == null) return ResponseEntity.notFound().build();
        dbMeeting.getUsers().add(((User) session.getAttribute("user")).getId());
        meetingRepository.save(dbMeeting);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/api/meetings/between")
    public List<Meeting> getMeetingsBetween(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.add(Calendar.HOUR_OF_DAY, 24);
        return meetingRepository.findAllByDateBetween(startDate, calendar.getTime());
    }
}
