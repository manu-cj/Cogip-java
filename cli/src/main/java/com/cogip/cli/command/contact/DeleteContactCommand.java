package com.cogip.cli.command.contact;

import com.cogip.cli.model.Contact;
import com.cogip.cli.model.ContactPage;
import com.cogip.cli.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.Scanner;
import java.util.UUID;

@CommandLine.Command(name = "delete-contact", description = "delete contact")
@Slf4j
@Component
public class DeleteContactCommand implements Runnable{
    @Autowired
    private ContactService contactService;

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

        //contact list
        for (int i = 0; i < contactPage.getContent().size(); i++ ) {
            System.out.println(CommandLine.Help.Ansi.ON.string("@|magenta - " + (i + 1) + " :|@ @|blue "
                    + contactPage.getContent().get(i).getFirstName()
                    + " " + contactPage.getContent().get(i).getLastName() + "|@"));
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

        System.out.println(CommandLine.Help.Ansi.ON.string("@|magenta Do you really want to delete the contact (" + contactChoice.getFirstName() + " " + contactChoice.getLastName() + ") ? 'YES' for confirm 'No' for cancel : |@"));
        String delete = scanner.nextLine();

        if ("YES".equalsIgnoreCase(delete.trim())) {
            contactService.deleteContact(contactChoice, contactId);
            System.out.println(CommandLine.Help.Ansi.ON.string("@|green Contact deleted with success !|@"));
        } else {
            System.out.println(CommandLine.Help.Ansi.ON.string("@|yellow delete canceled.|@"));
        }
    }

}
