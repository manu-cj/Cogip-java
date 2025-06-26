package com.cogip.cogip.services;

import com.cogip.cogip.Exception.EntityNotFoundException;
import com.cogip.cogip.dto.InvoiceDTO;
import com.cogip.cogip.mappers.InvoiceMapper;
import com.cogip.cogip.models.Company;
import com.cogip.cogip.models.Contact;
import com.cogip.cogip.models.Invoice;
import com.cogip.cogip.repository.CompanyRepository;
import com.cogip.cogip.repository.ContactRepository;
import com.cogip.cogip.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final CompanyRepository companyRepository;
    private final ContactRepository contactRepository;

    @Transactional
    public InvoiceDTO createInvoice(InvoiceDTO dto) {
        Company company = companyRepository.findByName(dto.getCompanyName())
                .orElseThrow(() -> new EntityNotFoundException("Company not found: " + dto.getCompanyName()));



        Contact contact = contactRepository.findAll().stream()
                .filter(c -> c.getFirstName().equalsIgnoreCase(dto.getContactFirstName()) && c.getLastName().equalsIgnoreCase(dto.getContactLastName()))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Contact not found: " + dto.getContactFirstName() + " " + dto.getContactLastName()));

        Invoice invoice = InvoiceMapper.toEntity(dto, company, contact);
        Invoice saved = invoiceRepository.save(invoice);

        return InvoiceMapper.toDTO(saved);
    }

    public List<InvoiceDTO> getAll() {
        return invoiceRepository.findAll().stream()
                .map(InvoiceMapper::toDTO)
                .toList();
    }
}
