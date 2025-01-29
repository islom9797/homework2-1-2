package org.islom.homework212.controller;

import org.islom.homework212.entity.LanguageSupport;
import org.islom.homework212.payload.ApiResponse;
import org.islom.homework212.payload.LanguageSupportDto;
import org.islom.homework212.service.LanguageSupportService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LanguageSupportController {
    LanguageSupportService service;

    @GetMapping("/language")
    public List<LanguageSupport> get() {
        return service.findAll();
    }

    @GetMapping("language/{id}")
    public LanguageSupport getById(@PathVariable int id) {
        return service.findById(id);
    }


    @PostMapping("/language")
    public ApiResponse save(@RequestBody LanguageSupportDto dto) {

        return service.save(dto);
    }

    @PutMapping("/language/{id}")
    public HttpEntity<ApiResponse> update(@PathVariable int id, @RequestBody LanguageSupportDto dto) {

        ApiResponse apiResponse = service.update(dto, id);
        return ResponseEntity.status(apiResponse.isSuccess()
                ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/language/{id}")
    public ApiResponse delete(@PathVariable int id) {
        return service.deleteById(id);
    }
}
