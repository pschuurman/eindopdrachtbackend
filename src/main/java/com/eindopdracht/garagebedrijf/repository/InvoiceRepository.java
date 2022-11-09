package com.eindopdracht.garagebedrijf.repository;

import com.eindopdracht.garagebedrijf.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
