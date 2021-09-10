package com.ironhack.TheCleanCodersCRMv2homework3.dao;

import com.ironhack.TheCleanCodersCRMv2homework3.output.Style;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Table(name = "contacts_table")
public class Contact extends Item {

    @OneToOne(mappedBy = "decisionMaker", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Opportunity opportunity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account")
    private Account account;

    // Constructor

    // Constructor called when a LEAD is converted
    public Contact(Lead lead, Account account) {
        setName(lead.getName());
        setPhoneNumber(lead.getPhoneNumber());
        setEmail(lead.getEmail());
        setCompanyName(lead.getCompanyName());
        setAccount(account);
    }

    // New Contact constructor including an associated Account
    public Contact(String name, String phoneNumber, String email, String companyName, Account account) {
        super(name, email, companyName, phoneNumber);
        setAccount(account);
    }

    @Override
    public String toString() {
        return "=== Contact " + getId() + " ===" + '\n' +
                "· name : " + getName() + '\n' +
                "· phone number : " + getPhoneNumber() + '\n' +
                "· email : " + getEmail() + '\n' +
                "· company name : " + getCompanyName() + '\n' +
                ". account : " +  getAccount();
    }

    public String toStringInOppClass() {
        return Style.DARK_GRAY + "CONTACT " + getId() + '\n' + Style.DEFAULT +
                "   - name : " + getName() + '\n' +
                "   - phone number : " + getPhoneNumber() + '\n' +
                "   - email : " + getEmail() + '\n' +
                "   - company name : " + getCompanyName();
    }

}

