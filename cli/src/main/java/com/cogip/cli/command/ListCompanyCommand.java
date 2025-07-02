package com.cogip.cli.command;

import com.cogip.cli.model.Company;
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
            out.println(CommandLine.Help.Ansi.ON.string("@|green - |@ @|blue " + company.getName() + "|@"));
        }
        out.println(CommandLine.Help.Ansi.ON.string("@|yellow ==============================|@"));
    }

}
