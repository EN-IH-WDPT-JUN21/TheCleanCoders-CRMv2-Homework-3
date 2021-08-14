package com.ironhack.TheCleanCodersCRMv2homework3.classes;


import com.ironhack.TheCleanCodersCRMv2homework3.output.Style;

import java.util.ArrayList;
import java.util.List;

public class Contact extends Item{

    // Properties

    private String name;
    private String phoneNumber;
    private String email;
    private String companyName;
    public static List<Item> allContacts = new ArrayList<>();

    // Constructor

    public Contact(Lead lead) {
        super(allContacts);
        setName(lead.getName());
        setPhoneNumber(lead.getPhoneNumber());
        setEmailAddress(lead.getEmail());
        setCompanyName(lead.getCompanyName());
    }

    public Contact(int id, String name, String phoneNumber, String email, String companyName) {
        super(id, allContacts);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
    }

// Setters and getters

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmailAddress(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public static List<Item> getAllContacts() {
        return allContacts;
    }

    @Override
    public String toString() {
        return "=== Contact " + getId() + " ===" + '\n' +
                "· name : " + name + '\n' +
                "· phone number : " + phoneNumber + '\n' +
                "· email : " + email + '\n' +
                "· company name : " + companyName + '\n';
    }

    public String toStringInOppClass() {
        return Style.DARK_GRAY + "CONTACT " + getId() + '\n' + Style.DEFAULT +
                "   - name : " + name + '\n' +
                "   - phone number : " + phoneNumber + '\n' +
                "   - email : " + email + '\n' +
                "   - company name : " + companyName;
    }
}

