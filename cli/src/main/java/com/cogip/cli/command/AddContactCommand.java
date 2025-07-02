package com.cogip.cli.command;

import com.cogip.cli.DTO.ContactRequest;
import com.cogip.cli.model.Company;
import com.cogip.cli.model.Contact;
import com.cogip.cli.service.CompanyService;
import com.cogip.cli.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.List;
import java.util.Scanner;

@CommandLine.Command(name = "add-contact", description = "Ajoute un contact")
@Slf4j
@Component
public class AddContactCommand implements Runnable {
    @Autowired
    private ContactService contactService;
    @Autowired
    private CompanyService companyService;

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Prénom du contact : ");
        String firstName = scanner.nextLine();

        System.out.print("Nom du contact : ");
        String lastName = scanner.nextLine();

        System.out.print("Email du contact : ");
        String email = scanner.nextLine();

        List<Company> companies = companyService.getCompanies();
        if (companies.isEmpty()) {
            System.out.println("Aucune société trouvée. Veuillez d'abord en créer une.");
            return;
        }
        System.out.println("Sélectionnez le numéro de la société du contact :");
        for (int i = 0; i < companies.size(); i++) {
            System.out.println("- " + (i + 1) + " : " + companies.get(i).getName());
        }
        int choice = -1;
        while (choice < 1 || choice > companies.size()) {
            System.out.print("Votre choix : ");
            String input = scanner.nextLine();
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                choice = -1;
            }
            if (choice < 1 || choice > companies.size()) {
                System.out.println("Choix invalide.");
            }
        }
        Company company = companies.get(choice - 1);


        Contact contact = Contact.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .companyName(company.getName())
                .build();


        Contact created = contactService.addContact(contact);
        log.info("Contact ajouté : {}", created);
    }

}
