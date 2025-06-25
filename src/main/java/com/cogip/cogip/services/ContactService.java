package com.cogip.cogip.services;

import com.cogip.cogip.dto.ContactDTO;
import com.cogip.cogip.mappers.ContactMapper;
import com.cogip.cogip.models.Contact;
import com.cogip.cogip.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;

    public ContactDTO create(ContactDTO dto) {
        Contact contact = ContactMapper.toEntity(dto);
        Contact saved = contactRepository.save(contact);
        return ContactMapper.toDTO(saved);
    }

    public List<ContactDTO> getAll()  {
        return contactRepository.findAll().stream()
                .map(ContactMapper::toDTO)
                .toList();
    }
}
