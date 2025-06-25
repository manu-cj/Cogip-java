package com.cogip.cogip.controller;

import com.cogip.cogip.dto.CompanyDTO;
import com.cogip.cogip.services.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<CompanyDTO> company = companyService.getAll();
        if (company.isEmpty()) {
            return ResponseEntity.status(404).body("Company not found");
        }
        return ResponseEntity.ok(company);
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody CompanyDTO dto) {
        CompanyDTO created = companyService.create(dto);
        return ResponseEntity.ok(created);
    }
}
