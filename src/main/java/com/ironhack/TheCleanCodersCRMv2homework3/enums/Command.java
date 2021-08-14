package com.ironhack.TheCleanCodersCRMv2homework3.enums;

public enum Command {
    NEW("NEW"),
    SHOW("SHOW"),
    LOOKUP("LOOKUP"),
    CONVERT("CONVERT"),
    CLOSE_LOST("CLOSE-LOST"),
    CLOSE_WON("CLOSE-WON"),
    OPEN("OPEN"),
    EXIT("EXIT"),
    HELP("HELP"),
    SAVE("SAVE");

    private String expectedInput;

    Command(String expectedInput) {
        this.expectedInput = expectedInput;
    }

    public String getExpectedInput() {
        return expectedInput;
    }
}