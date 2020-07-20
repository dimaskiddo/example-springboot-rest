package id.dimaskiddo.example.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import id.dimaskiddo.example.model.User;
import id.dimaskiddo.example.service.UserService;
import id.dimaskiddo.example.helper.ApiResponseHelper;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@RequestMapping(value="/api/v1/user", produces={"application/json"})
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    private final ApiResponseHelper apiResponseHelper;

    @Autowired
    public UserController(UserService userService, ApiResponseHelper apiResponseHelper) {
        this.userService = userService;
        this.apiResponseHelper = apiResponseHelper;
    }

    @GetMapping
    public ResponseEntity<String> getAllUser() {
        return apiResponseHelper.handleSuccessResponseWithData(userService.getAllUser());
    }

    @PostMapping
    public ResponseEntity<String> addUser(@Valid @NotNull @RequestBody User user) {
        if (userService.addUser(user) == 1) {
            return apiResponseHelper.handleCreatedResponse();
        }

        throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, "Failed to Add User"
        );
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<String> getUserById(@PathVariable("id") UUID id) {
        User user = userService.getUserById(id).orElse(null);
        if (user != null) {
            return apiResponseHelper.handleSuccessResponseWithData(user);
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User Id Not Found"
        );
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<String> udpateUserById(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody User user) {
        if (userService.updateUserById(id, user) == 1) {
            return apiResponseHelper.handleUpdatedResponse();
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User Id Not Found"
        );
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") UUID id) {
        if (userService.deleteUserById(id) == 1) {
            return apiResponseHelper.handleSuccessResponse();
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User Id Not Found"
        );
    }
}
