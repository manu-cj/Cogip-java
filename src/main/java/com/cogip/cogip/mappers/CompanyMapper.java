package com.cogip.cogip.mappers;

import com.cogip.cogip.dto.CompanyDTO;
import com.cogip.cogip.models.Company;

public class CompanyMapper {
    public static CompanyDTO toDTO(Company entity) {
        if (entity == null) {
            return null;
        }

        return CompanyDTO.builder()
                .id(entity.getId())
                .companyName(entity.getCompanyName())
                .tvaNumber(entity.getTvaNumber())
                .invoiceDTOS(entity.getInvoices() != null ? entity.getInvoices().stream()
                        .map(InvoiceMapper::toDTO)
                        .toList() : null)
                .contactDTOS(entity.getContact() != null ? entity.getContact().stream()
                        .map(ContactMapper::toDTO)
                        .toList() : null)
                .build();
    }

    public static Company toEntity(CompanyDTO dto) {
        if (dto == null) {
            return null;
        }

        return Company.builder()
                .id(dto.getId())
                .companyName(dto.getCompanyName())
                .tvaNumber(dto.getTvaNumber())
                .invoices(dto.getInvoiceDTOS() != null
                        ? dto.getInvoiceDTOS().stream()
                        .map(InvoiceMapper::toEntity)
                        .toList()
                        : null)
                .contact(dto.getContactDTOS() != null ? dto.getContactDTOS().stream()
                        .map(ContactMapper::toEntity)
                        .toList() : null)
                .build();

    }
}
