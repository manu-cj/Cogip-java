package com.cogip.cogip.mappers;

import com.cogip.cogip.dto.CompanyDTO;
import com.cogip.cogip.models.Company;

public class CompanyMapper {

    public static CompanyDTO toDTO(Company company) {
        if (company == null) return null;

        return CompanyDTO.builder()
                .id(company.getId())
                .name(company.getName())
                .vatNumber(company.getVatNumber())
                .type(company.getType())
                .invoices(company.getInvoices().stream()
                        .map(InvoiceMapper::toDTO)
                        .toList())
                .contacts(company.getContacts().stream()
                        .map(ContactMapper::toDTO)
                        .toList())
                .build();
    }

    public static Company toEntity(CompanyDTO dto) {
        if (dto == null) return null;

        Company company = new Company();
        company.setId(dto.getId());
        company.setName(dto.getName());
        company.setVatNumber(dto.getVatNumber());
        company.setType(dto.getType());
        // On laisse la gestion des listes à part pour éviter les problèmes de cascade
        return company;
    }
}
