package id.dimaskiddo.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import id.dimaskiddo.example.model.User;
import id.dimaskiddo.example.service.UserService;
import id.dimaskiddo.example.Response;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@RequestMapping(value="/api/v1/user", produces={"application/json"})
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    private final Response response;

    @Autowired
    public UserController(UserService userService, Response response) {
        this.userService = userService;
        this.response = response;
    }

    @GetMapping
    public ResponseEntity<String> getUsers() {
        return response.responseSuccessWithData(userService.getUsers());
    }

    @PostMapping
    public ResponseEntity<String> addUser(@Valid @NotNull @RequestBody User user) {
        if (userService.addUser(user) == 1) {
            return response.responseCreated();
        }

        throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, "Failed to Add User"
        );
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<String> getUserById(@PathVariable("id") UUID id) {
        User user = userService.getUserById(id).orElse(null);
        if (user != null) {
            return response.responseSuccessWithData(user);
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User Id Not Found"
        );
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<String> udpateUserById(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody User user) {
        if (userService.updateUserById(id, user) == 1) {
            return response.responseUpdated();
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User Id Not Found"
        );
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") UUID id) {
        if (userService.deleteUserById(id) == 1) {
            return response.responseSuccess("");
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User Id Not Found"
        );
    }
}
