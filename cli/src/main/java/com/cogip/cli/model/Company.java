package com.cogip.cli.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    private UUID id;
    private String name;
    private String vatNumber;
    private String type;
    private List<Invoice> invoices;
    private List<Contact> contacts;
}
