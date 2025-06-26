package com.cogip.cogip.controller;

import com.cogip.cogip.dto.CompanyDTO;
import com.cogip.cogip.dto.ContactDTO;
import com.cogip.cogip.services.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;


    @GetMapping
    public ResponseEntity<?> getAllByPage(Pageable pageable) {
        Page<ContactDTO> contacts = contactService.getByPage(pageable);
        if (contacts.isEmpty()) {
            return ResponseEntity.status(404).body("contacts not found");
        }
        return ResponseEntity.ok(contacts);
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody ContactDTO dto) {
        ContactDTO created = contactService.createContact(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}/{companyId}")
    public ResponseEntity<?> updateCompany(@PathVariable UUID id, @PathVariable UUID companyId) {
        ContactDTO updated = contactService.updateCompany(id, companyId);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable UUID id, @RequestBody ContactDTO dto) {
        ContactDTO updated = contactService.updateContactById(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleted(@PathVariable UUID id) {
        ContactDTO deleted = contactService.delete(id);
        return ResponseEntity.ok("Company deleted " + deleted);
    }
}
