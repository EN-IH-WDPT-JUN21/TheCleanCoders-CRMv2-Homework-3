package com.ironhack.TheCleanCodersCRMv2homework3.io;


import com.ironhack.TheCleanCodersCRMv2homework3.classes.*;
import com.ironhack.TheCleanCodersCRMv2homework3.enums.Industry;
import com.ironhack.TheCleanCodersCRMv2homework3.enums.Product;
import com.ironhack.TheCleanCodersCRMv2homework3.enums.Status;
import com.ironhack.TheCleanCodersCRMv2homework3.menu.Printer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {
    private final String ACCOUNTS_FILE_NAME = "Accounts.txt";
    private final String CONTACTS_FILE_NAME = "Contacts.txt";
    private final String LEADS_FILE_NAME = "Leads.txt";
    private final String OPPORTUNITIES_FILE_NAME = "Opportunities.txt";
    private Printer printer;

    public FileManager(Printer printer) {
        this.printer = printer;
    }

    public void exportData() {
        exportContacts();
        exportLeads();
        exportOpportunities();
        exportAccounts();
    }

    public void exportLeads() {
        String filename = LEADS_FILE_NAME;
        try {
            FileWriter writer = new FileWriter(filename, false);
            for (Item item : Lead.getAllLeads()) {
                Lead lead = (Lead) item;
                String leadString =
                        lead.getId() + "/&/" +
                                lead.getName() + "/&/" +
                                lead.getPhoneNumber() + "/&/" +
                                lead.getEmail() + "/&/" +
                                lead.getCompanyName() + "/&/";
                writer.write(leadString);
            }
            writer.close();
        } catch (IOException exception) {
            printer.printExportErrorInfo(filename);
        }
    }

    public void exportContacts() {
        String filename = CONTACTS_FILE_NAME;
        try {
            FileWriter writer = new FileWriter(filename, false);
            for (Item item : Contact.getAllContacts()) {
                Contact contact = (Contact) item;
                String contactString =
                        contact.getId() + "/&/" +
                                contact.getName() + "/&/" +
                                contact.getPhoneNumber() + "/&/" +
                                contact.getEmail() + "/&/" +
                                contact.getCompanyName() + "/&/";
                writer.write(contactString);
            }
            writer.close();
        } catch (IOException exception) {
            printer.printExportErrorInfo(filename);
        }
    }

    public void exportOpportunities() {
        String filename = OPPORTUNITIES_FILE_NAME;
        try {
            FileWriter writer = new FileWriter(filename, false);
            for (Item item : Opportunity.getAllOpportunities()) {
                Opportunity opportunity = (Opportunity) item;
                String opportunityString =
                        opportunity.getId() + "/&/" +
                                opportunity.getProduct() + "/&/" +
                                opportunity.getQuantity() + "/&/" +
                                opportunity.getDecisionMaker().getId() + "/&/" +
                                opportunity.getStatus() + "/&/";
                writer.write(opportunityString);
            }
            writer.close();
        } catch (IOException exception) {
            printer.printExportErrorInfo(filename);
        }
    }

    public void exportAccounts() {
        String filename = ACCOUNTS_FILE_NAME;
        try {
            FileWriter writer = new FileWriter(filename, false);
            for (Item item : Account.getAllAccounts()) {
                Account account = (Account) item;
                String accountString =
                        account.getId() + "/&/" +
                                account.getContactList().get(0).getId() + "/&/" +
                                account.getOpportunityList().get(0).getId() + "/&/" +
                                account.getCompanyName() + "/&/" +
                                account.getIndustry() + "/&/" +
                                account.getEmployeeCount() + "/&/" +
                                account.getCity() + "/&/" +
                                account.getCountry() + "/&/";
                writer.write(accountString);
            }
            writer.close();
        } catch (IOException exception) {
            printer.printExportErrorInfo(filename);
        }
    }

    public void importData() {
        importContacts();
        importLeads();
        importOpportunities();
        importAccounts();
    }

    public void importLeads() {
        String filename = LEADS_FILE_NAME;
        try {
            Scanner scanner = new Scanner(new File(filename));
            scanner.useDelimiter("/&/");
            while (scanner.hasNext()) {
                int id = Integer.parseInt(scanner.next());
                String name = scanner.next();
                String phoneNumber = scanner.next();
                String email = scanner.next();
                String companyName = scanner.next();
                new Lead(id, name, phoneNumber, email, companyName);
            }
        } catch (FileNotFoundException exception) {
            printer.printImportErrorInfo(filename);
        }
    }

    public void importContacts() {
        String filename = CONTACTS_FILE_NAME;
        try {
            Scanner scanner = new Scanner(new File(filename));
            scanner.useDelimiter("/&/");
            while (scanner.hasNext()) {
                int id = Integer.parseInt(scanner.next());
                String name = scanner.next();
                String phoneNumber = scanner.next();
                String email = scanner.next();
                String companyName = scanner.next();
                new Contact(id, name, phoneNumber, email, companyName);
            }
        } catch (FileNotFoundException exception) {
            printer.printImportErrorInfo(filename);
        }
    }

    public void importOpportunities() {
        String filename = OPPORTUNITIES_FILE_NAME;
        try {
            Scanner scanner = new Scanner(new File(filename));
            scanner.useDelimiter("/&/");
            while (scanner.hasNext()) {
                int id = Integer.parseInt(scanner.next());
                Product product = getProductFromString(scanner.next());
                int quantity = Integer.parseInt(scanner.next());
                Contact decisionMaker = (Contact) Contact.getById(Integer.parseInt(scanner.next()), Contact.getAllContacts());
                Status status = getStatusFromString(scanner.next());
                new Opportunity(id, product, quantity, decisionMaker, status);
            }
        } catch (FileNotFoundException exception) {
            printer.printImportErrorInfo(filename);
        }
    }

    public void importAccounts() {
        String filename = ACCOUNTS_FILE_NAME;
        try {
            Scanner scanner = new Scanner(new File(filename));
            scanner.useDelimiter("/&/");
            while (scanner.hasNext()) {
                int id = Integer.parseInt(scanner.next());
                Contact contact = (Contact) Contact.getById(Integer.parseInt(scanner.next()), Contact.getAllContacts());
                Opportunity opportunity = (Opportunity) Opportunity.getById(Integer.parseInt(scanner.next()), Opportunity.getAllOpportunities());
                String companyName = scanner.next();
                Industry industry = getIndustryFromString(scanner.next());
                int employeeCount = Integer.parseInt(scanner.next());
                String city = scanner.next();
                String country = scanner.next();
                new Account(id, contact, opportunity, companyName, industry, employeeCount, city, country);
            }
        } catch (FileNotFoundException exception) {
            printer.printImportErrorInfo(filename);
        }
    }

    public Product getProductFromString(String string) {
        for (Product product : Product.values()) {
            if (string.equals(product.name())) {
                return product;
            }
        }
        return null;
    }

    public Status getStatusFromString(String string) {
        for (Status status : Status.values()) {
            if (string.equals(status.name())) {
                return status;
            }
        }
        return null;
    }

    public Industry getIndustryFromString(String string) {
        for (Industry industry : Industry.values()) {
            if (string.equals(industry.name())) {
                return industry;
            }
        }
        return null;
    }
}
