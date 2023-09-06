package dev.danvega.controller;

import dev.danvega.domain.User;
import dev.danvega.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint to retrieve a list of all users
    @GetMapping("/list")
    public Iterable<User> list() {
        return userService.list();
    }

    // Endpoint to retrieve a list of users sorted by a specific field
    @GetMapping("/list/{field}")
    public List<User> listWithSort(@PathVariable String field) {
        return userService.recordsWithSorting(field);
    }

    // Endpoint to retrieve a paginated list of users sorted by a specific field
    @GetMapping("/list/{offset}/{pageSize}/{field}")
    public List<User> listWithPaginationAndSort(@PathVariable int offset, @PathVariable int pageSize,
                                                @PathVariable String field) {
        return userService.recordsWithPaginationAndSorting(offset, pageSize, field);
    }
}
