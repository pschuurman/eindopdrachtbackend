package garagebedrijf.controller;

import garagebedrijf.dto.InvoiceDto;
import garagebedrijf.service.InvoiceService;
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
    // test relatie invoice aan auto
    @PutMapping("/invoices/{id}/car")
    public void assignCarToInvoice(@PathVariable("id") Long id, @RequestBody InvoiceDto invoiceDto) {
        invoiceService.assignCarToInvoice(id, invoiceDto.id);
    }

    @DeleteMapping("invoices/{id}")
    public ResponseEntity<Object> deleteInvoice(@RequestBody Long id) {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }

}
