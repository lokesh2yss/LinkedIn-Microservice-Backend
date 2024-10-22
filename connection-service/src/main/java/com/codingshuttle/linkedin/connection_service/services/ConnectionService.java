package com.codingshuttle.linkedin.connection_service.services;

import com.codingshuttle.linkedin.connection_service.auth.UserContextHolder;
import com.codingshuttle.linkedin.connection_service.entities.Person;
import com.codingshuttle.linkedin.connection_service.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConnectionService {
    private final PersonRepository personRepository;

    public List<Person> getFirstDegreeConnections() {
        Long userId = UserContextHolder.getCurrentUserId();
        log.info("Getting first degree connections for user with id: {}", userId);
        return personRepository.getFirstDegreeConnections(userId);
    }

    public List<Person> getSecondDegreeConnections() {
        Long userId = UserContextHolder.getCurrentUserId();
        log.info("Getting second degree connections for user with id: {}", userId);
        return personRepository.getSecondDegreeConnections(userId);
    }

    public List<Person> getThirdDegreeConnections() {
        Long userId = UserContextHolder.getCurrentUserId();
        log.info("Getting third degree connections for user with id: {}", userId);
        return personRepository.getThirdDegreeConnections(userId);
    }

    public Boolean sendConnectionRequest(Long receiverUserId) {
        Long senderUserId = UserContextHolder.getCurrentUserId();
        boolean connectionRequestExists = personRepository.connectionRequestExist(senderUserId, receiverUserId);
        if(connectionRequestExists) {
            throw new RuntimeException("Connection request already exists. Cannot resend another request");
        }

        boolean alreadyConnected = personRepository.alreadyConnected(senderUserId, receiverUserId);
        if(alreadyConnected) {
            throw new RuntimeException("Connection already exists, cannot send new connection request");
        }

        personRepository.createNewConnectionRequest(senderUserId, receiverUserId);

        return true;

    }
}
