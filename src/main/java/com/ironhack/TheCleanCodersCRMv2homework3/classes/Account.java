package com.ironhack.TheCleanCodersCRMv2homework3.classes;



import com.ironhack.TheCleanCodersCRMv2homework3.enums.Industry;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String companyName;
    @Enumerated
    private Industry industry;
    private int employeeCount;
    private String city;
    private String country;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    @Column(name = "opportunity_list")
    private Set<Opportunity> opportunityList;

    // Constructor

    public Account(Contact contact, Opportunity opportunity, Industry industry, int employeeCount, String city, String country) {
//        super(allAccounts);
        // The CRM takes the Company name from Lead Object
        setCompanyName(contact.getCompanyName());
        // The CRM prompts user for the industry, number of employees, city, and country of Mike’s company.
        setIndustry(industry);
        setEmployeeCount(employeeCount);
        setCity(city);
        setCountry(country);
        // The CRM adds the Contact to the contactList of the Account and the new Opportunity to the opportunityList of the Account.
        addContactToList(contact);
        addOpportunityToList(opportunity);
    }

    public Account(int id, Contact contact, Opportunity opportunity, String companyName, Industry industry, int employeeCount, String city, String country) {
//        super(id, allAccounts);
        setCompanyName(companyName);
        setIndustry(industry);
        setEmployeeCount(employeeCount);
        setCity(city);
        setCountry(country);
        addContactToList(contact);
        addOpportunityToList(opportunity);
    }

    // Methods

    public void addContactToList(Contact contact) {
//        contactList.add(contact);
    }

    public void addOpportunityToList(Opportunity opportunity) {
//        opportunityList.add(opportunity);
    }

    // Getters and setters

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Industry getIndustry() {
        return this.industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public int getEmployeeCount() {
        return this.employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

//    public static List<Item> getAllAccounts() {
//        return allAccounts;
//    }

//    public List<Contact> getContactList() {
//        return this.contactList;
//    }

//    public List<Opportunity> getOpportunityList() {
//        return this.opportunityList;
//    }

//    @Override
//    public String toString() {
//        return "=== Account " + getId() + " ===" + '\n' +
//                "· industry : " + industry + '\n' +
//                "· employeeCount : " + employeeCount + '\n' +
//                "· city : " + city + '\n' +
//                "· country : " + country + '\n';
//    }
}
