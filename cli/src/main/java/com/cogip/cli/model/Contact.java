package com.cogip.cli.model;

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
public class Contact {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String companyName;
    private List<Invoice> invoices;


}
