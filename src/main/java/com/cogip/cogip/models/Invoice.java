package com.cogip.cogip.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

    @Column(unique = true)
    private String number;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    // Optionnel si le type ne peut pas être déduit de company
    @Enumerated(EnumType.STRING)
    private CompanyType type;
}
