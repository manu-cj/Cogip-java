package com.cogip.cogip.controller;

import com.cogip.cogip.dto.ContactDTO;
import com.cogip.cogip.services.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<?> getAll() {
        List<ContactDTO> contacts = contactService.getAll();
        if (contacts.isEmpty()) {
            return ResponseEntity.status(404).body("Contact not found");
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

    @PutMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Ça fonctionne !");
    }
}
