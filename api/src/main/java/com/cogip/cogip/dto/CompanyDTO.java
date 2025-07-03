package com.cogip.cogip.dto;

import com.cogip.cogip.models.CompanyType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyDTO {
        private UUID id;
        private String name;
        private String vatNumber;
        private CompanyType type;
        private List<InvoiceDTO> invoices;
        private List<ContactDTO> contacts;

}
