package org.islom.homework212.controller;

import org.islom.homework212.entity.Helps;
import org.islom.homework212.entity.LanguageSupport;
import org.islom.homework212.payload.ApiResponse;
import org.islom.homework212.payload.HelpsDto;
import org.islom.homework212.payload.LanguageSupportDto;
import org.islom.homework212.service.HelpsService;
import org.islom.homework212.service.LanguageSupportService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HelpsController {
    HelpsService service;

    @GetMapping("/helps")
    public List<Helps> get() {
        return service.findAll();
    }

    @GetMapping("helps/{id}")
    public Helps getById(@PathVariable int id) {
        return service.findById(id);
    }


    @PostMapping("/helps")
    public ApiResponse save(@RequestBody HelpsDto dto) {

        return service.save(dto);
    }

    @PutMapping("/helps/{id}")
    public HttpEntity<ApiResponse> update(@PathVariable int id, @RequestBody HelpsDto dto) {

        ApiResponse apiResponse = service.update(dto, id);
        return ResponseEntity.status(apiResponse.isSuccess()
                ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/helps/{id}")
    public ApiResponse delete(@PathVariable int id) {
        return service.deleteById(id);
    }
}
