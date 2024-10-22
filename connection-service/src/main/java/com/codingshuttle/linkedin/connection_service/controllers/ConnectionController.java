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
    public ResponseEntity<List<Person>> getFirstConnections() {
        List<Person> persons = connectionService.getFirstDegreeConnections();
        return ResponseEntity.ok(persons);
    }

    //@GetMapping(path = "/{userId}/second-degree")
    @GetMapping(path = "/second-degree")
    public ResponseEntity<List<Person>> getSecondConnections() {
        List<Person> persons = connectionService.getSecondDegreeConnections();
        return ResponseEntity.ok(persons);
    }

    //@GetMapping(path = "/{userId}/third-degree")
    @GetMapping(path = "/third-degree")
    public ResponseEntity<List<Person>> getThirdConnections() {
        List<Person> persons = connectionService.getThirdDegreeConnections();
        return ResponseEntity.ok(persons);
    }

    @PostMapping(path = "/request/{requestedUserId}")
    public ResponseEntity<Boolean> sendConnectionRequest(@PathVariable Long requestedUserId) {
        return ResponseEntity.ok(connectionService.sendConnectionRequest(requestedUserId));
    }
}
