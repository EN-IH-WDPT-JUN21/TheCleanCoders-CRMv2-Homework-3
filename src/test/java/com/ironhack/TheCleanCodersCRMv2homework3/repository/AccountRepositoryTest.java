package com.ironhack.TheCleanCodersCRMv2homework3.repository;

import com.ironhack.TheCleanCodersCRMv2homework3.utils.Data;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    private Data data;

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        data.populateRepos();
    }

    @AfterEach
    void tearDown() {
        data.cleanAllTables();
    }

    @Test
    void meanEmployeeCount() {
        int result = accountRepository.meanEmployeeCount();
        assertEquals(97.0,result);
    }

    @Test
    void medianEmployeeCount() {
        int result = accountRepository.medianEmployeeCount();
        assertEquals(74,result);
    }

    @Test
    void maxEmployeeCount() {
        int result = accountRepository.maxEmployeeCount();
        assertEquals(250,result);
    }

    @Test
    void minEmployeeCount() {
        int result = accountRepository.minEmployeeCount();
        assertEquals(12,result);
    }
}