package com.codingshuttle.linkedin.connection_service.controllers;

import com.codingshuttle.linkedin.connection_service.entities.Person;
import com.codingshuttle.linkedin.connection_service.services.ConnectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/core")
public class ConnectionController {
    private final ConnectionService connectionService;

    @GetMapping(path = "/first-degree")
    public ResponseEntity<List<Person>> getFirstConnections(@RequestHeader("X-User-Id") Long userId) {
        List<Person> persons = connectionService.getFirstDegreeConnections(userId);
        return ResponseEntity.ok(persons);
    }

    //@GetMapping(path = "/{userId}/second-degree")
    @GetMapping(path = "/second-degree")
    public ResponseEntity<List<Person>> getSecondConnections(@RequestHeader("X-User-Id") Long userId) {
        List<Person> persons = connectionService.getSecondDegreeConnections(userId);
        return ResponseEntity.ok(persons);
    }

    //@GetMapping(path = "/{userId}/third-degree")
    @GetMapping(path = "/third-degree")
    public ResponseEntity<List<Person>> getThirdConnections(@RequestHeader("X-User-Id") Long userId) {
        List<Person> persons = connectionService.getThirdDegreeConnections(userId);
        return ResponseEntity.ok(persons);
    }
}
