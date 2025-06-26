package com.cogip.cogip.mappers;

import com.cogip.cogip.dto.ContactDTO;
import com.cogip.cogip.dto.ContactSummaryDTO;
import com.cogip.cogip.models.Company;
import com.cogip.cogip.models.Contact;

public class ContactMapper {

    public static ContactDTO toDTO(Contact contact) {
        if (contact == null) return null;

        return ContactDTO.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .companyName(contact.getCompany().getName())
                .invoices(contact.getInvoices().stream()
                        .map(InvoiceMapper::toSummaryDTO)
                        .toList())
                .build();
    }

    public static ContactSummaryDTO toSummaryDTO(Contact contact) {
        if (contact == null) return null;

        return ContactSummaryDTO.builder()
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .build();
    }

    public static Contact toEntity(ContactDTO dto, Company company) {
        if (dto == null) return null;

        Contact contact = new Contact();
        contact.setId(dto.getId());
        contact.setFirstName(dto.getFirstName());
        contact.setLastName(dto.getLastName());
        contact.setEmail(dto.getEmail());
        contact.setCompany(company);
        return contact;
    }
}
