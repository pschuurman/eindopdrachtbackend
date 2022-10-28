package com.eindopdracht.garagebedrijf.controller;

import com.eindopdracht.garagebedrijf.dto.RepairDto;
import com.eindopdracht.garagebedrijf.service.RepairService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RepairController {

    private final RepairService repairService;

    public RepairController(RepairService repairService) {
        this.repairService = repairService;
    }

    @GetMapping("/repairs")
    public ResponseEntity<List<RepairDto>> getAllRepairs(@RequestParam(value = "partName", required = false) Optional partName) {
        List<RepairDto> repairDtoList;
        repairDtoList = repairService.getAllRepairs();

        return ResponseEntity.ok().body(repairDtoList);
    }

    @GetMapping("/repairs/{id}")
    public ResponseEntity<RepairDto> getRepair(@PathVariable("id")Long id) {
        RepairDto repair = repairService.getRepairById(id);
        return ResponseEntity.ok().body(repair);
    }

    @PostMapping("/repairs")
    public ResponseEntity<Long> createRepair(@RequestBody RepairDto repairDto) {
        Long repairId = repairService.createRepair(repairDto);
        return new ResponseEntity<>(repairId, HttpStatus.CREATED);
    }

}
