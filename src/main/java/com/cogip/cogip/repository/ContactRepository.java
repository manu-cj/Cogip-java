package com.cogip.cogip.repository;

import com.cogip.cogip.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<Contact, UUID> {
    @Override
    Optional<Contact> findById(UUID uuid);
}
