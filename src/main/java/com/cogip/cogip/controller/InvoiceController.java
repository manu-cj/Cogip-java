package com.cogip.cogip.controller;

import com.cogip.cogip.dto.InvoiceDTO;
import com.cogip.cogip.services.InvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("invoice")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    public ResponseEntity<?> getAll() {
        List<InvoiceDTO> invoices = invoiceService.getAll();
        if (invoices.isEmpty()) {
            return ResponseEntity.status(404).body("invoice not found");
        }
        return ResponseEntity.ok(invoices);
    }

    public ResponseEntity<?> add(@Valid @RequestBody InvoiceDTO dto) {
        InvoiceDTO created = invoiceService.create(dto);
        return ResponseEntity.ok(created);
    }
}
