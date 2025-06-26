package com.cogip.cogip.controller;

import com.cogip.cogip.dto.CompanyDTO;
import com.cogip.cogip.dto.InvoiceDTO;
import com.cogip.cogip.services.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;


    @GetMapping
    public ResponseEntity<?> getAllByPage(Pageable pageable) {
        Page<CompanyDTO> company = companyService.getByPage(pageable);
        if (company.isEmpty()) {
            return ResponseEntity.status(404).body("company not found");
        }
        return ResponseEntity.ok(company);
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody CompanyDTO dto) {
        CompanyDTO created = companyService.create(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable UUID id, @RequestBody CompanyDTO dto) {
        CompanyDTO updated = companyService.updateCompanyById(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleted(@PathVariable UUID id) {
        CompanyDTO deleted = companyService.delete(id);
        return ResponseEntity.ok("Company deleted " + deleted);
    }
}
