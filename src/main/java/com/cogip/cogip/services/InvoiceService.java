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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        Contact contact = contactRepository.findById(dto.getContactId())
                .orElseThrow(() -> new EntityNotFoundException("Contact not found"));

        Invoice invoice = InvoiceMapper.toEntity(dto, company, contact);
        Invoice saved = invoiceRepository.save(invoice);

        return InvoiceMapper.toDTO(saved);
    }

    public List<InvoiceDTO> getAll() {
        return invoiceRepository.findAll().stream()
                .map(InvoiceMapper::toDTO)
                .toList();
    }

    @Transactional
    public InvoiceDTO updateInvoiceById(UUID id, InvoiceDTO dto) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Invoice not found"));

        Company company = companyRepository.findByName(dto.getCompanyName())
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));

        Contact contact = contactRepository.findById(dto.getContactId())
                .orElseThrow(() -> new EntityNotFoundException("Contact not found"));

        invoice.setNumber(dto.getNumber());
        invoice.setDate(dto.getDate());
        invoice.setCompany(company);
        invoice.setContact(contact);

        Invoice updated = invoiceRepository.save(invoice);
        return InvoiceMapper.toDTO(updated);
    }

    @Transactional
    public InvoiceDTO delete(UUID id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Invoice not found"));

        invoiceRepository.deleteById(id);
        return InvoiceMapper.toDTO(invoice);
    }

    public Page<InvoiceDTO> getByPage(Pageable pageable) {
        return invoiceRepository.findAll(pageable)
                .map(InvoiceMapper::toDTO);
    }
}
