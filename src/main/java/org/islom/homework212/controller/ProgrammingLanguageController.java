package org.islom.homework212.controller;

import jakarta.validation.Valid;
import org.islom.homework212.entity.ProgrammingLanguage;
import org.islom.homework212.payload.ApiResponse;
import org.islom.homework212.service.ProgrammingLanguageService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/programmingLanguage")
public class ProgrammingLanguageController {

    ProgrammingLanguageService service;

    @GetMapping("/programmingLanguage")
    public List<ProgrammingLanguage> get() {
        return service.findAll();
    }

    @GetMapping("programmingLanguage/{id}")
    public ProgrammingLanguage getById(@PathVariable int id) {
        return service.findById(id);
    }


    @PostMapping("/programmingLanguage")
    public ApiResponse save(@RequestBody ProgrammingLanguage programmingLanguage) {

        return service.save(programmingLanguage);
    }

    @PutMapping("/programmingLanguage/{id}")
    public HttpEntity<ApiResponse> update(@PathVariable int id, @Valid @RequestBody ProgrammingLanguage programmingLanguage) {

        ApiResponse apiResponse = service.update(programmingLanguage, id);
        return ResponseEntity.status(apiResponse.isSuccess()
                ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/programmingLanguage/{id}")
    public ApiResponse delete(@PathVariable int id) {
        return service.deleteById(id);
    }
}
