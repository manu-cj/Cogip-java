package com.cogip.cogip.repository;

import com.cogip.cogip.models.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {
    @Override
    Optional<Invoice> findById(UUID uuid);
    Page<Invoice> findByNumberStartingWith(String number, Pageable pageable);
}
