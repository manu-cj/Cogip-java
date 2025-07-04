package com.cogip.cli;

import com.cogip.cli.command.CliRootCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import picocli.CommandLine;

import java.util.Scanner;


@SpringBootApplication
@ComponentScan
public class CliApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(CliApplication.class, args);
		var cliRootCommand = context.getBean(CliRootCommand.class);
		var factory = context.getBean(SpringFactory.class);

		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println(CommandLine.Help.Ansi.ON.string("@|yellow ==============================|@"));
			System.out.println(CommandLine.Help.Ansi.ON.string("@|yellow           COGIP CLI |@"));
			System.out.println(CommandLine.Help.Ansi.ON.string("@|yellow ==============================|@"));
			System.out.println(CommandLine.Help.Ansi.ON.string("@|green 1.|@ @|blue company |@"));
			System.out.println(CommandLine.Help.Ansi.ON.string("@|green 2.|@ @|blue contact |@"));
			System.out.println(CommandLine.Help.Ansi.ON.string("@|green 3.|@ @|blue invoice |@"));
			System.out.println(CommandLine.Help.Ansi.ON.string("@|red 0. quit |@"));
			System.out.print(CommandLine.Help.Ansi.ON.string("\n @|magenta Choose a sub command : |@"));
			String input = scanner.nextLine().trim();

			if (input.equalsIgnoreCase("quit") || input.equals("0") || input.equalsIgnoreCase("exit")) {
				System.out.println("Goodbye !");
				break;
			}

			int exitCode = new CommandLine(cliRootCommand, factory).execute(input);
		}
		System.exit(0);
	}

}
