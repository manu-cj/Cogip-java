package com.cogip.cli.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

@CommandLine.Command(
        name = "cogip-cli",
        description = "CLI COGIP",
        subcommands = {
                // Company
                CompanyCommand.class,
                AddCompanyCommand.class,
                ListCompanyCommand.class,

                // Contact
                ContactCommand.class,
                AddContactCommand.class,
                ListContactCommand.class,

                //Invoice
                InvoiceCommand.class,
                AddInvoiceCommand.class,
                ListInvoiceCommand.class

        }
)
@Component
public class CliRootCommand implements Runnable {
    @Autowired
    public CompanyCommand companyCommand;
    @Autowired
    public ContactCommand contactCommand;
    @Autowired
    public InvoiceCommand invoiceCommand;
    @Autowired
    public AddCompanyCommand addCompanyCommand;
    @Autowired
    public ListCompanyCommand listCompanyCommand;
    @Autowired
    public AddContactCommand addContactCommand;
    @Autowired
    public ListContactCommand listContactCommand;
    @Autowired
    public AddInvoiceCommand addInvoiceCommand;
    @Autowired
    public ListInvoiceCommand listInvoiceCommand;

    @Override
    public void run() {
        System.out.println("Use a sub command.");
    }
}