package com.ironhack.TheCleanCodersCRMv2homework3.menu;

import com.ironhack.TheCleanCodersCRMv2homework3.TheCleanCodersCrMv2Homework3Application;
import com.ironhack.TheCleanCodersCRMv2homework3.enums.Status;
import com.ironhack.TheCleanCodersCRMv2homework3.repository.*;
import com.ironhack.TheCleanCodersCRMv2homework3.utils.Data;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MenuTest {

    @MockBean
    private TheCleanCodersCrMv2Homework3Application theCleanCodersCrMv2Homework3Application;

    @Autowired
    private Data data;

    @Autowired
    private Menu menu;

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

    @Test
    public void interpretInput_nonRecognizableCommand() throws InterruptedException {
        String[] command1 = {"Hola"};
        String[] command2 = {"123"};
        String[] command3 = {"covert"};

        assertThrows(NullPointerException.class, () -> menu.interpretInput(command1));
        assertThrows(NullPointerException.class, () -> menu.interpretInput(command2));
        assertThrows(NullPointerException.class, () -> menu.interpretInput(command3));
    }

    @Test
    public void changeStatus_validModification() {
        data.populateRepos();
        menu.changeStatus(Status.CLOSED_WON, 1);
        assertEquals(Status.CLOSED_WON, opportunityRepository.findById(Long.valueOf(1)).get().getStatus());
    }


}
