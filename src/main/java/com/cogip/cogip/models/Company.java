package com.cogip.cogip.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="company")
public class Company {
    //les entreprises doivent toujours être répertoriées avec au moins les informations suivantes :
    // nom de l'entreprise, numéro de TVA de l'entreprise, liste des factures liées à l'entreprise, liste des contacts travaillant pour l'entreprise.
    @Id
    @GeneratedValue
    private UUID id;
    private String companyName;
    private String tvaNumber;

    @OneToMany(mappedBy = "company")
    private List<Invoice> invoices;

    @OneToMany(mappedBy = "company")
    private List<Contact> contact;
}
