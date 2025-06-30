package com.cogip.cli.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

@CommandLine.Command(
        name = "cogip-cli",
        description = "CLI COGIP",
        subcommands = {
                AddCompanyCommand.class,
                ListCompanyCommand.class
                // Ajoute ici d'autres commandes, ex: ListCompanyCommand.class
        }
)
@Component
public class CliRootCommand implements Runnable {
    @Autowired
    public AddCompanyCommand addCompanyCommand;
    @Autowired
    public ListCompanyCommand listCompanyCommand;

    @Override
    public void run() {
        System.out.println("Utilise une sous-commande.");
    }
}