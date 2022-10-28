package com.eindopdracht.garagebedrijf.controller;

import com.eindopdracht.garagebedrijf.dto.InvoiceDto;
import com.eindopdracht.garagebedrijf.service.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) { this.invoiceService = invoiceService; }

    @GetMapping("/invoices/{id}")
    public ResponseEntity<InvoiceDto> getInvoice(@PathVariable("id")Long id) {
        InvoiceDto invoice = invoiceService.getInvoiceById(id);
        return ResponseEntity.ok().body(invoice);
    }





    @PostMapping("/invoices")
    public ResponseEntity<Long> createInvoice(@RequestBody InvoiceDto invoiceDto) {
        Long invoiceId = invoiceService.createInvoice(invoiceDto);
        return new ResponseEntity<>(invoiceId, HttpStatus.CREATED);
    }


}
