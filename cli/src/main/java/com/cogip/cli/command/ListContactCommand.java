package com.cogip.cli.command;

import com.cogip.cli.model.Contact;
import com.cogip.cli.model.ContactPage;
import com.cogip.cli.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(name = "list-contact", description = "Display the contact list")
@Component
public class ListContactCommand implements Runnable {

    @CommandLine.Option(names = {"-p", "--page"}, description = "Numéro de page (0 par défaut)")
    private int page = 0;

    @CommandLine.Option(names = {"-s", "--size"}, description = "Taille de page (20 par défaut)")
    private int size = 20;

    @Autowired
    private ContactService contactService;

    @Override
    public void run() {
        ContactPage contactPage = contactService.getContactPage(page, size);
        List<Contact> contacts = contactService.getContact(page, size);
        System.out.println("Liste des contacts (page " + page + ", taille " + size + ") :");        for (Contact contact : contacts) {
            System.out.println("ID: " + contact.getId());
            System.out.println("Nom: " + contact.getLastName());
            System.out.println("Prénom: " + contact.getFirstName());
            System.out.println("Email: " + contact.getEmail());
            System.out.println("Company name: " + contact.getCompanyName());
            System.out.println("-------------------------");
        }
        System.out.println("Total contacts : " + contactPage.getTotalElements());
        System.out.println("Page : " + contactPage.getPageable().getPageNumber() + ", sur : " + contactPage.getTotalPages());
    }
}
