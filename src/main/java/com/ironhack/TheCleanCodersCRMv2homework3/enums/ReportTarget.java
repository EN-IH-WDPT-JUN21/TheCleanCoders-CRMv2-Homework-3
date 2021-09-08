package com.ironhack.TheCleanCodersCRMv2homework3.enums;

import lombok.Getter;

@Getter

public enum ReportTarget {
    LEAD("LEAD", "LEADS"),
    OPPORTUNITY("OPPORTUNITY", "OPPORTUNITIES"),
    CLOSED_WON("CLOSED-WON", "CLOSED-WONS"),
    CLOSED_LOST("CLOSED-LOST", "CLOSED-LOSTS"),
    OPEN("OPEN", "OPENS");

    private String singularForm;
    private String pluralForm;

    ReportTarget(String singularForm, String pluralForm) {
        this.singularForm = singularForm;
        this.pluralForm = pluralForm;
    }

}
