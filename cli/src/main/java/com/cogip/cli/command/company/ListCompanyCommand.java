package com.cogip.cli.command.company;

import com.cogip.cli.model.*;
import com.cogip.cli.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.Scanner;

@CommandLine.Command(name = "list-company", description = "Display the company list")
@Component
public class ListCompanyCommand implements Runnable {
    @CommandLine.Option(names = {"-p", "--page"}, description = "Numéro de page (0 par défaut)")
    private int page = 0;

    @CommandLine.Option(names = {"-s", "--size"}, description = "Taille de page (5 par défaut)")
    private int size = 5;

    @Autowired
    private CompanyService companyService;

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(CommandLine.Help.Ansi.ON.string("@|magenta page number: (0 by default) |@"));
        String pageInput = scanner.nextLine();
        if (!pageInput.trim().isEmpty()) {
            page = Integer.parseInt(pageInput.trim());
        }
        System.out.print(CommandLine.Help.Ansi.ON.string("@|magenta page size: (5 by default) |@"));
        String sizeInput = scanner.nextLine();
        if (!sizeInput.trim().isEmpty()) {
            size = Integer.parseInt(sizeInput.trim());
        }

        CompanyPage companyPage = companyService.getCompanyPage(page, size);

        System.out.println(CommandLine.Help.Ansi.ON.string("@|yellow ============================== |@"));
        System.out.println(CommandLine.Help.Ansi.ON.string("@|yellow   Company list (page " + page + ", size " + size + ") : |@"));
        System.out.println(CommandLine.Help.Ansi.ON.string("@|yellow ============================== |@"));

        companyPage.getContent().forEach(company -> {
            System.out.println(CommandLine.Help.Ansi.ON.string(
                    "@|cyan ID: " + company.getId() + " |@"));
            System.out.println(CommandLine.Help.Ansi.ON.string(
                    "@|cyan Company name: " + company.getName() + " |@"));
            System.out.println(CommandLine.Help.Ansi.ON.string(
                    "@|cyan Vat number: " + company.getVatNumber() + " |@"));
            System.out.println(CommandLine.Help.Ansi.ON.string(
                    "@|cyan Company type: " + company.getType() + " |@"));
            System.out.println(CommandLine.Help.Ansi.ON.string(
                    "@|cyan Contacts : |@"));
            for (Contact contact : company.getContacts()) {
                System.out.println(CommandLine.Help.Ansi.ON.string(
                        "@|green ------------------------------ |@"));
                System.out.println(CommandLine.Help.Ansi.ON.string(
                        "@|cyan     Lastname: " + contact.getLastName() + " |@"));
                System.out.println(CommandLine.Help.Ansi.ON.string(
                        "@|cyan     Firstname: " + contact.getFirstName() + " |@"));
                System.out.println(CommandLine.Help.Ansi.ON.string(
                        "@|cyan     Email: " + contact.getEmail() + " |@"));
                System.out.println(CommandLine.Help.Ansi.ON.string(
                        "@|green ------------------------------ |@"));
            }

            System.out.println(CommandLine.Help.Ansi.ON.string(
                    "@|cyan Invoices : |@"));
            for (Invoice invoice : company.getInvoices()) {
                System.out.println(CommandLine.Help.Ansi.ON.string(
                        "@|green ------------------------------ |@"));
                System.out.println(CommandLine.Help.Ansi.ON.string(
                        "@|cyan     Vat number: " + invoice.getNumber() + " |@"));
                System.out.println(CommandLine.Help.Ansi.ON.string(
                        "@|cyan     Date: " + invoice.getDate() + " |@"));
                System.out.println(CommandLine.Help.Ansi.ON.string(
                        "@|cyan     Client lastname: " + invoice.getContactLastName() + " |@"));
                System.out.println(CommandLine.Help.Ansi.ON.string(
                        "@|cyan     Client firstname: " + invoice.getContactFirstName() + " |@"));
                System.out.println(CommandLine.Help.Ansi.ON.string(
                        "@|green ------------------------------ |@"));
            }


            System.out.println(CommandLine.Help.Ansi.ON.string(
                    "@|green ------------------------------ |@"));
        });
        System.out.println(CommandLine.Help.Ansi.ON.string("@|green Total contacts: |@ @|cyan " + companyPage.getTotalElements() + " |@"));
        System.out.println(CommandLine.Help.Ansi.ON.string("@|green Page: |@ @|cyan " + companyPage.getPageable().getPageNumber()  + " |@"));
        System.out.println(CommandLine.Help.Ansi.ON.string("@|green Total page: |@ @|cyan " + companyPage.getTotalPages() + " |@"));

    }

}
