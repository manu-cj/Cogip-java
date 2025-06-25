package com.cogip.cogip.controller;

import com.cogip.cogip.dto.ContactDTO;
import com.cogip.cogip.services.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("contact")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;

    public ResponseEntity<?> getAll() {
        List<ContactDTO> contacts = contactService.getAll();
        if (contacts.isEmpty()) {
            return ResponseEntity.status(404).body("Contact not found");
        }
        return ResponseEntity.ok(contacts);
    }

    public ResponseEntity<?> add(@Valid @RequestBody ContactDTO dto) {
        ContactDTO created = contactService.create(dto);
        return ResponseEntity.ok(created);
    }
}
