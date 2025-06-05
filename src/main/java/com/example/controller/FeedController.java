package com.example.controller;

import com.example.model.Feed;
import com.example.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/feeds")
@CrossOrigin(origins = "*")
public class FeedController {

    @Autowired
    private FeedRepository feedRepository;

    @PostMapping
    public ResponseEntity<Map<String, String>> addFeed(@RequestBody Feed feed) {
        try {
            feedRepository.save(feed);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Feed record added successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error adding feed record: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<List<Feed>> getFeedsByEmail(@RequestParam String email) {
        try {
            List<Feed> feeds = feedRepository.findByEmail(email);
            return ResponseEntity.ok(feeds);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}