package com.cogip.cli.command.invoice;

import com.cogip.cli.model.InvoicePage;
import com.cogip.cli.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.Scanner;

@CommandLine.Command(name = "list-invoice", description = "Display the invoice list")
@Component
public class ListInvoiceCommand implements Runnable{
    @CommandLine.Option(names = {"-p", "--page"}, description = "Numéro de page (0 par défaut)")
    private int page = 0;

    @CommandLine.Option(names = {"-s", "--size"}, description = "Taille de page (20 par défaut)")
    private int size = 20;

    @Autowired
    private InvoiceService invoiceService;

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

        InvoicePage invoicePage = invoiceService.getInvoicePage(page, size);

        System.out.println(CommandLine.Help.Ansi.ON.string("@|yellow ============================== |@"));
        System.out.println(CommandLine.Help.Ansi.ON.string("@|yellow   Invoice list (page " + page + ", size " + size + ") : |@"));
        System.out.println(CommandLine.Help.Ansi.ON.string("@|yellow ============================== |@"));

        invoicePage.getContent().forEach(invoice -> {
            System.out.println(CommandLine.Help.Ansi.ON.string(
                    "@|cyan ID: " + invoice.getId() + " |@"));
            System.out.println(CommandLine.Help.Ansi.ON.string(
                    "@|cyan Invoice number: " + invoice.getNumber() + " |@"));
            System.out.println(CommandLine.Help.Ansi.ON.string(
                    "@|cyan Date: " + invoice.getDate() + " |@"));
            System.out.println(CommandLine.Help.Ansi.ON.string(
                    "@|cyan Company: " + invoice.getCompanyName() + " |@"));
            System.out.println(CommandLine.Help.Ansi.ON.string(
                    "@|cyan Company type: " + invoice.getCompanyType() + " |@"));
            System.out.println(CommandLine.Help.Ansi.ON.string(
                    "@|cyan Client id: " + invoice.getContactId() + " |@"));
            System.out.println(CommandLine.Help.Ansi.ON.string(
                    "@|cyan Client firstname: " + invoice.getContactFirstName() + " |@"));
            System.out.println(CommandLine.Help.Ansi.ON.string(
                    "@|cyan Client lastname: " + invoice.getContactLastName() + " |@"));
            System.out.println(CommandLine.Help.Ansi.ON.string(
                    "@|green ------------------------------ |@"));
        });
        System.out.println(CommandLine.Help.Ansi.ON.string("@|green Total contacts: |@ @|cyan " + invoicePage.getTotalElements() + " |@"));
        System.out.println(CommandLine.Help.Ansi.ON.string("@|green Page: |@ @|cyan " + invoicePage.getPageable().getPageNumber()  + " |@"));
        System.out.println(CommandLine.Help.Ansi.ON.string("@|green Total page: |@ @|cyan " + invoicePage.getTotalPages() + " |@"));

    }

}
