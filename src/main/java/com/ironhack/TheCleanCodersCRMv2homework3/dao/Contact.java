package com.ironhack.TheCleanCodersCRMv2homework3.dao;

import com.ironhack.TheCleanCodersCRMv2homework3.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Table(name = "contacts_table")
public class Contact extends Item {

    @OneToOne(mappedBy = "decisionMaker", cascade = CascadeType.ALL)
    private Opportunity opportunity;

    @ManyToOne
    @JoinColumn(name = "account")
    private Account account;

    // Constructor

    // Constructor called when a lead is converted
    public Contact(Lead lead) {
        setName(lead.getName());
        setPhoneNumber(lead.getPhoneNumber());
        setEmail(lead.getEmail());
        setCompanyName(lead.getCompanyName());
    }

    // New Contact constructor
    public Contact(String name, String phoneNumber, String email, String companyName) {
        super(name, email, companyName, phoneNumber);
    }

    // New Contact constructor including an associated Account
    public Contact(String name, String phoneNumber, String email, String companyName, Account account) {
        super(name, email, companyName, phoneNumber);
        setAccount(account);
    }

}

