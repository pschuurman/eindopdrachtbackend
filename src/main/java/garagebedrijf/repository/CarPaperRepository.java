package com.eindopdracht.garagebedrijf.repository;

import com.eindopdracht.garagebedrijf.model.Carpaper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarPaperRepository extends JpaRepository<Carpaper, Long> {
    Carpaper findByFileName(String fileName);
}
