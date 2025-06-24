package com.cogip.cogip.mappers;

import com.cogip.cogip.dto.ContactDTO;
import com.cogip.cogip.models.Contact;

import java.util.stream.Collectors;

public class ContactMapper {

    public static ContactDTO toDTO(Contact entity) {
        if (entity == null) {
            return null;
        }

        return ContactDTO.builder()
                .id(entity.getId())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .email(entity.getEmail())
                .companyDTO(entity.getCompany() != null ? CompanyMapper.toDTO(entity.getCompany()) : null)
                .invoiceDTOS(entity.getInvoices() != null
                        ? entity.getInvoices().stream()
                        .map(InvoiceMapper::toDTO)
                        .toList()
                        : null)
                .build();
    }


    public static Contact toEntity(ContactDTO dto) {
        if (dto == null) {
            return  null;
        }

        return Contact.builder()
                .id(dto.getId())
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .email(dto.getEmail())
                .company(dto.getCompanyDTO() != null ? CompanyMapper.toEntity(dto.getCompanyDTO()) : null)
                .invoices(dto.getInvoiceDTOS() != null
                        ? dto.getInvoiceDTOS().stream()
                        .map(InvoiceMapper::toEntity)
                        .toList()
                        : null)
                .build();
    }
}
