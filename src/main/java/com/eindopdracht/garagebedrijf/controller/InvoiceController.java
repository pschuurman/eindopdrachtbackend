package com.eindopdracht.garagebedrijf.controller;

import com.eindopdracht.garagebedrijf.service.InvoiceService;
import com.eindopdracht.garagebedrijf.dto.InvoiceDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) { this.invoiceService = invoiceService; }

    @GetMapping("/invoices")
    public ResponseEntity<List<InvoiceDto>> getAllInvoices(@RequestParam(value = "partName", required = false) Optional<String> partName) {
        List<InvoiceDto> invoiceDtoList;
        invoiceDtoList = invoiceService.getAllInvoices();

        return ResponseEntity.ok().body(invoiceDtoList);
    }

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

    @DeleteMapping("invoices/{id}")
    public ResponseEntity<Object> deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/invoices/{id}/{carId}")
    public void assignCarToInvoice(@PathVariable("id") Long id, @PathVariable("carId") Long carId) {
        invoiceService.assignCarToInvoice(id, carId);
    }

}
