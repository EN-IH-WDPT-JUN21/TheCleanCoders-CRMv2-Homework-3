package com.ironhack.TheCleanCodersCRMv2homework3.repository;

import com.ironhack.TheCleanCodersCRMv2homework3.TheCleanCodersCrMv2Homework3Application;
import com.ironhack.TheCleanCodersCRMv2homework3.utils.Data;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SalesRepRepositoryTest {

    @MockBean
    private TheCleanCodersCrMv2Homework3Application theCleanCodersCrMv2Homework3Application;

    @Autowired
    private Data data;

    @Autowired
    private SalesRepRepository salesRepRepository;

    @BeforeEach
    void setUp() {
        data.populateRepos();
    }

    @AfterEach
    void tearDown() {
        data.cleanAllTables();
    }


    @Test
    void  reportLeadsBySalesRep(){
        String[][] result = salesRepRepository.reportLeadsBySalesRep();

        assertEquals(4, result.length);
        assertEquals("2",result[2][1]);
        assertEquals("Atul Gupta",result[3][0]);
    }

    @Test
    void reportOpportunitiesBySalesRep() {
        String[][] result = salesRepRepository.reportOpportunitiesBySalesRep();

        assertEquals(3, result.length);
        assertEquals("2",result[0][1]);
        assertEquals("Julia Dunst",result[2][0]);
    }

    @Test
    void reportClosedWonOpportunitiesBySalesRep() {
        String[][] result = salesRepRepository.reportClosedWonOpportunitiesBySalesRep();

        assertEquals(0, result.length);
    }

    @Test
    void reportClosedLostOpportunitiesBySalesRep() {
        String[][] result = salesRepRepository.reportClosedLostOpportunitiesBySalesRep();

        assertEquals(0, result.length);
    }

    @Test
    void reportOpenOpportunitiesBySalesRep() {
        String[][] result = salesRepRepository.reportOpenOpportunitiesBySalesRep();

        assertEquals(3, result.length);
        assertEquals("2",result[0][1]);
        assertEquals("Marian Garcia",result[0][0]);
    }
}