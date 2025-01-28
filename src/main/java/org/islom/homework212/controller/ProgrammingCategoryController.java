package org.islom.homework212.controller;

import jakarta.validation.Valid;
import org.islom.homework212.entity.ProgrammingCategory;
import org.islom.homework212.entity.ProgrammingLanguage;
import org.islom.homework212.payload.ApiResponse;
import org.islom.homework212.payload.ProgrammingCategoryDto;
import org.islom.homework212.service.ProgrammingCategoryService;
import org.islom.homework212.service.ProgrammingLanguageService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProgrammingCategoryController {
    ProgrammingCategoryService service;

    @GetMapping("/programmingCategory")
    public List<ProgrammingCategory> get() {
        return service.findAll();
    }

    @GetMapping("programmingCategory/{id}")
    public ProgrammingCategory getById(@PathVariable int id) {
        return service.findById(id);
    }


    @PostMapping("/programmingCategory")
    public ApiResponse save(@RequestBody ProgrammingCategoryDto programmingCategoryDto) {

        return service.save(programmingCategoryDto);
    }

    @PutMapping("/programmingCategory/{id}")
    public HttpEntity<ApiResponse> update(@PathVariable int id, @RequestBody ProgrammingCategoryDto programmingCategoryDto) {

        ApiResponse apiResponse = service.update(programmingCategoryDto, id);
        return ResponseEntity.status(apiResponse.isSuccess()
                ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/programmingCategory/{id}")
    public ApiResponse delete(@PathVariable int id) {
        return service.deleteById(id);
    }
}
