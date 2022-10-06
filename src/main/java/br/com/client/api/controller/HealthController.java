package br.com.client.api.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Health Check")
@RestController
public class HealthController {

    @GetMapping("/ping")
    public String index() {
        return "PONG";
    }

}
