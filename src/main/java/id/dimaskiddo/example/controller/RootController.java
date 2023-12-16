package id.dimaskiddo.example.controller;

import id.dimaskiddo.example.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value="/", produces={"application/json"})
@RestController
public class RootController {

    @Autowired
    private final Response response;

    public RootController(Response response) {
        this.response = response;
    }

    @GetMapping
    public ResponseEntity<String> getIndex() {
        return response.responseSuccess("Spring Boot Example REST is running");
    }
}
