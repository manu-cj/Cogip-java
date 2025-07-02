package com.cogip.cli.command;

import com.cogip.cli.model.Contact;
import com.cogip.cli.model.ContactPage;
import com.cogip.cli.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.List;
import java.util.Scanner;

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

            Scanner scanner = new Scanner(System.in);
            System.out.print(CommandLine.Help.Ansi.ON.string("@|magenta page number: (0 by default) |@"));
            String pageInput = scanner.nextLine();
            if (!pageInput.trim().isEmpty()) {
                page = Integer.parseInt(pageInput.trim());
            }
            System.out.print(CommandLine.Help.Ansi.ON.string("@|magenta page size: (20 by default) |@"));
            String sizeInput = scanner.nextLine();
            if (!sizeInput.trim().isEmpty()) {
                size = Integer.parseInt(sizeInput.trim());
            }

        ContactPage contactPage = contactService.getContactPage(page, size);

        if (contactPage == null) {
            System.out.println(CommandLine.Help.Ansi.ON.string("@|red no data for this page. |@"));
            return;
        }

        List<Contact> contacts = contactService.getContact(page, size);

        System.out.println(CommandLine.Help.Ansi.ON.string("@|yellow ============================== |@"));
        System.out.println(CommandLine.Help.Ansi.ON.string("@|yellow   Contact list (page " + page + ", size " + size + ") : |@"));
        System.out.println(CommandLine.Help.Ansi.ON.string("@|yellow ============================== |@"));
        for (Contact contact : contacts) {
            System.out.println(CommandLine.Help.Ansi.ON.string("@|green Id: |@ @|cyan " + contact.getId() + " |@"));
            System.out.println(CommandLine.Help.Ansi.ON.string("@|green Lastname: |@ @|cyan " + contact.getLastName() + " |@"));
            System.out.println(CommandLine.Help.Ansi.ON.string("@|green Firstname: |@ @|cyan " + contact.getFirstName() + " |@"));
            System.out.println(CommandLine.Help.Ansi.ON.string("@|green Email: |@ @|cyan " + contact.getEmail() + " |@"));
            System.out.println(CommandLine.Help.Ansi.ON.string("@|green Company name: |@ @|cyan " + contact.getCompanyName() + " |@"));
            System.out.println(CommandLine.Help.Ansi.ON.string("@|Yellow ------------------------- |@"));
        }
        System.out.println(CommandLine.Help.Ansi.ON.string("@|green Total contacts: |@ @|cyan " + contactPage.getTotalElements() + " |@"));
        System.out.println(CommandLine.Help.Ansi.ON.string("@|green Page: |@ @|cyan " + contactPage.getPageable().getPageNumber()  + " |@"));
        System.out.println(CommandLine.Help.Ansi.ON.string("@|green Total page: |@ @|cyan " + contactPage.getTotalPages() + " |@"));

    }
}
