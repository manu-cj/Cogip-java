package com.cogip.cogip.dto;

import jakarta.validation.constraints.Email;
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
public class ContactDTO {
    private UUID id;
    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @Email
    private String email;

    private CompanyDTO companyDTO;

    private List<InvoiceDTO> invoiceDTOS;





}
