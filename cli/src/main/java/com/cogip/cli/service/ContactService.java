package com.cogip.cli.service;

import com.cogip.cli.model.Contact;
import com.cogip.cli.model.ContactPage;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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

    public List<Contact> getContact() {
        try {
            ContactPage page = restTemplate.getForObject(url, ContactPage.class);
            return page != null ? page.getContent() : List.of();
        } catch (HttpClientErrorException.NotFound e) {
            return List.of();
        }
    }
}
