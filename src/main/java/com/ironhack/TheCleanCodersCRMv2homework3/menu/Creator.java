package com.ironhack.TheCleanCodersCRMv2homework3.menu;

import com.ironhack.TheCleanCodersCRMv2homework3.dao.*;
import com.ironhack.TheCleanCodersCRMv2homework3.enums.Industry;
import com.ironhack.TheCleanCodersCRMv2homework3.enums.Product;
import com.ironhack.TheCleanCodersCRMv2homework3.output.Style;
import com.ironhack.TheCleanCodersCRMv2homework3.repository.*;
import com.ironhack.TheCleanCodersCRMv2homework3.utils.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Getter
@Setter

@Component
public class Creator {

    final AccountRepository accountRepository;
    final SalesRepRepository salesRepRepository;
    final ContactRepository contactRepository;
    final LeadRepository leadRepository;
    final OpportunityRepository opportunityRepository;

    @Autowired
    Data data;

    private Input input;
    private Printer printer;

    @Autowired
    public Creator(AccountRepository accountRepository, SalesRepRepository salesRepRepository, ContactRepository contactRepository, LeadRepository leadRepository, OpportunityRepository opportunityRepository, Input input, Printer printer) {
        this.accountRepository = accountRepository;
        this.salesRepRepository = salesRepRepository;
        this.contactRepository = contactRepository;
        this.leadRepository = leadRepository;
        this.opportunityRepository = opportunityRepository;
        this.input = input;
        this.printer = printer;
    }

    // Method used to create a SALES REP independently
    public void createSalesRep() {
        System.out.println(Style.OCHER + "\nCreating a new SALES REP." + Style.DEFAULT);

        String name;
        boolean errorName = false;
        do {

            System.out.println("\nPlease input a name:");
            name = input.getString();

            try {
                errorName = Validator.isStringValid(name);
            } catch (IllegalArgumentException e) {
                System.out.println("Input is whether empty or too long, or it may contain not valid characters.");
            }

        } while (!errorName);

        // New SALES REP Object saved in database
        SalesRep salesRep = new SalesRep(name);
        salesRepRepository.save(salesRep);

        // Print a SALES REP creation message
        System.out.println(Style.OCHER + "\nA new SALES REP has been created with the following info:" + Style.DEFAULT);
        System.out.println(salesRep);

    }

    // Method used to create an ACCOUNT independently
    public void createAccount() {

        System.out.println(Style.OCHER + "\nCreating a new ACCOUNT." + Style.DEFAULT);
        System.out.println("Please input the following:");

        System.out.println("\nChoose type of Industry:");
        Industry industry = input.chooseIndustry();

        System.out.println("\nNumber of employees of the Company:");
        int employeeCount = input.getIntegerHigherThanZero();


        String city;
        boolean errorCity = false;
        do {

            System.out.println("\nCity where the Company is based:");
            city = input.getString();

            try {
                errorCity = Validator.isStringValid(city);
            } catch (IllegalArgumentException e) {
                System.out.println("Input is whether empty or too long, or it may contain not valid characters.");
            }

        } while (!errorCity);


        String country;
        boolean errorCountry = false;
        do {

            System.out.println("\nCountry where the Company is based:");
            country = input.getString();

            try {
                errorCountry = Validator.isStringValid(country);
            } catch (IllegalArgumentException e) {
                System.out.println("Input is whether empty or too long, or it may contain not valid characters.");
            }

        } while (!errorCountry);

        // New ACCOUNT Object saved in database
        Account account = new Account(industry, employeeCount, city, country);
        accountRepository.save(account);

        // Print an ACCOUNT creation message
        System.out.println(Style.OCHER + "\nNew ACCOUNT created:" + Style.DEFAULT);
        System.out.println(account);

    }

    // Method that creates an ACCOUNT when LEAD is converted
    public void createAccount(Lead lead) {
        // Asks user all the necessary info and then calls constructor
        System.out.println(Style.OCHER + "\nAdditional information required for the ACCOUNT." + Style.DEFAULT);
        System.out.println("\nPlease input the following:");

        System.out.println("\nChoose type of Industry:");
        Industry industry = input.chooseIndustry();

        System.out.println("\nNumber of employees of the Company");
        int employeeCount = input.getIntegerHigherThanZero();


        String city;
        boolean errorCity = false;
        do {

            System.out.println("\nCity where the Company is based:");
            city = input.getString();

            try {
                errorCity =Validator.isStringValid(city);
            } catch (IllegalArgumentException e) {
                System.out.println("Input is whether empty or too long, or it may contain not valid characters.");
            }

        } while (!errorCity);


        String country;
        boolean errorCountry = false;
        do {

            System.out.println("\nCountry where the Company is based:");
            country = input.getString();

            try {
                errorCountry = Validator.isStringValid(country);
            } catch (IllegalArgumentException e) {
                System.out.println("Input is whether empty or too long, or it may contain not valid characters.");
            }

        } while (!errorCountry);

        Account account = new Account(industry, employeeCount, city, country);

        // New ACCOUNT Object saved in database
        accountRepository.save(account);

        // Print an ACCOUNT creation message
        System.out.println(Style.OCHER + "\nA new ACCOUNT has been created with the following info:" + Style.DEFAULT);
        System.out.println(account);

    }

    // Method used to create a LEAD independently
    public void createLead() {
        // First it is checked if there is any Sales Rep as we need to associate one to our Lead.
        if(salesRepRepository.count() == 0L){
            System.out.println(Style.RED + "\nThere is no Sales Rep saved in this database. A new Lead cannot be created." +  Style.DEFAULT);
            System.out.println("\nPlease, select other option.");
            return;
        } else {
            System.out.println(Style.OCHER + "\nCreating a new LEAD." + Style.DEFAULT);
            System.out.println("Please input the following:");
        }

        // Then, the user is asked for all the necessary info

        String name;
        boolean errorName = false;
        do {

            System.out.println("\nName:");
            name = input.getString();

            try {
                errorName = Validator.isStringValid(name);
            } catch (IllegalArgumentException e) {
                System.out.println("Input is whether empty or too long, or it may contain not valid characters.");
            }

        } while (!errorName);


        String phoneNumber;
        boolean errorPhone = false;
        do {

            System.out.println("\nPhone Number:");
            phoneNumber = input.getString();

            try {
                errorPhone = Validator.isPhoneNumberValid(phoneNumber);
            } catch (IllegalArgumentException e) {
                System.out.println("The phone number must have 9 digits. A prefix can be included.");
            }

        } while (!errorPhone);


        String email;
        boolean errorEmail = false;
        do {

            System.out.println("\nEmail:");
            email = input.getString();

            try {
                errorEmail = Validator.isEmailValid(email);
            } catch (IllegalArgumentException e) {
                System.out.println("The email address format is not valid.");
            }

        } while (!errorEmail);


        String companyName;
        boolean errorCompany = false;
        do {

            System.out.println("\nCompany Name:");
            companyName = input.getString();

            try {
                errorCompany = Validator.isStringValid(companyName);
            } catch (IllegalArgumentException e) {
                System.out.println("Input is whether empty or too long, or it may contain not valid characters.");
            }

        } while (!errorCompany);


        int idSalesRep;
        boolean errorSRep = false;
        do {

            System.out.println("\nSALES REP id:");
            idSalesRep = input.getIntegerHigherThanZero();

            try{
                salesRepRepository.findById(Long.valueOf(idSalesRep)).get();
                errorSRep = true;
            } catch (NoSuchElementException e){
                System.out.println(Style.RED + "\nThe id entered does not correspond to any sales rep.");
                System.out.println(Style.DEFAULT + "\nPlease, try again.");
            }

        } while(!errorSRep);

        // New LEAD Object saved in database
        Lead lead = new Lead(name, phoneNumber, email, companyName, salesRepRepository.findById(Long.valueOf(idSalesRep)).get());
        leadRepository.save(lead);

        // Print a LEAD creation message
        System.out.println(Style.OCHER + "\nA new LEAD has been created with the following info:" + Style.DEFAULT);
        System.out.println(lead);

    }

    //  Method that creates a CONTACT automatically when LEAD is converted
    public void createContact(Lead lead) {
        // This method is implemented right after the createAccount(Lead lead) method, triggered by the "convert <id number>" command.
        // This means that the Account associated is the last one saved in the accountRepository.
        Contact contact = new Contact(lead, accountRepository.findById(Long.valueOf(accountRepository.count())).get());

        // New CONTACT Object saved in database with the LEAD information
        contactRepository.save(contact);

        // Print a CONTACT creation message
        System.out.println(Style.OCHER + "\nA new CONTACT has been created with the following info:" + Style.DEFAULT);
        System.out.println(contact);
    }

    // Method used to create a CONTACT independently from a LEAD
    public void createContact() {
        // First it is checked if there is any Lead and any Account as we need to associate one of each to our Contact.
        if(leadRepository.count() == 0L){
            System.out.println(Style.RED + "\nThere is no Lead saved in this database. A new Contact cannot be created." +  Style.DEFAULT);
            System.out.println("\nPlease, select other option.");
            return;
        } else if(accountRepository.count() == 0L){
            System.out.println(Style.RED + "\nThere is no Account saved in this database. A new Contact cannot be created." +  Style.DEFAULT);
            System.out.println("\nPlease, select other option.");
            return;
        } else {
            System.out.println(Style.OCHER + "\nCreating a new CONTACT." + Style.DEFAULT);
            System.out.println("Please input the following:");
        }

        // Then, the user is asked for all the necessary info

        int idLead;
        boolean errorLead = false;
        do {

            System.out.println("\nLEAD id:");
            idLead = input.getIntegerHigherThanZero();

            try{
                leadRepository.findById(Long.valueOf(idLead)).get();
                errorLead = true;
            } catch (NoSuchElementException e){
                System.out.println(Style.RED + "\nThe id entered does not correspond to any lead.");
                System.out.println(Style.DEFAULT + "\nPlease, try again.");
            }

        } while(!errorLead);


        int idAccount;
        boolean errorAccount = false;
        do {

            System.out.println("\nACCOUNT id:");
            idAccount = input.getIntegerHigherThanZero();

            try{
                accountRepository.findById(Long.valueOf(idAccount)).get();
                errorAccount = true;
            } catch (NoSuchElementException e){
                System.out.println(Style.RED + "\nThe id entered does not correspond to any account.");
                System.out.println(Style.DEFAULT + "\nPlease, try again.");
            }

        } while(!errorAccount);

        // New CONTACT Object saved in database
        Contact contact = new Contact(leadRepository.findById(Long.valueOf(idLead)).get(), accountRepository.findById(Long.valueOf(idAccount)).get());
        contactRepository.save(contact);

        // Print a CONTACT creation message
        System.out.println(Style.OCHER + "\nA new CONTACT has been created with the following info:" + Style.DEFAULT);
        System.out.println(contact);

    }

    // Method used when an OPPORTUNITY is created independently
    public void createOpportunity() {
        // First it is checked if there is any Contact and Sales Rep as we need to associate one of each to our Opportunity.
        if(contactRepository.count() == 0L){
            System.out.println(Style.RED + "\nThere is no Contact saved in this database. A new Opportunity cannot be created." +  Style.DEFAULT);
            System.out.println("\nPlease, select other option.");
            return;
        } else if(salesRepRepository.count() == 0L) {
            System.out.println(Style.RED + "\nThere is no Sales Rep saved in this database. A new Opportunity cannot be created." +  Style.DEFAULT);
            System.out.println("\nPlease, select other option.");
            return;
        } else {
            System.out.println(Style.OCHER + "\nCreating a new Opportunity." + Style.DEFAULT);
            System.out.println("Please input the following:");
        }

        // Then, the user is asked for all the necessary info

        int idContact;
        boolean errorContact = false;
        do {

            System.out.println("\nCONTACT id:");
            idContact = input.getIntegerHigherThanZero();

            try{
                contactRepository.findById(Long.valueOf(idContact)).get();
                errorContact = true;
            } catch (NoSuchElementException e){
                System.out.println(Style.RED + "\nThe id entered does not correspond to any contact.");
                System.out.println(Style.DEFAULT + "\nPlease, try again.");
            } catch (Exception e) {
                System.out.println(Style.RED + "\nThe selected Contact has already an Opportunity.");
                System.out.println(Style.DEFAULT +"\nWould you like to go back to the main menu? (Y/N)");
                while(true) {
                    String answer = input.getYesOrNo();
                    if(answer.equals("Y") || answer.equals("YES")){
                        System.out.println("\nPlease, select other option.");
                        return;
                    } else if (answer.equals("N") || answer.equals("NO")){
                        break;
                    } else {
                        System.out.println(Style.LIGHT_GRAY +"Invalid answer." + Style.DEFAULT);
                    }
                }
            }

        } while(!errorContact);

        int idSalesRep;
        boolean errorSRep = false;
        do {

            System.out.println("\nSALES REP id:");
            idSalesRep = input.getIntegerHigherThanZero();

            try{
                salesRepRepository.findById(Long.valueOf(idSalesRep)).get();
                errorSRep = true;
            } catch (NoSuchElementException e){
                System.out.println(Style.RED + "\nThe id entered does not correspond to any sales rep.");
                System.out.println(Style.DEFAULT + "\nPlease, try again.");
            }

        } while(!errorSRep);

        System.out.println("\nChoose the product");
        Product product = input.chooseProduct();

        System.out.println("\nQuantity of trucks:");
        int quantity = input.getIntegerHigherThanZero();

        // New OPPORTUNITY Object saved in database
        Opportunity opportunity = new Opportunity(product, quantity, contactRepository.findById(Long.valueOf(idContact)).get(),
                salesRepRepository.findById(Long.valueOf(idSalesRep)).get(), contactRepository.findById(Long.valueOf(idContact)).get().getAccount());
        opportunityRepository.save(opportunity);

        // Print an OPPORTUNITY creation message
        System.out.println("\nA new OPPORTUNITY has been created with the following info:");
        System.out.println(opportunity);
    }

    // Method that creates an OPPORTUNITY when LEAD is converted
    public void createOpportunityByLeadConversion(Lead lead) {
        System.out.println(Style.OCHER + "\nAdditional information required for the OPPORTUNITY." + Style.DEFAULT);
        System.out.println("\nPlease input the following:");


        System.out.println("\nProduct type:");
        Product product = input.chooseProduct();


        System.out.println("\nQuantity of trucks");
        int quantity = input.getIntegerHigherThanZero();


        // This method is implemented right after the createContact(Lead lead) method and the createAccount(Lead lead) method,
        // triggered by the "convert <id number>" command. This means that the Contact associated with this Opportunity is the
        // last one saved in the contactRepository and the Account associated is the last one saved in the accountRepository.
        Opportunity opportunity = new Opportunity(product, quantity, contactRepository.findById(Long.valueOf(contactRepository.count())).get(),
                lead.getSalesRep(), accountRepository.findById(Long.valueOf(accountRepository.count())).get());

        // New OPPORTUNITY Object saved in database
        opportunityRepository.save(opportunity);

        // Print an OPPORTUNITY creation message
        System.out.println(Style.OCHER + "\nA new OPPORTUNITY has been created with the following info:" + Style.DEFAULT);
        System.out.println(opportunity);
    }

    public void printAllAccounts() {
        for(Account account : accountRepository.findAll()){
            System.out.println(account);
        }
    }

    public void printAllContacts() {
        for(Contact contact : contactRepository.findAll()){
            System.out.println(contact);
        }
    }

    public void printAllLeads() {
        for(Lead lead : leadRepository.findAll()){
            System.out.println(lead);
        }
    }

    public void printAllOpportunities() {
        for(Opportunity opportunity : opportunityRepository.findAll()){
            System.out.println(opportunity);
        }
    }

    public void printAllSalesRep() {
        for(SalesRep salesRep : salesRepRepository.findAll()){
            System.out.println(salesRep);
        }
    }

}
