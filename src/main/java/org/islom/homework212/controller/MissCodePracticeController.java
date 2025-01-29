package org.islom.homework212.controller;

import org.islom.homework212.entity.MissCodePratice;
import org.islom.homework212.entity.Problems;
import org.islom.homework212.payload.ApiResponse;
import org.islom.homework212.payload.MissCodePracticeDto;
import org.islom.homework212.payload.ProblemDto;
import org.islom.homework212.service.MissCodePracticeService;
import org.islom.homework212.service.ProblemsService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MissCodePracticeController {
    MissCodePracticeService service;

    @GetMapping("/practice")
    public List<MissCodePratice> get() {
        return service.findAll();
    }

    @GetMapping("practice/{id}")
    public MissCodePratice getById(@PathVariable int id) {
        return service.findById(id);
    }


    @PostMapping("/practice")
    public ApiResponse save(@RequestBody MissCodePracticeDto dto) {

        return service.save(dto);
    }

    @PutMapping("/practice/{id}")
    public HttpEntity<ApiResponse> update(@PathVariable int id, @RequestBody MissCodePracticeDto dto) {

        ApiResponse apiResponse = service.update(dto, id);
        return ResponseEntity.status(apiResponse.isSuccess()
                ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/practice/{id}")
    public ApiResponse delete(@PathVariable int id) {
        return service.deleteById(id);
    }
}
