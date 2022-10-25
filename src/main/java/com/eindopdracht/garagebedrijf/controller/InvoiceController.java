package com.eindopdracht.garagebedrijf.controller;

import com.eindopdracht.garagebedrijf.dto.InvoiceDto;
import com.eindopdracht.garagebedrijf.service.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvoiceController {

    private final InvoiceService service;

    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    @PostMapping("/invoices")
    public ResponseEntity<Long> createInvoice(@RequestBody InvoiceDto invoiceDto) {
        Long invoiceId = service.createInvoice(invoiceDto);
        return new ResponseEntity<>(invoiceId, HttpStatus.CREATED);
    }


}
