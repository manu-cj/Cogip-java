package com.cogip.cogip.dto;

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
    @NotBlank
    private String companyName;

    @NotBlank
    private String tvaNumber;

    private List<InvoiceDTO> invoiceDTOS;

    private List<ContactDTO> contactDTOS;
}
