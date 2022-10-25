package com.eindopdracht.garagebedrijf.controller;

import com.eindopdracht.garagebedrijf.dto.RepairDto;
import com.eindopdracht.garagebedrijf.service.RepairService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RepairController {

    private final RepairService service;

    public RepairController(RepairService service) {
        this.service = service;
    }

    @PostMapping("/repairs")
    public ResponseEntity<Long> createRepair(@RequestBody RepairDto repairDto) {
        Long repairId = service.createRepair(repairDto);
        return new ResponseEntity<>(repairId, HttpStatus.CREATED);
    }

}
