package org.islom.homework212.controller;

import org.islom.homework212.entity.Problems;
import org.islom.homework212.payload.ApiResponse;
import org.islom.homework212.payload.ProblemDto;
import org.islom.homework212.service.ProblemsService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProblemsController {
    ProblemsService service;

    @GetMapping("/problems")
    public List<Problems> get() {
        return service.findAll();
    }

    @GetMapping("problems/{id}")
    public Problems getById(@PathVariable int id) {
        return service.findById(id);
    }


    @PostMapping("/problems")
    public ApiResponse save(@RequestBody ProblemDto problemDto) {

        return service.save(problemDto);
    }

    @PutMapping("/problems/{id}")
    public HttpEntity<ApiResponse> update(@PathVariable int id, @RequestBody ProblemDto problemDto) {

        ApiResponse apiResponse = service.update(problemDto, id);
        return ResponseEntity.status(apiResponse.isSuccess()
                ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/problems/{id}")
    public ApiResponse delete(@PathVariable int id) {
        return service.deleteById(id);
    }
}
