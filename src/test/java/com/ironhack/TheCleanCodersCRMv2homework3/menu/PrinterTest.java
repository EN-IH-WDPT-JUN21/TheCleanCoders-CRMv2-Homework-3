package com.ironhack.TheCleanCodersCRMv2homework3.menu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PrinterTest {

    static Printer printer = new Printer();
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void print() {
        printer.print("some text to print");
        assertEquals("some text to print", outputStreamCaptor.toString().trim());
        assertNotEquals("different to print", outputStreamCaptor.toString().trim());
    }

    @Test
    void printTypoInfo() {
        printer.printTypoInfo("WRONG-WORD");
        assertEquals("Incorrect command. Possible typo in the word \"WRONG-WORD\". Please try again.", outputStreamCaptor.toString().trim());
        assertNotEquals("Incorrect command. Possible typo in the word \"DIFFERENT-WRONG-WORD\". Please try again.", outputStreamCaptor.toString().trim());
    }

    @Test
    void printExportErrorInfo() {
        printer.printExportErrorInfo("SOME_FILE");
        assertEquals("There was an error while exporting data to file SOME_FILE", outputStreamCaptor.toString().trim());
        assertNotEquals("There was an error while exporting data to file DIFFERENT_FILE", outputStreamCaptor.toString().trim());
    }

    @Test
    void printImportErrorInfo() {
        printer.printImportErrorInfo("SOME_FILE");
        assertEquals("There was an error while importing data from file SOME_FILE", outputStreamCaptor.toString().trim());
        assertNotEquals("There was an error while importing data from file DIFFERENT_FILE", outputStreamCaptor.toString().trim());
    }
}