package de.cdiag.aws.codeguru.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health")
public class HealthCheckController {

    private final Logger LOGGER = LoggerFactory.getLogger(HealthCheckController.class);

    @GetMapping(value = "/check")
    public String healthCheck() {
        LOGGER.info("Healthcheck");
        return "I am alive!";
    }

}
