package com.cogip.cli.command.company;

import com.cogip.cli.model.Company;
import com.cogip.cli.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.Scanner;

@CommandLine.Command(name = "add-company", description = "Add company")
@Slf4j
@Component
public class AddCompanyCommand implements Runnable {
    @Autowired
    private CompanyService companyService;
    private String name;
    private String vatNumber;
    private String type;


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

        if (type == null) {
            String[] validTypes = {"CLIENT", "PROVIDER"};

            System.out.println("Type de société possible : CLIENT, PROVIDER");
            System.out.print("Type de société : ");
            if (scanner == null) scanner = new Scanner(System.in);
            type = scanner.nextLine().trim().toUpperCase();

            boolean isValid = false;
            for (String validType : validTypes) {
                if (validType.equals(type)) {
                    isValid = true;
                    break;
                }
            }

            if (!isValid) {
                System.out.println("Type inconnu. Valeur par défaut : CLIENT");
                type = "CLIENT";
            }
        }

        Company company = Company.builder()
                .name(name)
                .vatNumber(vatNumber)
                .type(type)
                .build();
        Company created = companyService.addCompany(company);

        log.info("Société ajoutée : {}", created);
    }
}
