package com.cogip.cogip.services;

import com.cogip.cogip.Exception.EntityNotFoundException;
import com.cogip.cogip.dto.CompanyDTO;
import com.cogip.cogip.mappers.CompanyMapper;
import com.cogip.cogip.models.Company;
import com.cogip.cogip.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;


    @Transactional
    public CompanyDTO create(CompanyDTO dto) {
        Company company = CompanyMapper.toEntity(dto);
        Company saved = companyRepository.save(company);
        return CompanyMapper.toDTO(saved);
    }

    public Page<CompanyDTO> getByPage(Pageable pageable) {
        return companyRepository.findAll(pageable)
                .map(CompanyMapper::toDTO);
    }


    public CompanyDTO updateCompanyById(UUID id, CompanyDTO dto) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));

        company.setName(dto.getName());
        company.setVatNumber(dto.getVatNumber());
        company.setType(dto.getType());

        Company updated = companyRepository.save(company);
        return CompanyMapper.toDTO(updated);
    }


    public CompanyDTO delete(UUID id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));

        companyRepository.deleteById(company.getId());

        return CompanyMapper.toDTO(company);
    }
}
