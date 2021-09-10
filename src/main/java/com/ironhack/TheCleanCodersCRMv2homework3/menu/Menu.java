package com.ironhack.TheCleanCodersCRMv2homework3.menu;

import com.ironhack.TheCleanCodersCRMv2homework3.dao.Account;
import com.ironhack.TheCleanCodersCRMv2homework3.dao.Lead;
import com.ironhack.TheCleanCodersCRMv2homework3.dao.Opportunity;
import com.ironhack.TheCleanCodersCRMv2homework3.enums.*;
import com.ironhack.TheCleanCodersCRMv2homework3.utils.Data;
import com.ironhack.TheCleanCodersCRMv2homework3.output.Style;
import com.ironhack.TheCleanCodersCRMv2homework3.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Objects;

@Component
public class Menu {

    final AccountRepository accountRepository;
    final SalesRepRepository salesRepRepository;
    final ContactRepository contactRepository;
    final LeadRepository leadRepository;
    final OpportunityRepository opportunityRepository;

    @Autowired
    Data data;
    @Autowired
    Creator creator;

    @Autowired
    public Menu(AccountRepository accountRepository, SalesRepRepository salesRepRepository, ContactRepository contactRepository, LeadRepository leadRepository, OpportunityRepository opportunityRepository) {
        this.accountRepository = accountRepository;
        this.salesRepRepository = salesRepRepository;
        this.contactRepository = contactRepository;
        this.leadRepository = leadRepository;
        this.opportunityRepository = opportunityRepository;
    }

    private final Printer printer = new Printer();
    private final Input input = new Input(printer);
//    private final FileManager fileManager = new FileManager(printer);


    public void controlLoop() throws InterruptedException {
        Command command;
        do {
            String[] inputList = splitInput(input.getString());
            command = input.getCommandFromString(inputList[0]);
            if (Objects.isNull(command)) {
                printer.printTypoInfo(inputList[0]);
            } else {
                try {
                    interpretInput(inputList);
                } catch (NullPointerException exception) {
                    printer.print(exception.getMessage());
                } catch (NumberFormatException exception) {
                    printer.print("Incorrect format of Id. Please try again.");
                } catch (ArrayIndexOutOfBoundsException exception) {
                    printer.print("Must input Id number. Please try again.");
                }
            }
        } while (command != Command.EXIT);
        System.out.println(Style.LIGHT_GRAY + "\n\n\n\n\n\n\n\n\n\n\nAll data saved");
        System.out.println(Style.OCHER + "Thank you for using the cleanCRM. Have a nice day.\n" + Style.DEFAULT);
        Thread.sleep(500);
        input.close();
    }

    public String[] splitInput(String string) {
        return string.trim().split(" ");
    }

    public void interpretInput(String[] inputList) throws InterruptedException {
        creator = new Creator(accountRepository, salesRepRepository, contactRepository, leadRepository, opportunityRepository, input, printer);
        Command command = input.getCommandFromString(inputList[0]);
        ObjectType objectType;
        int id;

        switch (command) {
            case NEW:
                objectType = input.getObjectType(inputList[1]);
                if (Objects.isNull(objectType)) {
                    printer.printTypoInfo(inputList[1]);
                } else {
                    create(objectType);
                }
                break;
            case SHOW:
                objectType = input.getObjectType(inputList[1]);
                if (Objects.isNull(objectType)) {
                    printer.printTypoInfo(inputList[1]);
                } else {
                    show(objectType);
                }
                break;
            case LOOKUP:
                objectType = input.getObjectType(inputList[1]);
                if (Objects.isNull(objectType)) {
                    printer.printTypoInfo(inputList[1]);
                } else {
                    id = Integer.parseInt(inputList[2]);
                    lookup(objectType, id);
                }
                break;
            case CONVERT:
                id = Integer.parseInt(inputList[1]);
                convert(id);
                break;
            case REPORT:
                ReportTarget reportTarget = input.getReportTarget(inputList[1]);
                ReportBy reportBy = input.getReportBy(inputList[3]);
                if (Objects.isNull(reportTarget)) {
                    printer.printTypoInfo(inputList[1]);
                } else if(Objects.isNull(reportBy)) {
                    printer.printTypoInfo(inputList[3]);
                } else if(inputList[2].equalsIgnoreCase("by")) {
                    report(reportTarget, reportBy);
                } else {
                    printer.print("Incorrect command structure. Please try again.");
                }
                break;
            case CLOSE_LOST:
                id = Integer.parseInt(inputList[1]);
                changeStatus(Status.CLOSED_LOST, id);
                break;
            case CLOSE_WON:
                id = Integer.parseInt(inputList[1]);
                changeStatus(Status.CLOSED_WON, id);
                break;
            case OPEN:
                id = Integer.parseInt(inputList[1]);
                changeStatus(Status.OPEN, id);
                break;
            case MEAN:
            case MEDIAN:
            case MIN:
            case MAX:
                StateObject stateObject = input.getStateObject(inputList[1]);
                if (Objects.isNull(stateObject)) {
                    printer.printTypoInfo(inputList[1]);
                } else {
                    reportStates(command, stateObject);
                }
                break;
            case POPULATE:
                data = new Data(accountRepository, salesRepRepository, contactRepository, leadRepository, opportunityRepository);
                data.populateRepos();
                break;
            case HELP:
                printer.helpPage();
                break;
        }
    }

    public void create(ObjectType objectType) {
        switch (objectType) {
            case ACCOUNT:
                creator.createAccount();
                break;
            case CONTACT:
                creator.createContact(); //When a CONTACT is created independently, it will ask for a LEAD Id
                break;
            case LEAD:
                creator.createLead();
                break;
            case OPPORTUNITY:
                creator.createOpportunity();
                break;
            case SALESREP:
                creator.createSalesRep();
                break;
        }
    }

    public void show(ObjectType objectType) {
        System.out.println(Style.OCHER + "\nShows all " + objectType.getPluralForm() + ".\n" + Style.DEFAULT);
        switch (objectType) {
            case ACCOUNT:
                creator.printAllAccounts();
                break;
            case CONTACT:
                creator.printAllContacts();
                break;
            case LEAD:
                creator.printAllLeads();
                break;
            case OPPORTUNITY:
                creator.printAllOpportunities();
                break;
            case SALESREP:
                creator.printAllSalesRep();
                break;
        }
    }

    public void lookup(ObjectType objectType, int id) {
        switch (objectType) {
            case ACCOUNT:
                System.out.println(creator.getAccountRepository().findById(Long.valueOf(id)).get());
                break;
            case CONTACT:
                System.out.println(creator.getContactRepository().findById(Long.valueOf(id)).get());
                break;
            case LEAD:
                System.out.println(creator.getLeadRepository().findById(Long.valueOf(id)).get());
                break;
            case OPPORTUNITY:
                System.out.println(creator.getOpportunityRepository().findById(Long.valueOf(id)).get());
                break;
            case SALESREP:
                System.out.println(creator.getSalesRepRepository().findById(Long.valueOf(id)).get());
                break;
        }
    }

    public void convert(int idLead) throws InterruptedException {
        // When a Lead is converted a Contact, Opportunity and Account are automatically created and the Lead must be deleted.
        Lead lead;
        try{
            lead = leadRepository.findById(Long.valueOf(idLead)).get();
        } catch (NoSuchElementException e) {
            System.out.println(Style.RED + "\nThe id entered does not correspond to any lead.");
            System.out.println(Style.DEFAULT + "\nPlease, select another option.");
            return;
        }
        System.out.println(Style.OCHER + "\nConverting LEAD nº " + idLead + " to CONTACT, ACCOUNT and OPPORTUNITY\n" + Style.DEFAULT);
        Thread.sleep(500);
        System.out.println(Style.OCHER + "\nWould you like to create a new Account? (Y/N)" + Style.DEFAULT);
        while(true) {
            String answer = input.getYesOrNo();
            if (answer.equals("Y") || answer.equals("YES")) {
                creator.createAccount();
                Thread.sleep(1000);
                System.out.println(Style.OCHER + "Converting Lead to Contact..." + Style.DEFAULT);
                Thread.sleep(2100);
                creator.createContact(lead);
                Thread.sleep(2000);
                creator.createOpportunityByLeadConversion(lead);
                break;
            } else if (answer.equals("N") || answer.equals("NO")) {
                int idAccount = creator.getExistingAccount();
                Account account = accountRepository.findById(Long.valueOf(idAccount)).get();
                System.out.println(Style.OCHER + "Converting Lead to Contact..." + Style.DEFAULT);
                Thread.sleep(2100);
                creator.createContact(lead, account);
                Thread.sleep(2000);
                creator.createOpportunityByLeadConversion(lead, account);
                break;
            } else {
                System.out.println("Invalid response.");
            }
        }

        Thread.sleep(1400);
        leadRepository.deleteById(Long.valueOf(idLead));
        System.out.println(Style.OCHER + "\n\n\n..." + Style.DEFAULT);
        Thread.sleep(1400);
        System.out.println(Style.OCHER + "\n\n\nLead has been successfully converted and deleted.\n\n\n" + Style.DEFAULT);
    }

    public void changeStatus(Status status, int id) {
        Opportunity opportunity;

        try{
            opportunity = opportunityRepository.findById(Long.valueOf(id)).get();
        } catch (NoSuchElementException e){
            System.out.println(Style.RED + "\nThe id entered does not correspond to any opportunity.");
            System.out.println(Style.DEFAULT + "\nPlease, select another option.");
            return;
        }

        opportunityRepository.findById(Long.valueOf(id));
        opportunity.setStatus(status);
        opportunityRepository.save(opportunity);
        System.out.println(Style.OCHER + "OPPORTUNITY with an id of " + id + " changed to " + status +".\n" + Style.DEFAULT);
    }

    public void report(ReportTarget reportTarget, ReportBy reportBy) {
        System.out.println(Style.OCHER + "\n" + reportTarget + " BY " + reportBy + Style.DEFAULT);

        switch (reportBy) {
            case SALESREP:
                switch (reportTarget) {
                    case LEAD:
                        printer.printReport(salesRepRepository.reportLeadsBySalesRep());
                        break;
                    case OPPORTUNITY:
                        printer.printReport(salesRepRepository.reportOpportunitiesBySalesRep());
                        break;
                    case CLOSED_WON:
                        printer.printReport(salesRepRepository.reportClosedWonOpportunitiesBySalesRep());
                        break;
                    case CLOSED_LOST:
                        printer.printReport(salesRepRepository.reportClosedLostOpportunitiesBySalesRep());
                        break;
                    case OPEN:
                        printer.printReport(salesRepRepository.reportOpenOpportunitiesBySalesRep());
                        break;
                }
            break;
            case PRODUCT:
                switch (reportTarget) {
                    case OPPORTUNITY:
                        printer.printReport(opportunityRepository.reportOpportunityByProduct());
                        break;
                    case CLOSED_WON:
                        printer.printReport(opportunityRepository.reportClosedWonOpportunityByProduct());
                        break;
                    case CLOSED_LOST:
                        printer.printReport(opportunityRepository.reportClosedLostOpportunityByProduct());
                        break;
                    case OPEN:
                        printer.printReport(opportunityRepository.reportOpenOpportunityByProduct());
                        break;
                }
            break;
            case COUNTRY:
                switch (reportTarget) {
                    case OPPORTUNITY:
                        printer.printReport(opportunityRepository.reportOpportunitiesByCountry());
                        break;
                    case CLOSED_WON:
                        printer.printReport(opportunityRepository.reportClosedWonOpportunitiesByCountry());
                        break;
                    case CLOSED_LOST:
                        printer.printReport(opportunityRepository.reportClosedLostOpportunitiesByCountry());
                        break;
                    case OPEN:
                        printer.printReport(opportunityRepository.reportOpenOpportunitiesByCountry());
                        break;
                }
            break;
            case CITY:
                switch (reportTarget) {
                    case OPPORTUNITY:
                        printer.printReport(opportunityRepository.reportOpportunitiesByCity());
                        break;
                    case CLOSED_WON:
                        printer.printReport(opportunityRepository.reportClosedWonOpportunitiesByCity());
                        break;
                    case CLOSED_LOST:
                        printer.printReport(opportunityRepository.reportClosedLostOpportunitiesByCity());
                        break;
                    case OPEN:
                        printer.printReport(opportunityRepository.reportOpenOpportunitiesByCity());
                        break;
                }
            break;
            case INDUSTRY:
                switch (reportTarget) {
                    case OPPORTUNITY:
                        printer.printReport(opportunityRepository.reportOpportunitiesByIndustry());
                        break;
                    case CLOSED_WON:
                        printer.printReport(opportunityRepository.reportClosedWonOpportunitiesByIndustry());
                        break;
                    case CLOSED_LOST:
                        printer.printReport(opportunityRepository.reportClosedLostOpportunitiesByIndustry());
                        break;
                    case OPEN:
                        printer.printReport(opportunityRepository.reportOpenOpportunitiesByIndustry());
                        break;
                }
            break;
            }
        System.out.println("\n");
    }

    public void reportStates(Command command, StateObject stateObject) {

        switch (stateObject) {
            case EMPLOYEECOUNT:
                System.out.println(Style.OCHER + "\n" + command + " " + stateObject + Style.DEFAULT);
                switch (command) {
                    case MEAN:
                        System.out.println(accountRepository.meanEmployeeCount());
                        break;
                    case MEDIAN:
                        System.out.println(accountRepository.medianEmployeeCount());
                        break;
                    case MAX:
                        System.out.println(accountRepository.maxEmployeeCount());
                        break;
                    case MIN:
                        System.out.println(accountRepository.minEmployeeCount());
                        break;
                }
            break;
            case QUANTITY:
                System.out.println(Style.OCHER + "\n" + command + " " + stateObject + " of products order" + Style.DEFAULT);
                switch (command) {
                    case MEAN:
                        System.out.println(opportunityRepository.meanQuantity());
                        break;
                    case MEDIAN:
                        System.out.println(opportunityRepository.medianQuantity());
                        break;
                    case MAX:
                        System.out.println(opportunityRepository.maxQuantity());
                        break;
                    case MIN:
                        System.out.println(opportunityRepository.minQuantity());
                        break;

                }
            break;
            case OPPORTUNITY:
                System.out.println(Style.OCHER + "\n" + command + " number of " + stateObject.getPluralForm()
                        + " associated with an ACCOUNT" + Style.DEFAULT);
                switch (command) {
                    case MEAN:
                        System.out.println(opportunityRepository.meanOppsPerAccount());
                        break;
                    case MEDIAN:
                        System.out.println(opportunityRepository.medianOppsPerAccount());
                        break;
                    case MAX:
                        System.out.println(opportunityRepository.maxOppsPerAccount());
                        break;
                    case MIN:
                        System.out.println(opportunityRepository.minOppsPerAccount());
                        break;
                }
            break;
        }
        System.out.println("\n");
    }

}