package com.eindopdracht.garagebedrijf.service;

import com.eindopdracht.garagebedrijf.dto.InvoiceDto;
import com.eindopdracht.garagebedrijf.exceptions.RecordNotFoundException;
import com.eindopdracht.garagebedrijf.model.Invoice;
import com.eindopdracht.garagebedrijf.repository.InvoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<InvoiceDto> getAllInvoices() {
        List<Invoice> invoiceList = invoiceRepository.findAll();
        List<InvoiceDto> invoiceDtoList = new ArrayList<>();

        for (Invoice invoice : invoiceList) {
            InvoiceDto dto = transferToDto(invoice);
            invoiceDtoList.add(dto);
        }
        return invoiceDtoList;
    }

    public InvoiceDto getInvoiceById(Long id) {

        if (invoiceRepository.findById(id).isPresent()) {
            Invoice dto = invoiceRepository.findById(id).get();
            return transferToDto(dto);
        } else {
            throw new RecordNotFoundException("geen factuur gevonden");
        }
    }

    private InvoiceDto transferToDto(Invoice invoice) {
        InvoiceDto dto = new InvoiceDto();

        dto.setId(invoice.getId());
        dto.setPartName(invoice.getPartName());
        dto.setPrice(invoice.getPrice());

        return dto;

    }

    public Long createInvoice(InvoiceDto invoiceDto) {
        Invoice invoice = new Invoice();
        invoice.setPartName(invoiceDto.partName);
        invoice.setPrice(invoiceDto.price);

        Invoice savedInvoice = this.invoiceRepository.save(invoice);

        return savedInvoice.getId();
    }

    public void deleteInvoice(@RequestBody Long id) {
        invoiceRepository.deleteById(id);
    }
}









