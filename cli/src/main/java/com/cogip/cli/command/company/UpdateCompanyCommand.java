package com.cogip.cli.command.company;

import com.cogip.cli.model.Company;
import com.cogip.cli.model.CompanyPage;
import com.cogip.cli.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.Scanner;
import java.util.UUID;

@CommandLine.Command(name = "update-company", description = "Update company")
@Slf4j
@Component
public class UpdateCompanyCommand implements Runnable{

    @Autowired
    private CompanyService companyService;



    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(CommandLine.Help.Ansi.ON.string("@|magenta Choose page number for display company (default: 0) |@"));
        String page = scanner.nextLine();
        if (page.isEmpty()) {
            page = "0";
        }
        CompanyPage companyPages = companyService.getCompanyPage(Integer.parseInt(page), 20);
        if (companyPages.getContent().isEmpty()) {
            System.out.println(CommandLine.Help.Ansi.ON.string("@|red No company found. Please create one first. |@"));
            return;
        }

        for (int i = 0; i < companyPages.getContent().size(); i++ ) {
            System.out.println(CommandLine.Help.Ansi.ON.string("@|magenta - " + (i + 1) + " :|@ @|blue " + companyPages.getContent().get(i).getName() + "|@"));
        }
        int choice = -1;
        while (choice < 1 || choice > companyPages.getContent().size()) {
            System.out.print(CommandLine.Help.Ansi.ON.string("@|magenta Your choice : |@"));
            String input = scanner.nextLine();
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                choice = -1;
            }
            if (choice < 1 || choice > companyPages.getContent().size()) {
                System.out.println(CommandLine.Help.Ansi.ON.string("@|red Choice invalid. |@"));
            }
        }
        Company companyChoice = companyPages.getContent().get(choice -1);

        UUID companyId = companyChoice.getId();


// Ask for new fields (leave empty to keep the old value)
System.out.print(CommandLine.Help.Ansi.ON.string("@|magenta Nouveau nom (" + companyChoice.getName() + ") : |@"));
        String newName = scanner.nextLine();
        if (newName.isEmpty()) newName = companyChoice.getName();

        System.out.print(CommandLine.Help.Ansi.ON.string("@|magenta Nouveau numéro de TVA (" + companyChoice.getVatNumber() + ") : |@"));
        String newVat = scanner.nextLine();
        if (newVat.isEmpty()) newVat = companyChoice.getVatNumber();

        System.out.print(CommandLine.Help.Ansi.ON.string("@|magenta Nouveau type (" + companyChoice.getType() + ") choose enter (CLIENT, PROVIDER) : |@"));
        String newType = scanner.nextLine();
        if (newType.isEmpty()) newType = companyChoice.getType();

        Company updatedCompany = Company.builder()
                .id(companyChoice.getId())
                .name(newName)
                .vatNumber(newVat)
                .type(newType)
                .build();

        companyService.updateCompany(updatedCompany, companyId);

        System.out.println(CommandLine.Help.Ansi.ON.string("@|green Société mise à jour avec succès !|@"));
    }
}
