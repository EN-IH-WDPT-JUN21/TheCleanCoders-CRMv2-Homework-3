package com.ironhack.TheCleanCodersCRMv2homework3.dao;

import com.ironhack.TheCleanCodersCRMv2homework3.enums.Industry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Table(name = "accounts_table")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Enumerated(value = EnumType.STRING)
    private Industry industry;

    @Column(name = "employees")
    private int employeeCount;

    private String city;

    private String country;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    @Column(name = "opportunity_list")
    private Set<Opportunity> opportunityList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    @Column(name = "contact_list")
    private Set<Contact> contactList;

    // Constructor

    public Account(Contact contact, Opportunity opportunity, Industry industry, int employeeCount, String city, String country) {
        // The CRM takes the Company name from Lead Object
        setCompanyName(contact.getCompanyName());
        // The CRM prompts user for the industry, number of employees, city, and country of the company.
        setIndustry(industry);
        setEmployeeCount(employeeCount);
        setCity(city);
        setCountry(country);
        // The CRM adds the Contact to the contactList of the Account and the new Opportunity to the opportunityList of the Account.
        addContactToList(contact);
        addOpportunityToList(opportunity);
    }

    public Account(Contact contact, Opportunity opportunity, String companyName, Industry industry, int employeeCount, String city, String country) {
        setCompanyName(companyName);
        setIndustry(industry);
        setEmployeeCount(employeeCount);
        setCity(city);
        setCountry(country);
        addContactToList(contact);
        addOpportunityToList(opportunity);
    }

    // Constructor for a new Account
    public Account(String companyName, Industry industry, int employeeCount, String city, String country) {
        setCompanyName(companyName);
        setIndustry(industry);
        setEmployeeCount(employeeCount);
        setCity(city);
        setCountry(country);
    }

    // Methods

    public void addContactToList(Contact contact) {
//        contactList.add(contact);
    }

    public void addOpportunityToList(Opportunity opportunity) {
//        opportunityList.add(opportunity);
    }


    @Override
    public String toString() {
        return "=== Account " + getId() + " ===" + '\n' +
                "· industry : " + industry + '\n' +
                "· employeeCount : " + employeeCount + '\n' +
                "· city : " + city + '\n' +
                "· country : " + country + '\n';
    }
}
