package garagebedrijf.repository;

import garagebedrijf.model.Carpaper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarPaperRepository extends JpaRepository<Carpaper, Long> {
    Carpaper findByFileName(String fileName);
}
