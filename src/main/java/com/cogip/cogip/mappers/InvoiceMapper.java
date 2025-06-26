package com.cogip.cogip.mappers;

import com.cogip.cogip.dto.InvoiceDTO;
import com.cogip.cogip.dto.InvoiceSummaryDTO;
import com.cogip.cogip.models.Company;
import com.cogip.cogip.models.Contact;
import com.cogip.cogip.models.Invoice;
public class InvoiceMapper {

    public static InvoiceDTO toDTO(Invoice invoice) {
        if (invoice == null) return null;

        return InvoiceDTO.builder()
                .id(invoice.getId())
                .number(invoice.getNumber())
                .date(invoice.getDate())
                .companyName(invoice.getCompany().getName())
                .companyType(invoice.getCompany().getType())
                .contactFirstName(invoice.getContact().getFirstName())
                .contactLastName(invoice.getContact().getLastName())
                .build();
    }

    public static InvoiceSummaryDTO toSummaryDTO(Invoice invoice) {
        if (invoice == null) return null;

        return InvoiceSummaryDTO.builder()
                .number(invoice.getNumber())
                .date(invoice.getDate())
                .build();
    }

    public static Invoice toEntity(InvoiceDTO dto, Company company, Contact contact) {
        if (dto == null) return null;

        Invoice invoice = new Invoice();
        invoice.setNumber(dto.getNumber());
        invoice.setDate(dto.getDate());
        invoice.setCompany(company);
        invoice.setContact(contact);
        invoice.setType(company.getType());
        return invoice;
    }
}
