package com.cogip.cli.command.invoice;

import com.cogip.cli.model.*;
import com.cogip.cli.service.CompanyService;
import com.cogip.cli.service.ContactService;
import com.cogip.cli.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@CommandLine.Command(name = "update-contact", description = "Update contact")
@Slf4j
@Component
public class UpdateInvoice implements Runnable{
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private CompanyService companyService;

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(CommandLine.Help.Ansi.ON.string("@|magenta Choose page number for display invoice (default: 0) |@"));
        String page = scanner.nextLine();
        if (page.isEmpty()) {
            page = "0";
        }
        InvoicePage invoicePage = invoiceService.getInvoicePage(Integer.parseInt(page), 20);
        if (invoicePage.getContent().isEmpty()) {
            System.out.println(CommandLine.Help.Ansi.ON.string("@|red No invoice found. Please create one first. |@"));
            return;
        }

        for (int i = 0; i < invoicePage.getContent().size(); i++ ) {
            System.out.println(CommandLine.Help.Ansi.ON.string("@|magenta - " + (i + 1) + " :|@ @|blue "
                    + invoicePage.getContent().get(i).getNumber() + " "
                    + invoicePage.getContent().get(i).getContactLastName() + " " + invoicePage.getContent().get(i).getContactFirstName() + "|@"));
        }
        int choice = -1;
        while (choice < 1 || choice > invoicePage.getContent().size()) {
            System.out.print(CommandLine.Help.Ansi.ON.string("@|magenta Your choice : |@"));
            String input = scanner.nextLine();
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                choice = -1;
            }
            if (choice < 1 || choice > invoicePage.getContent().size()) {
                System.out.println(CommandLine.Help.Ansi.ON.string("@|red Choice invalid. |@"));
            }
        }
        Invoice invoiceChoice = invoicePage.getContent().get(choice -1);

        UUID invoiceId = invoiceChoice.getId();

        // Ask for new fields (leave empty to keep the old value)

        System.out.print(CommandLine.Help.Ansi.ON.string("@|magenta New invoice number (" + invoiceChoice.getNumber() + ") : |@"));
        String newInvoiceNumber = scanner.nextLine();
        if (newInvoiceNumber.isEmpty()) newInvoiceNumber = invoiceChoice.getNumber();

        System.out.print(CommandLine.Help.Ansi.ON.string("@|magenta New date (" + invoiceChoice.getDate() + ") : |@"));
        String newDate = scanner.nextLine();
        if (newDate.isEmpty()) newDate = invoiceChoice.getDate().toString();


        // Gets company
        System.out.println(CommandLine.Help.Ansi.ON.string("@|magenta Choose page number for display company list (default: 0) |@"));
        String companyPageChoice = scanner.nextLine();
        if (companyPageChoice.isEmpty()) {
            companyPageChoice = "0";
        }
        System.out.println(CommandLine.Help.Ansi.ON.string("@|magenta Liste des companies :|@"));
        List<Company> companies = companyService.getCompanyPage(Integer.parseInt(companyPageChoice), 50).getContent();
        for (int i = 0; i < companies.size(); i++) {
            System.out.println((i + 1) + " : " + companies.get(i).getName());
        }
        System.out.print(CommandLine.Help.Ansi.ON.string("@|magenta Choisis une company (numéro, vide pour garder "
                + invoiceChoice.getCompanyName() + ") : |@"));
        String companyInput = scanner.nextLine();

        Company selectedCompany = invoiceChoice.getCompanyName() != null ?
                Company.builder().name(invoiceChoice.getCompanyName()).build() : null;
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

            // Gets contact
            System.out.println(CommandLine.Help.Ansi.ON.string("@|magenta Choose page number for display contact list (default: 0) |@"));
            String contactPageChoice = scanner.nextLine();
            if (contactPageChoice.isEmpty()) {
                contactPageChoice = "0";
            }
            System.out.println(CommandLine.Help.Ansi.ON.string("@|magenta Liste des contacts :|@"));
            List<Contact> contacts = contactService.getContactPage(Integer.parseInt(contactPageChoice), 50).getContent();
            for (int i = 0; i < contacts.size(); i++) {
                System.out.println((i + 1) + " : " + contacts.get(i).getFirstName() + " " + contacts.get(i).getLastName());
            }
            System.out.print(CommandLine.Help.Ansi.ON.string("@|magenta Choisis une contacts (numéro, vide pour garder " + invoiceChoice.getContactFirstName() + " " + invoiceChoice.getContactLastName() + " ) : |@"));
            String contactInput = scanner.nextLine();

            Contact selectedContact = invoiceChoice.getContactId() != null ?
                    Contact.builder().id(invoiceChoice.getContactId()).build() : null;
            if (!contactInput.isEmpty()) {
                try {
                    int companyChoice = Integer.parseInt(contactInput);
                    if (companyChoice >= 1 && companyChoice <= contacts.size()) {
                        selectedContact = contacts.get(companyChoice - 1);
                    } else {
                        System.out.println(CommandLine.Help.Ansi.ON.string("@|red Choix invalide, ancien contact conservée.|@"));
                    }
                } catch (NumberFormatException e) {
                    System.out.println(CommandLine.Help.Ansi.ON.string("@|red Entrée invalide, ancien contact conservée.|@"));
                }
            }

            Invoice updatedInvoice = Invoice.builder()
                    .id(invoiceChoice.getId())
                    .number(newInvoiceNumber)
                    .date(LocalDate.parse(newDate))
                    .companyName(selectedCompany != null ? selectedCompany.getName() : null)
                    .companyType(selectedCompany != null ? selectedCompany.getType() : null)
                    .contactId(selectedContact != null ? selectedContact.getId() : null)
                    .build();

            invoiceService.updateInvoice(updatedInvoice, invoiceId);

            System.out.println(CommandLine.Help.Ansi.ON.string("@|green Invoice mis à jour avec succès !|@"));
        }



    }
}
