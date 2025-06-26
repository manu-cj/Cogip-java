package com.cogip.cogip.controller;

import com.cogip.cogip.dto.InvoiceDTO;
import com.cogip.cogip.services.InvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<InvoiceDTO> invoices = invoiceService.getAll();
        if (invoices.isEmpty()) {
            return ResponseEntity.status(404).body("invoice not found");
        }
        return ResponseEntity.ok(invoices);
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody InvoiceDTO dto) {
        InvoiceDTO created = invoiceService.createInvoice(dto);
        return ResponseEntity.ok(created);
    }
}
