package com.eindopdracht.garagebedrijf.repository;

import com.eindopdracht.garagebedrijf.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long> {
}
