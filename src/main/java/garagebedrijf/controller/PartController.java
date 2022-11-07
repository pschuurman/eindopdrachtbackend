package garagebedrijf.controller;

import garagebedrijf.dto.CustomerDto;
import garagebedrijf.dto.PartDto;
import garagebedrijf.service.PartService;
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

    @PutMapping("/parts/{id}")
    public PartDto updatePart(@PathVariable("id") Long id, @RequestBody PartDto partDto) {
        partService.updatePart(id, partDto);
        return partDto;
    }

    @DeleteMapping("/parts/{id}")
    public ResponseEntity<Object> deletePart(@PathVariable Long id){
        partService.deletePart(id);
        return ResponseEntity.noContent().build();
    }

}
