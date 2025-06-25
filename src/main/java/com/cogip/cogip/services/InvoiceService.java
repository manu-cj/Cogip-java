package com.cogip.cogip.services;

import com.cogip.cogip.dto.InvoiceDTO;
import com.cogip.cogip.mappers.InvoiceMapper;
import com.cogip.cogip.models.Invoice;
import com.cogip.cogip.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;

    @Transactional
    public InvoiceDTO create(InvoiceDTO dto) {
        Invoice invoice = InvoiceMapper.toEntity(dto);
        Invoice saved = invoiceRepository.save(invoice);
        return InvoiceMapper.toDTO(saved);
    }

    public List<InvoiceDTO> getAll() {
        return invoiceRepository.findAll().stream()
                .map(InvoiceMapper::toDTO)
                .toList();
    }
}
