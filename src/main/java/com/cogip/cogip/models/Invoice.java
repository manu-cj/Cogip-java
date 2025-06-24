package com.cogip.cogip.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoice")
public class Invoice {
    //les factures doivent être répertoriées avec les informations suivantes :
    // numéro, date, société liée à la facture, type de société ( fournisseur ou client ), contact lié à la facture
    @Id
    @GeneratedValue
    private UUID id;
    private long number;
    private java.time.LocalDate date;
    private User user;

    // Société liée à la facture
    @jakarta.persistence.ManyToOne
    private Company company;

    // Type de société (FOURNISSEUR ou CLIENT)
    private String typeSociete;

    // Contact lié à la facture
    @jakarta.persistence.ManyToOne
    private Contact contact;
}
