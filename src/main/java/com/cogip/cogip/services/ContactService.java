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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<ContactDTO> getByPage(Pageable pageable) {
        return contactRepository.findAll(pageable)
                .map(ContactMapper::toDTO);
    }

    /**
     * Updates the company associated with a contact and all their invoices.
     *
     * @param contactId the UUID of the contact to update
     * @param companyId the UUID of the new company
     * @return the updated ContactDTO
     * @throws EntityNotFoundException if the company or contact is not found
     */
    @Transactional
    public ContactDTO updateCompany(UUID contactId, UUID companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));

        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new EntityNotFoundException("Contact not found"));

        // Update the company of the contact
        contact.setCompany(company);

        // Update the company for all invoices associated with the contact
        List<Invoice> invoices = contact.getInvoices();
        for (Invoice invoice : invoices) {
            invoice.setCompany(company);
            invoiceRepository.save(invoice);
        }

        // Save the updated contact and return its DTO
        Contact updated = contactRepository.save(contact);
        return ContactMapper.toDTO(updated);
    }

    /**
     * Updates an existing contact by its identifier and the information provided in the DTO.
     * Also updates the associated company and all invoices linked to this contact.
     *
     * @param id the identifier of the contact to update
     * @param dto the new contact information
     * @return the updated ContactDTO
     * @throws EntityNotFoundException if the contact or company is not found
     */
    public ContactDTO updateContactById(UUID id, ContactDTO dto) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contact not found"));

        Company company = companyRepository.findByName(dto.getCompanyName())
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));

        // Update data of contact
        contact.setFirstName(dto.getFirstName());
        contact.setLastName(dto.getLastName());
        contact.setEmail(dto.getEmail());
        contact.setCompany(company);


        // Update the company for all invoices associated with the contact
        List<Invoice> invoices = contact.getInvoices();
        for (Invoice invoice : invoices) {
            invoice.setCompany(company);
            invoiceRepository.save(invoice);
        }

        // Save the updated contact and return its DTO
        Contact updated = contactRepository.save(contact);
        return ContactMapper.toDTO(updated);
    }

    public ContactDTO delete(UUID id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contact not found"));

        companyRepository.deleteById(id);
        return ContactMapper.toDTO(contact);
    }
}
