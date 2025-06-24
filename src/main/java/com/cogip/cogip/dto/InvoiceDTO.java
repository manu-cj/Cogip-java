package com.cogip.cogip.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceDTO {
    private UUID id;
    @Min(0)
    private Long number;

    @DateTimeFormat
    private LocalDateTime date;

    private CompanyDTO companyDTO;

    @NotBlank
    private String companyType;

    private ContactDTO contactDTO;


}
