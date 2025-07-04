package com.cogip.cli.service;

import com.cogip.cli.model.Contact;
import com.cogip.cli.model.ContactPage;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class ContactService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final  String url = "http://localhost:8080/contacts";

    public Contact addContact(Contact contact) {
        try {
            return restTemplate.postForObject(url, contact, Contact.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            System.out.println("Erreur lors de l'ajout du contact : " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            return null;
        }
    }

    public List<Contact> getContact(int page, int size) {
        try {
            String pagedUrl = url + "?page=" + page + "&size=" + size;
            ContactPage contactPage = restTemplate.getForObject(pagedUrl, ContactPage.class);
            return contactPage != null ? contactPage.getContent() : List.of();
        } catch (HttpClientErrorException.NotFound e) {
            return List.of();
        }
    }

    public ContactPage getContactPage(int page, int size) {
        try {
            String pagedUrl = url + "?page=" + page + "&size=" + size;
            return restTemplate.getForObject(pagedUrl, ContactPage.class);
        } catch (HttpClientErrorException.NotFound e) {
            return null;
        }
    }

    public Contact updateContact(Contact contact, UUID id) {
        try {
            restTemplate.put(url + "/" + id, contact);
            return contact;
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            System.out.println("Error when update the contact : " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            return null;
        }
    }
}
