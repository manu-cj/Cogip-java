package com.cogip.cogip.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="company")
@Builder
public class Company {
    //les entreprises doivent toujours être répertoriées avec au moins les informations suivantes :
    // nom de l'entreprise, numéro de TVA de l'entreprise, liste des factures liées à l'entreprise, liste des contacts travaillant pour l'entreprise.
    @Id
    @GeneratedValue
    private UUID id;
    private String name;

    @Column(unique = true)
    private String vatNumber;

    @Enumerated(EnumType.STRING)
    private CompanyType type; // CLIENT or PROVIDER

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Contact> contacts = new ArrayList<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Invoice> invoices = new ArrayList<>();
}
