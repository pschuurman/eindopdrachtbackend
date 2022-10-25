package com.eindopdracht.garagebedrijf.service;

import com.eindopdracht.garagebedrijf.dto.RepairDto;
import com.eindopdracht.garagebedrijf.model.Repair;
import com.eindopdracht.garagebedrijf.repository.RepairRepository;
import org.springframework.stereotype.Service;

@Service
public class RepairService {

    private final RepairRepository repos;

    public RepairService(RepairRepository repos) {
        this.repos = repos;
    }

    public Long createRepair(RepairDto repairDto) {
        Repair repair = new Repair();
        repair.setPartName(repairDto.partName);
        repair.setPrice(repairDto.price);

        Repair savedRepair = this.repos.save(repair);

        return savedRepair.getId();


    }

}
