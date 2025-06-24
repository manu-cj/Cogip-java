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
@Table(name = "contact")
public class Contact {
    //les contacts doivent être répertoriés avec les informations suivantes :
    // prénom et nom, email, nom de l'entreprise où travaille la personne, liste de toutes les factures liées.
    @Id
    @GeneratedValue
    private UUID id;
    private String firstname;
    private String lastname;
    private String email;

    // Relation vers la société où travaille la personne
    @jakarta.persistence.ManyToOne
    private Company company;

    private User user;

    // Liste des factures liées à ce contact
    @OneToMany(mappedBy = "contact")
    private List<Invoice> invoices;
}
