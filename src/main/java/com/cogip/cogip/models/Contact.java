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
@Table(name = "contact")
@Builder
public class Contact {
    //les contacts doivent être répertoriés avec les informations suivantes :
    // prénom et nom, email, nom de l'entreprise où travaille la personne, liste de toutes les factures liées.
    @Id
    @GeneratedValue
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
    private List<Invoice> invoices = new ArrayList<>();
}
