package com.cogip.cogip.services;

import com.cogip.cogip.Exception.EntityNotFoundException;
import com.cogip.cogip.dto.ContactDTO;
import com.cogip.cogip.dto.InvoiceDTO;
import com.cogip.cogip.mappers.ContactMapper;
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
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;
    private final CompanyRepository companyRepository;
    private final InvoiceRepository invoiceRepository;

    @Transactional
    public ContactDTO createContact(ContactDTO dto) {
        Company company = companyRepository.findByName(dto.getCompanyName())
                .orElseThrow(() -> new EntityNotFoundException("Company not found: " + dto.getCompanyName()));

        Contact contact = ContactMapper.toEntity(dto, company);
        Contact saved = contactRepository.save(contact);

        return ContactMapper.toDTO(saved);
    }

    public List<ContactDTO> getAll()  {
        return contactRepository.findAll().stream()
                .map(ContactMapper::toDTO)
                .toList();
    }

    public ContactDTO updateCompany(UUID contactId, UUID companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found" ));

        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new EntityNotFoundException("Contact not found"));

        contact.setCompany(company);

        List<Invoice> invoices = contact.getInvoices();
        for (Invoice invoice : invoices) {
            invoice.setCompany(company);
            invoiceRepository.save(invoice);
        }


        Contact updated = contactRepository.save(contact);
        return ContactMapper.toDTO(updated);
    }
}
