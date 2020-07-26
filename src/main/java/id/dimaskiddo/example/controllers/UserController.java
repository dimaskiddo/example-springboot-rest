package id.dimaskiddo.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import id.dimaskiddo.example.models.User;
import id.dimaskiddo.example.services.UserService;
import id.dimaskiddo.example.Responses;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@RequestMapping(value="/api/v1/user", produces={"application/json"})
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    private final Responses responses;

    @Autowired
    public UserController(UserService userService, Responses responses) {
        this.userService = userService;
        this.responses = responses;
    }

    @GetMapping
    public ResponseEntity<String> getUsers() {
        return responses.responseSuccessWithData(userService.getUsers());
    }

    @PostMapping
    public ResponseEntity<String> addUser(@Valid @NotNull @RequestBody User user) {
        if (userService.addUser(user) == 1) {
            return responses.responseCreated();
        }

        throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, "Failed to Add User"
        );
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<String> getUserById(@PathVariable("id") UUID id) {
        User user = userService.getUserById(id).orElse(null);
        if (user != null) {
            return responses.responseSuccessWithData(user);
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User Id Not Found"
        );
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<String> udpateUserById(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody User user) {
        if (userService.updateUserById(id, user) == 1) {
            return responses.responseUpdated();
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User Id Not Found"
        );
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") UUID id) {
        if (userService.deleteUserById(id) == 1) {
            return responses.responseSuccess("");
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User Id Not Found"
        );
    }
}
