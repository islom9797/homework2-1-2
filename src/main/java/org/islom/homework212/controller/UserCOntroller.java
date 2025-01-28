package org.islom.homework212.controller;

import jakarta.validation.Valid;
import org.islom.homework212.entity.User;
import org.islom.homework212.payload.ApiResponse;
import org.islom.homework212.service.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserCOntroller {

    UserService service;



    @GetMapping("/users")
    public List<User> getCustomers() {
        return service.findAll();
    }

    @GetMapping("users/{id}")
    public User getCustomer(@PathVariable int id) {
        return service.findById(id);
    }


    @PostMapping("/users")
    public ApiResponse addCustomer( @RequestBody User user) {

        return service.save(user);
    }
    @PutMapping("/users/{id}")
    public HttpEntity<ApiResponse> updateCustomer(@PathVariable int id, @Valid @RequestBody User user) {

        ApiResponse apiResponse = service.update(user,id);
        return ResponseEntity.status(apiResponse.isSuccess()
                ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/users/{id}")
    public ApiResponse deleteCustomer(@PathVariable int id) {
        return service.deleteById(id);
    }


}
