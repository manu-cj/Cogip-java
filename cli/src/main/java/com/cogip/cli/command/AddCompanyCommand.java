package com.cogip.cli.command;

import com.cogip.cli.model.Company;
import com.cogip.cli.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.Scanner;

@CommandLine.Command(name = "add-company", description = "Ajoute une société")
@Slf4j
@Component
public class AddCompanyCommand implements Runnable {
    @Autowired
    private CompanyService companyService;
    private String name;
    private String vatNumber;


    @Override
    public void run() {
        Scanner scanner = null;
        if (name == null) {
            if (System.console() != null) {
                name = System.console().readLine("Nom de la société : ");
            } else {
                System.out.print("Nom de la société : ");
                scanner = new Scanner(System.in);
                name = scanner.nextLine();
            }
        }
        if (vatNumber == null) {
            if (System.console() != null) {
                vatNumber = System.console().readLine("Numéro de TVA : ");
            } else {
                System.out.print("Numéro de TVA : ");
                if (scanner == null) scanner = new Scanner(System.in);
                vatNumber = scanner.nextLine();
            }
        }

        Company company = Company.builder()
                .name(name)
                .vatNumber(vatNumber)
                .type("CLIENT")
                .build();
        Company created = companyService.addCompany(company);

        log.info("Société ajoutée : {}", company);
    }
}
