package id.dimaskiddo.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.dimaskiddo.example.Responses;

@RequestMapping(value="/", produces={"application/json"})
@RestController
public class RootController {

    @Autowired
    private final Responses responses;

    public RootController(Responses responses) {
        this.responses = responses;
    }

    @GetMapping
    public ResponseEntity<String> getIndex() {
        return responses.responseSuccess("Spring Boot Example REST is running");
    }
}
