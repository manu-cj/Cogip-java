package com.cogip.cogip.mappers;

import com.cogip.cogip.dto.InvoiceDTO;
import com.cogip.cogip.models.Invoice;

public class InvoiceMapper {
    public static InvoiceDTO toDTO(Invoice entity) {
        if (entity == null) {
            return null;
        }
        return InvoiceDTO.builder()
                .id(entity.getId())
                .number(entity.getNumber())
                .date(entity.getDate())
                .companyDTO(entity.getCompany() != null ? CompanyMapper.toDTO(entity.getCompany()) : null)
                .companyType(entity.getCompanyType())
                .contactDTO(entity.getContact() != null ? ContactMapper.toDTO(entity.getContact()) : null)
                .build();
    }

    public static Invoice toEntity(InvoiceDTO dto) {
        if (dto == null) {
            return null;
        }
        return Invoice.builder()
                .id(dto.getId())
                .number(dto.getNumber())
                .date(dto.getDate())
                .company(dto.getCompanyDTO() != null ? CompanyMapper.toEntity(dto.getCompanyDTO()) : null)
                .companyType(dto.getCompanyType())
                .contact(dto.getContactDTO() != null ? ContactMapper.toEntity(dto.getContactDTO()) : null)
                .build();
    }
}