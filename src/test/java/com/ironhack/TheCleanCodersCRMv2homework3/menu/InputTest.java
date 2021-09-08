package com.ironhack.TheCleanCodersCRMv2homework3.menu;

import com.ironhack.TheCleanCodersCRMv2homework3.enums.Command;
import com.ironhack.TheCleanCodersCRMv2homework3.enums.Industry;
import com.ironhack.TheCleanCodersCRMv2homework3.enums.ObjectType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputTest {

    static Input input;
    static Industry industry;

    @BeforeEach
    void setUp() {
            input = new Input(new Printer());
    }

    @Test
    void getCommandFromString() {
        assertEquals(Command.SHOW, input.getCommandFromString("SHOW"));
        assertEquals(Command.SHOW, input.getCommandFromString("show"));
        assertNull(input.getCommandFromString("abc"));
    }

    @Test
    void getObjectType() {
        assertEquals(ObjectType.ACCOUNT, input.getObjectType("ACCOUNT"));
        assertEquals(ObjectType.ACCOUNT, input.getObjectType("account"));
        assertNull(input.getObjectType("abc"));

        assertEquals(ObjectType.ACCOUNT, input.getObjectType("ACCOUNTS"));
        assertEquals(ObjectType.ACCOUNT, input.getObjectType("accounts"));
        assertNull(input.getObjectType("abc"));
    }

}