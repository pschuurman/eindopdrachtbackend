package com.eindopdracht.garagebedrijf.controller;

import com.eindopdracht.garagebedrijf.dto.PartDto;
import com.eindopdracht.garagebedrijf.service.PartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PartController {

    private final PartService service;

    public PartController(PartService service) {
        this.service = service;
    }

    @PostMapping("/parts")
    public ResponseEntity<Long> createPart(@RequestBody PartDto partDto) {
        Long partId = service.createPart(partDto);
        return new ResponseEntity<>(partId, HttpStatus.CREATED);
    }

}
