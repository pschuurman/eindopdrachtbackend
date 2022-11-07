package garagebedrijf.service;

import garagebedrijf.dto.CustomerDto;
import garagebedrijf.dto.PartDto;
import garagebedrijf.exceptions.RecordNotFoundException;
import garagebedrijf.model.Customer;
import garagebedrijf.model.Part;
import garagebedrijf.repository.PartRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartService {

    private final PartRepository partRepository;

    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    public List<PartDto> getAllParts() {
        List<Part> partList = partRepository.findAll();
        List<PartDto> partDtoList = new ArrayList<>();

        for (Part part : partList) {
            PartDto dto = transferToDto(part);
            partDtoList.add(dto);
        }
        return partDtoList;
    }

    public PartDto getPartById(Long id) {

        if (partRepository.findById(id).isPresent()) {
            Part dto = partRepository.findById(id).get();
            return transferToDto(dto);
        } else {
            throw new RecordNotFoundException("geen onderdeel gevonden");
        }
    }

    private PartDto transferToDto(Part part) {
        PartDto dto = new PartDto();

        dto.setId(part.getId());
        dto.setPartName(part.getPartName());
        dto.setPrice(part.getPrice());

        return dto;
    }



    public Long createPart(PartDto partDto) {
        Part part = new Part();
        part.setPartName(partDto.partName);
        part.setPrice(partDto.price);

        Part savedPart = this.partRepository.save(part);

        return savedPart.getId();

    }

    public void updatePart(Long id, PartDto partDto) {
        if(!partRepository.existsById(id)) {
            throw new RecordNotFoundException("No part found");
        }
        Part storedPart = partRepository.findById(id).orElse(null);
        storedPart.setId(partDto.getId());
        storedPart.setPartName(partDto.getPartName());
        storedPart.setPrice(partDto.getPrice());
        partRepository.save(storedPart);
    }


    public void deletePart(@RequestBody Long id) {
        partRepository.deleteById(id);
    }

}
