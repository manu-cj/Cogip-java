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
    private String firstName;
    private String lastName;
    private String email;
    private String companyName;
    private List<InvoiceSummaryDTO> invoices;
}

