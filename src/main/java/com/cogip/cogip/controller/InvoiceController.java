package com.cogip.cogip.controller;

import com.cogip.cogip.dto.ContactDTO;
import com.cogip.cogip.dto.InvoiceDTO;
import com.cogip.cogip.services.InvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/invoice")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<?> getAllByPage(Pageable pageable) {
        Page<InvoiceDTO> invoices = invoiceService.getByPage(pageable);
        if (invoices.isEmpty()) {
            return ResponseEntity.status(404).body("invoices not found");
        }
        return ResponseEntity.ok(invoices);
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody InvoiceDTO dto) {
        InvoiceDTO created = invoiceService.createInvoice(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable UUID id, @RequestBody InvoiceDTO dto) {
        InvoiceDTO updated = invoiceService.updateInvoiceById(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleted(@PathVariable UUID id) {
        InvoiceDTO deleted = invoiceService.delete(id);
        return ResponseEntity.ok("Company deleted " + deleted);
    }
}
