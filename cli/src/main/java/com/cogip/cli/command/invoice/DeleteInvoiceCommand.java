package com.cogip.cli.command.invoice;

import com.cogip.cli.model.Invoice;
import com.cogip.cli.model.InvoicePage;
import com.cogip.cli.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.Scanner;
import java.util.UUID;

@CommandLine.Command(name = "delete-invoice", description = "delete invoice")
@Slf4j
@Component
public class DeleteInvoiceCommand implements Runnable{
    @Autowired
    private InvoiceService invoiceService;

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(CommandLine.Help.Ansi.ON.string("@|magenta Choose page number for display contact (default: 0) |@"));
        String page = scanner.nextLine();
        if (page.isEmpty()) {
            page = "0";
        }
        InvoicePage invoicePage = invoiceService.getInvoicePage(Integer.parseInt(page), 20);
        if (invoicePage.getContent().isEmpty()) {
            System.out.println(CommandLine.Help.Ansi.ON.string("@|red No invoice found. Please create one first. |@"));
            return;
        }

        //invoice list
        for (int i = 0; i < invoicePage.getContent().size(); i++ ) {
            System.out.println(CommandLine.Help.Ansi.ON.string("@|magenta - " + (i + 1) + " :|@ @|blue "
                    + invoicePage.getContent().get(i).getNumber() + "|@"));
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

        UUID InvoiceId = invoiceChoice.getId();

        System.out.println(CommandLine.Help.Ansi.ON.string("@|magenta Do you really want to delete the invoice (" + invoiceChoice.getNumber() + ") ? 'YES' for confirm 'No' for cancel : |@"));
        String delete = scanner.nextLine();

        if ("YES".equalsIgnoreCase(delete.trim())) {
            invoiceService.deleteInvoice(invoiceChoice, InvoiceId);
            System.out.println(CommandLine.Help.Ansi.ON.string("@|green Invoice deleted with success !|@"));
        } else {
            System.out.println(CommandLine.Help.Ansi.ON.string("@|yellow delete canceled.|@"));
        }
    }

}
