package com.cogip.cli.command;

import com.cogip.cli.model.Company;
import com.cogip.cli.model.Contact;
import com.cogip.cli.model.Invoice;
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

@CommandLine.Command(name = "add-invoice", description = "Add invoice")
@Slf4j
@Component
public class AddInvoiceCommand implements Runnable{
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private CompanyService companyService;

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.print(CommandLine.Help.Ansi.ON.string("@|magenta Number of invoice (ex: INV-2025-001): |@"));
        String invoiceNumber = scanner.nextLine();

        System.out.print(CommandLine.Help.Ansi.ON.string("@|magenta Date of invoice (YYYY-MM-DD): |@"));
        String date = scanner.nextLine();

        // Get company list
        List<Company> companies = companyService.getCompanies();
        if (companies.isEmpty()) {
            System.out.println(CommandLine.Help.Ansi.ON.string("@|red No company found. Please create one first. |@"));
            return;
        }
        System.out.println(CommandLine.Help.Ansi.ON.string("@|magenta Select a number company of invoice: |@"));
        for (int i = 0; i < companies.size(); i++) {
            System.out.println(CommandLine.Help.Ansi.ON.string("@|magenta - " + (i + 1) + " :|@ @|blue " + companies.get(i).getName() + "|@"));
        }
        int choiceCompany = -1;
        while (choiceCompany < 1 || choiceCompany > companies.size()) {
            System.out.print(CommandLine.Help.Ansi.ON.string("@|magenta Your choice : |@"));
            String input = scanner.nextLine();
            try {
                choiceCompany = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                choiceCompany = -1;
            }
            if (choiceCompany < 1 || choiceCompany > companies.size()) {
                System.out.println(CommandLine.Help.Ansi.ON.string("@|red Choice invalid. |@"));
            }
        }
        Company company = companies.get(choiceCompany - 1);


        //Get contact list
        System.out.print(CommandLine.Help.Ansi.ON.string("@|magenta Choose page number for display contact (default: 0) |@"));
        String page = scanner.nextLine();
        if (page.isEmpty()) {
            page = "0";
        }

        List<Contact> contacts = contactService.getContact(Integer.parseInt(page), 20);
        if (contacts.isEmpty()) {
            System.out.println(CommandLine.Help.Ansi.ON.string("@|red No contact found. Please create one first. |@"));
            return;
        }
        System.out.println(CommandLine.Help.Ansi.ON.string("@|magenta Select a number contacts of invoice: |@"));
        for (int i = 0; i < contacts.size(); i++) {
            System.out.println(CommandLine.Help.Ansi.ON.string("@|magenta - " + (i + 1) + " :|@ @|blue " + contacts.get(i).getFirstName() + " " + contacts.get(i).getLastName() + "|@"));
        }
        int choiceContact = -1;
        while (choiceContact < 1 || choiceContact > contacts.size()) {
            System.out.print(CommandLine.Help.Ansi.ON.string("@|magenta Your choice : |@"));
            String input = scanner.nextLine();
            try {
                choiceContact = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                choiceContact = -1;
            }
            if (choiceContact < 1 || choiceContact > contacts.size()) {
                System.out.println(CommandLine.Help.Ansi.ON.string("@|red Choice invalid. |@"));
            }
        }
        Contact contact = contacts.get(choiceContact - 1);

        Invoice invoice = Invoice.builder()
                .number(invoiceNumber)
                .date(LocalDate.parse(date))
                .companyName(company.getName())
                .companyType(company.getType())
                .contactId(contact.getId())
                .build();
        Invoice created = invoiceService.addInvoice(invoice);
        log.info("Invoice add : {}", created);


    }
}
