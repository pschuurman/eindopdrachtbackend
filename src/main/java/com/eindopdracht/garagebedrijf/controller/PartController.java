package com.eindopdracht.garagebedrijf.controller;

import com.eindopdracht.garagebedrijf.dto.PartDto;
import com.eindopdracht.garagebedrijf.service.PartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PartController {

    private final PartService partService;

    public PartController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping("/parts")
    public ResponseEntity<List<PartDto>> getAllParts(@RequestParam(value = "partName") Optional<String> partName) {
        List<PartDto> partDtoList;
        partDtoList = partService.getAllParts();

        return ResponseEntity.ok().body(partDtoList);
    }

    @GetMapping("/parts/{id}")
    public ResponseEntity<PartDto> getPart(@PathVariable("id")Long id) {
        PartDto part = partService.getPartById(id);

        return ResponseEntity.ok().body(part);
    }

    @PostMapping("/parts")
    public ResponseEntity<Long> createPart(@RequestBody PartDto partDto) {
        Long partId = partService.createPart(partDto);
        return new ResponseEntity<>(partId, HttpStatus.CREATED);
    }

}
