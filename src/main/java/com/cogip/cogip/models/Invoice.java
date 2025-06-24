package com.cogip.cogip.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoice")
@Builder
public class Invoice {
    //les factures doivent être répertoriées avec les informations suivantes :
    // numéro, date, société liée à la facture, type de société ( fournisseur ou client ), contact lié à la facture
    @Id
    @GeneratedValue
    private UUID id;
    private long number;
    private LocalDateTime date;

    // Société liée à la facture
    @ManyToOne
    private Company company;

    // Type de société (FOURNISSEUR ou CLIENT)
    private String companyType;

    // Contact lié à la facture
    @ManyToOne
    private Contact contact;
}
