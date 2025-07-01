package com.cogip.cli;

import com.cogip.cli.command.AddCompanyCommand;
import com.cogip.cli.command.CliRootCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import picocli.CommandLine;

import javax.naming.Context;
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
			System.out.println("Sous-commandes disponibles :");
			System.out.println("1. add-company");
			System.out.println("2. list-company");
			System.out.println("3. add-contact");
			System.out.println("4. list-contact");
			// Ajoute ici d'autres sous-commandes si besoin
			System.out.println("0. quit");
			System.out.print("Choisissez une sous-commande : ");
			String input = scanner.nextLine().trim();

			if (input.equalsIgnoreCase("quit") || input.equals("0") || input.equalsIgnoreCase("exit")) {
				System.out.println("Au revoir !");
				break;
			}

			int exitCode = new CommandLine(cliRootCommand, factory).execute(input);
		}
		System.exit(0);
	}

}
