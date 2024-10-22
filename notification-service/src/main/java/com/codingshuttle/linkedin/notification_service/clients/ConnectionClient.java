package com.codingshuttle.linkedin.notification_service.clients;

import com.codingshuttle.linkedin.notification_service.dto.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "connection-service", path="/connections/core")
public interface ConnectionClient {
    @GetMapping(path = "/first-degree")
    List<PersonDto> getFirstConnections(@RequestHeader("X-User-Id") Long userId);

    @GetMapping(path = "/second-degree")
    List<PersonDto> getSecondConnections();

    @GetMapping(path = "/third-degree")
    List<PersonDto> getThirdConnections();
}
