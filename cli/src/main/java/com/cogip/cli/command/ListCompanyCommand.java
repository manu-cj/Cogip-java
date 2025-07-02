package com.cogip.cli.command;

import com.cogip.cli.model.Company;
import com.cogip.cli.model.Contact;
import com.cogip.cli.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.io.PrintWriter;
import java.util.List;

@CommandLine.Command(name = "list-company", description = "Display the company list")
@Component
public class ListCompanyCommand implements Runnable {
    @Autowired
    private CompanyService companyService;

    @CommandLine.Spec
    CommandLine.Model.CommandSpec spec; // Permet d'accéder à l'output Picocli

    @Override
    public void run() {
        List<Company> companies = companyService.getCompanies();
        PrintWriter out = spec.commandLine().getOut();
        out.println(CommandLine.Help.Ansi.ON.string("@|yellow ==============================|@"));
        out.println(CommandLine.Help.Ansi.ON.string("@|yellow   company list      |@"));
        out.println(CommandLine.Help.Ansi.ON.string("@|yellow ==============================|@"));
        for (Company company : companies) {
            out.println(CommandLine.Help.Ansi.ON.string("@|green Id: |@ @|cyan " + company.getId() + "|@"));
            out.println(CommandLine.Help.Ansi.ON.string("@|green Name: |@ @|cyan " + company.getName() + "|@"));
            out.println(CommandLine.Help.Ansi.ON.string("@|green Vat number: |@ @|cyan " + company.getVatNumber() + "|@"));
            out.println(CommandLine.Help.Ansi.ON.string("@|green Type: |@ @|cyan " + company.getType() + "|@"));
            out.println(CommandLine.Help.Ansi.ON.string("@|green Contacts:|@"));
            if (company.getContacts().isEmpty()) {
                out.println(CommandLine.Help.Ansi.ON.string("@|cyan  No contact for this company  |@"));
            }
            for (Contact contact : company.getContacts()) {
                out.println(CommandLine.Help.Ansi.ON.string("@|cyan  -------------------- |@"));
                out.println(CommandLine.Help.Ansi.ON.string("@|green   Lastname: |@ @|cyan " + contact.getLastName() + " |@"));
                out.println(CommandLine.Help.Ansi.ON.string("@|green   Firstname: |@ @|cyan " + contact.getFirstName() + " |@"));
                out.println(CommandLine.Help.Ansi.ON.string("@|green   Email: |@ @|cyan " + contact.getEmail() + " |@"));
            }
            out.println(CommandLine.Help.Ansi.ON.string("@|Yellow ------------------------------ |@"));
        }
    }

}
