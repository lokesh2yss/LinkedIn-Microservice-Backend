package com.codingshuttle.linkedin.post_service.clients;

import com.codingshuttle.linkedin.post_service.dto.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "connection-service", path="/connections/core")
public interface ConnectionClient {
    @GetMapping(path = "/first-degree")
    List<PersonDto> getFirstConnections();

    @GetMapping(path = "/second-degree")
    List<PersonDto> getSecondConnections();

    @GetMapping(path = "/third-degree")
    List<PersonDto> getThirdConnections();
}
