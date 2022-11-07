package garagebedrijf.repository;

import garagebedrijf.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairRepository extends JpaRepository<Repair, Long> {
}
