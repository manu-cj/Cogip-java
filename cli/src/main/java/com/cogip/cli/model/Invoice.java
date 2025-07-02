package com.cogip.cli.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Invoice {
    private UUID id;
    private String number;
    private LocalDate date;
    private String companyName;
    private String companyType;
    private UUID contactId;
    private String contactFirstName;
    private String contactLastName;
}
