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

@Table(name = "leads_table")
public class Lead extends Item{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sales_rep")
    private SalesRep salesRep;

    // Constructor

    public Lead(String name, String phoneNumber, String email, String companyName, SalesRep salesRep) {
        super(name, email, companyName, phoneNumber);
        setSalesRep(salesRep);
    }

    @Override
    public String toString() {
        return "=== Lead " + getId() + " ===" + '\n' +
                "· name : " + getName() + '\n' +
                "· phone number : " + getPhoneNumber() + '\n' +
                "· email : " + getEmail() + '\n' +
                "· company name : " + getCompanyName() + '\n' +
                "· associate sales rep : " + salesRep.getName() + '\n';
    }

}
