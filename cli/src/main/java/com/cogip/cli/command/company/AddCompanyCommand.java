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



    @Override
    public void run() {
        Scanner scanner = null;

                System.out.print("Company name: ");
                scanner = new Scanner(System.in);
                String name = scanner.nextLine();



                System.out.print("VAT number: ");
                if (scanner == null) scanner = new Scanner(System.in);
                String vatNumber = scanner.nextLine();




            String[] validTypes = {"CLIENT", "PROVIDER"};

            System.out.println("Possible company types: CLIENT, PROVIDER");
            System.out.print("Company type: ");
            if (scanner == null) scanner = new Scanner(System.in);
            String type = scanner.nextLine().trim().toUpperCase();

            boolean isValid = false;
            for (String validType : validTypes) {
                if (validType.equals(type)) {
                    isValid = true;
                    break;
                }
            }

            if (!isValid) {
                System.out.println("Unknown type. Default value: CLIENT");
                type = "CLIENT";
            }


        Company company = Company.builder()
                .name(name)
                .vatNumber(vatNumber)
                .type(type)
                .build();
        Company created = companyService.addCompany(company);

        log.info("Company added: {}", created);
    }
}
