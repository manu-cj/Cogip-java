package com.cogip.cli.command.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.Scanner;

@CommandLine.Command(name = "contact", description = "Contact menu")
@Component
public class ContactCommand implements Runnable {

    @Autowired
    private ApplicationContext context;

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(CommandLine.Help.Ansi.ON.string("@|yellow === |@ @|green Contact |@ @|yellow === |@"));
            System.out.println(CommandLine.Help.Ansi.ON.string("@|blue 1. |@ @|yellow list |@"));
            System.out.println(CommandLine.Help.Ansi.ON.string("@|blue 2. |@ @|yellow add |@"));
            System.out.println(CommandLine.Help.Ansi.ON.string("@|blue 3. |@ @|yellow update |@"));
            System.out.println(CommandLine.Help.Ansi.ON.string("@|blue 4. |@ @|yellow delete |@"));
            System.out.println(CommandLine.Help.Ansi.ON.string("@|blue 0. |@ @|red back |@"));
            System.out.println(CommandLine.Help.Ansi.ON.string("@|magenta Enter a sub command:  |@"));
            String input = scanner.nextLine().trim();

            if (input.equals("back")) break;
            if (input.equals("list")) {
                context.getBean(ListContactCommand.class).run();
            } else if (input.equals("add")) {
                context.getBean(AddContactCommand.class).run();
            } else if (input.equals("update")) {
                context.getBean(UpdateContactCommand.class).run();
            }else if (input.equals("delete")) {
                context.getBean(DeleteContactCommand.class).run();
            } else {
                System.out.println(CommandLine.Help.Ansi.ON.string("@|red Invalid choice. |@"));
            }
        }
    }
}