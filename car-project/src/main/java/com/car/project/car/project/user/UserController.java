package com.car.project.car.project.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    public UserService service;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void createUser(@RequestBody User user) {
        service.create(user);
    }


    @PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteUser(@RequestBody User userId) {
        service.delete(userId.getUserId());
    }

    @GetMapping(value = "/login")
    @ResponseBody
    public User login(@RequestBody User user) {
        return service.login(user);
    }
}
