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

@CommandLine.Command(name = "delete-company", description = "delete company")
@Slf4j
@Component
public class DeleteCompanyCommand implements Runnable{
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

        //company list
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

        System.out.println(CommandLine.Help.Ansi.ON.string("@|magenta Do you really want to delete the company (" + companyChoice.getName() + ") ? 'YES' for confirm 'No' for cancel : |@"));
        String delete = scanner.nextLine();

        if ("YES".equalsIgnoreCase(delete.trim())) {
            companyService.deleteCompany(companyChoice, companyId);
            System.out.println(CommandLine.Help.Ansi.ON.string("@|green Company deleted with success !|@"));
        } else {
            System.out.println(CommandLine.Help.Ansi.ON.string("@|yellow delete canceled.|@"));
        }
    }

}
