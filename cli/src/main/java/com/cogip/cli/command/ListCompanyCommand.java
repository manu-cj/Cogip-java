package com.cogip.cli.command;

import com.cogip.cli.model.Company;
import com.cogip.cli.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(name = "list-company", description = "Display the company list")
@Component
public class ListCompanyCommand implements Runnable {
    @Autowired
    private CompanyService companyService;

    @Override
    public void run() {
        List<Company> companies = companyService.getCompanies();
        System.out.println("Liste des companies :");
        for (Company company : companies) {
            System.out.println("- " + company.getName());
        }
    }

}
