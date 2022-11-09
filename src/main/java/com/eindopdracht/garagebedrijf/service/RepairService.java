package com.eindopdracht.garagebedrijf.service;

import com.eindopdracht.garagebedrijf.exceptions.RecordNotFoundException;
import com.eindopdracht.garagebedrijf.model.Repair;
import com.eindopdracht.garagebedrijf.repository.RepairRepository;
import com.eindopdracht.garagebedrijf.dto.RepairDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepairService {

    private final RepairRepository repairRepository;

    public RepairService(RepairRepository repairRepository) {
        this.repairRepository = repairRepository;
    }

    public List<RepairDto> getAllRepairs() {
        List<Repair> repairList = repairRepository.findAll();
        List<RepairDto> repairDtoList = new ArrayList<>();

        for (Repair repair : repairList) {
            RepairDto dto = transferToDto(repair);
            repairDtoList.add(dto);
        }
        return repairDtoList;
    }

    public RepairDto getRepairById(Long id) {

        if (repairRepository.findById(id).isPresent()) {
            Repair dto = repairRepository.findById(id).get();
            return transferToDto(dto);
        } else {
            throw new RecordNotFoundException("geen reparatie gevonden");
        }
    }

    private RepairDto transferToDto(Repair repair) {
        RepairDto dto = new RepairDto();

        dto.setId(repair.getId());
        dto.setPartName(repair.getPartName());
        dto.setPrice(repair.getPrice());

        return dto;
    }

    public Long createRepair(RepairDto repairDto) {
        Repair repair = new Repair();
        repair.setPartName(repairDto.partName);
        repair.setPrice(repairDto.price);

        Repair savedRepair = this.repairRepository.save(repair);

        return savedRepair.getId();

    }

    public void updateRepair(Long id, RepairDto repairDto) {
        if(!repairRepository.existsById(id)) {
            throw new RecordNotFoundException("No repair found");
        }
        Repair storedRepair = repairRepository.findById(id).orElse(null);
        storedRepair.setId(repairDto.getId());
        storedRepair.setPartName(repairDto.getPartName());
        storedRepair.setPrice(repairDto.getPrice());
        repairRepository.save(storedRepair);
    }

    public void deleteRepair(@RequestBody Long id) {
        repairRepository.deleteById(id);
    }

}
