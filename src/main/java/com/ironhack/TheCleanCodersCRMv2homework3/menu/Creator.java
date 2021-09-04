package com.ironhack.TheCleanCodersCRMv2homework3.menu;

import com.ironhack.TheCleanCodersCRMv2homework3.dao.Contact;
import com.ironhack.TheCleanCodersCRMv2homework3.dao.Lead;
import com.ironhack.TheCleanCodersCRMv2homework3.enums.Industry;
import com.ironhack.TheCleanCodersCRMv2homework3.enums.Product;
import com.ironhack.TheCleanCodersCRMv2homework3.output.Style;
import com.ironhack.TheCleanCodersCRMv2homework3.repository.*;
import com.ironhack.TheCleanCodersCRMv2homework3.utils.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    // Method used automatically when LEAD is converted
    public void createAccount(Lead lead) {
        // Asks user all the necessary info and then calls constructor
        System.out.println("Additional information for the" + Style.OCHER + " ACCOUNT." + Style.DEFAULT + " Please input the following:");

        System.out.println("\nChoose type of Industry:");
        Industry industry = input.chooseIndustry();

        System.out.println("\nNow input number of employees of the Company");
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

//        Account account = new Account((Contact) Contact.allContacts.get(Contact.allContacts.size()-1),
//                (Opportunity) Opportunity.allOpportunities.get(Opportunity.allOpportunities.size()-1),
//                industry, employeeCount, city, country);
        System.out.println("\nNew ACCOUNT created:");
//        System.out.println(account.toString());

    }

    // Method used to create an ACCOUNT independently.
    public void createAccount() {
        // It will request CONTACT id, OPPORTUNITY id
        System.out.println(Style.OCHER + "Creating an independent ACCOUNT" + Style.DEFAULT);

        System.out.println("\nPlease insert related CONTACT id number:");
        int contactId = input.getIntegerHigherThanZero();
//        Contact contact = (Contact) Contact.getById(contactId, Contact.allContacts);

        System.out.println("\nPlease insert related OPPORTUNITY id number");
        int opportunityId = input.getIntegerHigherThanZero();
//        Opportunity opportunity = (Opportunity) Opportunity.getById(opportunityId, Opportunity.allOpportunities);

        System.out.println("\nPlease choose the Industry of the related Company:");
        Industry industry = input.chooseIndustry();

        System.out.println("\nPlease insert number of employees of the Company (Integer higher than 0)");
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
                Validator.isStringValid(country);
            } catch (IllegalArgumentException e) {
                System.out.println("Input is whether empty or too long, or it may contain not valid characters.");
            }

        } while (!errorCountry);

//        Account account = new Account(contact, opportunity, industry, employeeCount, city, country);
        System.out.println("\nNew ACCOUNT created:");
//        System.out.println(account.toString());

    }

    //  Method that creates a CONTACT automatically when LEAD is converted
    public void createContact(Lead lead) {
        Contact contact = new Contact(lead);
    }

    // Method used to create a CONTACT independently.
    public void createContact() {
        // It will request LEAD id
        System.out.println(Style.OCHER + "Creating a new CONTACT." + Style.DEFAULT);

        System.out.println("\nPlease insert LEAD id to be used:");
        int idLead = input.getIntegerHigherThanZero();
//        Lead lead = (Lead) Lead.getById(idLead, Lead.allLeads);
//        Contact contact = new Contact(lead);

        System.out.println("\nNew CONTACT created:");
//        System.out.println(contact);

    }

    public void createLead() {
        // First it is checked if there is any Sales Rep as we need to associate one to our lead.
        try{
            if(!salesRepRepository.findAll().isEmpty()) {
                System.out.println(Style.OCHER + "Creating a new LEAD." + Style.DEFAULT);
                System.out.println("Please input the following:");
            }
        } catch(NullPointerException e){
            System.out.println(Style.RED + "\nThere is no Sales Rep saved in this database. A new Lead cannot be created." +  Style.DEFAULT);
            System.out.println("\nPlease, select other option.");
            return;
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

        Long idNumber;
        while(true){

            System.out.println("\nSales Rep id:");
            int idSalesRep = input.getIntegerHigherThanZero();
            idNumber = Long.valueOf(idSalesRep);

            try{
                if(salesRepRepository.findById(idNumber).isPresent()) {
                    break;
                }
            } catch (NullPointerException e){
                System.out.println("The id entered does not correspond to any sales rep.");
            }
        };


        Lead lead = new Lead(name, phoneNumber, email, companyName, salesRepRepository.findById(idNumber).get());
        leadRepository.save(lead);


        // Print a LEAD creation message, using Lead.toString method on the last element of leadsList, newly created
        System.out.println("\nA new LEAD was created with the following info:");
        System.out.println(lead);

    }

    public void createOpportunityByLeadConversion() {
        System.out.println("Additional information for the" + Style.OCHER + " OPPORTUNITY." + Style.DEFAULT + " Please input the following:");

        System.out.println("\nProduct type:");
        Product product = input.chooseProduct();

        System.out.println("\nQuantity of trucks");
        int quantity = input.getIntegerHigherThanZero(); // Added this method to Input class.

        //This method should be implemented right after the createContact method, and both are triggered by the
        // "convert <id number>" command. So it will use the last contact it was created in the allContactsList array.
        //Constructing the Opportunity object, and storing it in the allOpportunitiesList:
//        Opportunity opportunity = new Opportunity(product, quantity,
//                (Contact) Contact.allContacts.get(Contact.allContacts.size()-1));
        System.out.println("\n\nA new Opportunity was created as follows:");
//        System.out.println(opportunity);
    }

    // Method used when an OPPORTUNITY is created independently, it still depends on a CONTACT id
    public void createOpportunity() {
        System.out.println(Style.OCHER + "Creating an independent OPPORTUNITY. " + Style.DEFAULT);

        System.out.println("\nPlease insert the Id number of the CONTACT to be associated: ");
        int contactId = input.getIntegerHigherThanZero();

        System.out.println("\nPlease choose the product");
        Product product = input.chooseProduct();

        System.out.println("\nQuantity of trucks:");
        int quantity = input.getIntegerHigherThanZero();

//        Opportunity opportunity = new Opportunity(product, quantity,
//                (Contact) Contact.getById(contactId, Contact.allContacts));
        System.out.println("\nNew OPPORTUNITY created as follows:");
//        System.out.println(opportunity);
    }

}
