package com.eindopdracht.garagebedrijf.service;

import com.eindopdracht.garagebedrijf.dto.InvoiceDto;
import com.eindopdracht.garagebedrijf.model.Invoice;
import com.eindopdracht.garagebedrijf.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    private final InvoiceRepository repos;

    public InvoiceService(InvoiceRepository repos) {
        this.repos = repos;
    }

    public Long createInvoice(InvoiceDto invoiceDto) {
        Invoice invoice = new Invoice();
        invoice.setPartName(invoiceDto.partName);
        invoice.setPrice(invoiceDto.price);

        Invoice savedInvoice = this.repos.save(invoice);

        return savedInvoice.getId();
    }
}
