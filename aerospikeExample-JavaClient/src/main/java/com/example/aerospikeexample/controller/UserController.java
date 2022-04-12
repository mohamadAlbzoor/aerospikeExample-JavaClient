package com.example.aerospikeexample.controller;
import com.example.aerospikeexample.exception.ApiRequestException;
import com.example.aerospikeexample.model.User;
import com.example.aerospikeexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("domo")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{key}")
    public User getUser(@PathVariable String key) {
        try {
            return userService.findAUser(key);
        }
        catch (Exception exc) {
            throw new ApiRequestException("User doesnt found");
        }
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping("/users/{key}")
    public String create(@RequestBody User user, @PathVariable String key) {
        return userService.create( user,key);
    }

    @DeleteMapping("/users/{key}")
    public String delete(@PathVariable String key) {
        return userService.delete(key);
    }

    @PutMapping("/users/{key}")
    public String update(@PathVariable String key,@RequestBody Map<String,Object> body) {
        return userService.update(key,body.get("Salary").toString());
    }
}
