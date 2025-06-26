package com.cogip.cogip.dto;

import com.cogip.cogip.models.CompanyType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceDTO {
    private UUID id;
    private String number;
    private LocalDate date;
    private String companyName;
    private CompanyType companyType;
    private String contactFirstName;
    private String contactLastName;


}
