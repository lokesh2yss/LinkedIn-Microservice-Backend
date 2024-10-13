package com.codingshuttle.linkedin.connection_service.controllers;

import com.codingshuttle.linkedin.connection_service.entities.Person;
import com.codingshuttle.linkedin.connection_service.services.ConnectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/core")
public class ConnectionController {
    private final ConnectionService connectionService;

    @GetMapping(path = "/{userId}/first-degree")
    public ResponseEntity<List<Person>> getFirstConnections(@PathVariable Long userId) {
        List<Person> persons = connectionService.getFirstDegreeConnections(userId);
        return ResponseEntity.ok(persons);
    }

    @GetMapping(path = "/{userId}/second-degree")
    public ResponseEntity<List<Person>> getSecondConnections(@PathVariable Long userId) {
        List<Person> persons = connectionService.getSecondDegreeConnections(userId);
        return ResponseEntity.ok(persons);
    }

    @GetMapping(path = "/{userId}/third-degree")
    public ResponseEntity<List<Person>> getThirdConnections(@PathVariable Long userId) {
        List<Person> persons = connectionService.getThirdDegreeConnections(userId);
        return ResponseEntity.ok(persons);
    }
}
