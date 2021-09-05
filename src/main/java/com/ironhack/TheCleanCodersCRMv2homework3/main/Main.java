package com.ironhack.TheCleanCodersCRMv2homework3.main;

import com.ironhack.TheCleanCodersCRMv2homework3.enums.ObjectType;
import com.ironhack.TheCleanCodersCRMv2homework3.menu.Menu;
import com.ironhack.TheCleanCodersCRMv2homework3.menu.Printer;
import com.ironhack.TheCleanCodersCRMv2homework3.repository.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Main {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private SalesRepRepository salesRepRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private OpportunityRepository opportunityRepository;


    public void consoleRun() throws InterruptedException{

        Menu menu = new Menu(accountRepository, salesRepRepository, contactRepository, leadRepository, opportunityRepository);
        Printer printer = new Printer();
        printer.welcomeMessage();
        menu.controlLoop();

    }

}
