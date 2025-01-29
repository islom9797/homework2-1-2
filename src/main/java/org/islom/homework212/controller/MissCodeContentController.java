package org.islom.homework212.controller;

import org.islom.homework212.entity.MissCodeContent;
import org.islom.homework212.payload.ApiResponse;
import org.islom.homework212.payload.MissCodeContentDto;
import org.islom.homework212.service.MissCodeContentService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MissCodeContentController {
    MissCodeContentService service;

    @GetMapping("/misscodecontent")
    public List<MissCodeContent> get() {
        return service.findAll();
    }

    @GetMapping("misscodecontent/{id}")
    public MissCodeContent getById(@PathVariable int id) {
        return service.findById(id);
    }


    @PostMapping("/misscodecontent")
    public ApiResponse save(@RequestBody MissCodeContentDto dto) {

        return service.save(dto);
    }

    @PutMapping("/misscodecontent/{id}")
    public HttpEntity<ApiResponse> update(@PathVariable int id, @RequestBody MissCodeContentDto dto) {

        ApiResponse apiResponse = service.update(dto, id);
        return ResponseEntity.status(apiResponse.isSuccess()
                ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/practice/{id}")
    public ApiResponse delete(@PathVariable int id) {
        return service.deleteById(id);
    }
}
