package com.ironhack.TheCleanCodersCRMv2homework3.enums;

public enum ObjectType {
    ACCOUNT("ACCOUNT", "ACCOUNTS"),
    CONTACT("CONTACT", "CONTACTS"),
    LEAD("LEAD", "LEADS"),
    OPPORTUNITY("OPPORTUNITY", "OPPORTUNITIES"),
    SALESREP("SALESREP", "SALESREPS");

    private String singularForm;
    private String pluralForm;

    ObjectType(String singularForm, String pluralForm) {
        this.singularForm = singularForm;
        this.pluralForm = pluralForm;
    }

    public String getSingularForm() {
        return singularForm;
    }

    public String getPluralForm() {
        return pluralForm;
    }
}