package com.cogip.cli.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.Scanner;

@CommandLine.Command(name = "invoice", description = "Invoice menu")
@Component
public class InvoiceCommand implements Runnable {

    @Autowired
    private ApplicationContext context;

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(CommandLine.Help.Ansi.ON.string("@|yellow === |@ @|cyan Invoice |@ @|yellow === |@"));
            System.out.println(CommandLine.Help.Ansi.ON.string("@|blue 1. |@ @|yellow list-invoice |@"));
            System.out.println(CommandLine.Help.Ansi.ON.string("@|blue 2. |@ @|yellow add-invoice |@"));
            System.out.println(CommandLine.Help.Ansi.ON.string("@|blue 0. |@ @|red back |@"));
            System.out.println(CommandLine.Help.Ansi.ON.string("@|magenta Enter a sub command :  |@"));
            String input = scanner.nextLine().trim();

            if (input.equals("back")) break;
            if (input.equals("list-invoice")) {
                context.getBean(ListContactCommand.class).run();
            } else if (input.equals("add-invoice")) {
                context.getBean(AddContactCommand.class).run();
            } else {
                System.out.println(CommandLine.Help.Ansi.ON.string("@|red Choose invalide. |@"));
            }
        }
    }
}