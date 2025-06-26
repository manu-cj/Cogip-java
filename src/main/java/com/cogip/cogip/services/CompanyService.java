package com.cogip.cogip.services;

import com.cogip.cogip.dto.CompanyDTO;
import com.cogip.cogip.mappers.CompanyMapper;
import com.cogip.cogip.models.Company;
import com.cogip.cogip.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<CompanyDTO> getAll() {
        return companyRepository.findAll().stream()
                .map(CompanyMapper::toDTO)
                .toList();
    }

}
