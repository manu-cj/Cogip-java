package com.cogip.cli.command.contact;

import com.cogip.cli.model.Company;
import com.cogip.cli.model.CompanyPage;
import com.cogip.cli.model.Contact;
import com.cogip.cli.model.ContactPage;
import com.cogip.cli.service.CompanyService;
import com.cogip.cli.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@CommandLine.Command(name = "update-contact", description = "Update contact")
@Slf4j
@Component
public class UpdateContact implements Runnable{
    @Autowired
    private ContactService contactService;
    @Autowired
    private CompanyService companyService;



    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(CommandLine.Help.Ansi.ON.string("@|magenta Choose page number for display contact (default: 0) |@"));
        String page = scanner.nextLine();
        if (page.isEmpty()) {
            page = "0";
        }
        ContactPage contactPage = contactService.getContactPage(Integer.parseInt(page), 20);
        if (contactPage.getContent().isEmpty()) {
            System.out.println(CommandLine.Help.Ansi.ON.string("@|red No contact found. Please create one first. |@"));
            return;
        }

        for (int i = 0; i < contactPage.getContent().size(); i++ ) {
            System.out.println(CommandLine.Help.Ansi.ON.string("@|magenta - " + (i + 1) + " :|@ @|blue "
                    + contactPage.getContent().get(i).getFirstName() + " "
                    + contactPage.getContent().get(i).getLastName() + "|@"));
        }
        int choice = -1;
        while (choice < 1 || choice > contactPage.getContent().size()) {
            System.out.print(CommandLine.Help.Ansi.ON.string("@|magenta Your choice : |@"));
            String input = scanner.nextLine();
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                choice = -1;
            }
            if (choice < 1 || choice > contactPage.getContent().size()) {
                System.out.println(CommandLine.Help.Ansi.ON.string("@|red Choice invalid. |@"));
            }
        }
        Contact contactChoice = contactPage.getContent().get(choice -1);

        UUID contactId = contactChoice.getId();

        // Ask for new fields (leave empty to keep the old value)
        System.out.print(CommandLine.Help.Ansi.ON.string("@|magenta New firstname (" + contactChoice.getFirstName() + ") : |@"));
        String newFirstname = scanner.nextLine();
        if (newFirstname.isEmpty()) newFirstname = contactChoice.getFirstName();

        System.out.print(CommandLine.Help.Ansi.ON.string("@|magenta New lastname (" + contactChoice.getLastName() + ") : |@"));
        String newLastname = scanner.nextLine();
        if (newLastname.isEmpty()) newLastname = contactChoice.getLastName();

        System.out.print(CommandLine.Help.Ansi.ON.string("@|magenta New email (" + contactChoice.getEmail() + ") : |@"));
        String newEmail = scanner.nextLine();
        if (newEmail.isEmpty()) newEmail = contactChoice.getEmail();


        // Gets company
        System.out.println(CommandLine.Help.Ansi.ON.string("@|magenta Choose page number for display company list (default: 0) |@"));
        System.out.print(CommandLine.Help.Ansi.ON.string("@|magenta New company name (" + contactChoice.getCompanyName() + ") : |@"));
        String contactPageChoice = scanner.nextLine();
        if (contactPageChoice.isEmpty()) {
            contactPageChoice = "0";
        }
        System.out.println(CommandLine.Help.Ansi.ON.string("@|magenta Liste des companies :|@"));
        List<Company> companies = companyService.getCompanyPage(Integer.parseInt(contactPageChoice), 50).getContent();
        for (int i = 0; i < companies.size(); i++) {
            System.out.println((i + 1) + " : " + companies.get(i).getName() + ")");
        }
        System.out.print(CommandLine.Help.Ansi.ON.string("@|magenta Choisis une company (numéro, vide pour garder " + contactChoice.getCompanyName() + ") : |@"));
        String companyInput = scanner.nextLine();

        Company selectedCompany = contactChoice.getCompanyName() != null ?
                Company.builder().name(contactChoice.getCompanyName()).build() : null;
        if (!companyInput.isEmpty()) {
            try {
                int companyChoice = Integer.parseInt(companyInput);
                if (companyChoice >= 1 && companyChoice <= companies.size()) {
                    selectedCompany = companies.get(companyChoice - 1);
                } else {
                    System.out.println(CommandLine.Help.Ansi.ON.string("@|red Choix invalide, ancienne société conservée.|@"));
                }
            } catch (NumberFormatException e) {
                System.out.println(CommandLine.Help.Ansi.ON.string("@|red Entrée invalide, ancienne société conservée.|@"));
            }
        }

// Construction de l'objet Contact mis à jour
        Contact updatedContact = Contact.builder()
                .id(contactChoice.getId())
                .firstName(newFirstname)
                .lastName(newLastname)
                .email(newEmail)
                .companyName(selectedCompany != null ? selectedCompany.getName() : null)
                .build();

        contactService.updateContact(updatedContact, contactId);

        System.out.println(CommandLine.Help.Ansi.ON.string("@|green Contact mis à jour avec succès !|@"));

    }
}
