package com.cogip.cogip.repository;

import com.cogip.cogip.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {
    @Override
    Optional<Company> findById(UUID uuid);
    Optional<Company> findByName(String name);
}
