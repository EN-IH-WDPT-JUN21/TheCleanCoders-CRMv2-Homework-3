package com.ironhack.TheCleanCodersCRMv2homework3.dao;

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

@PrimaryKeyJoinColumn(name = "id")
@Table(name = "contacts")
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
        super(name, phoneNumber, email, companyName);
    }

}

