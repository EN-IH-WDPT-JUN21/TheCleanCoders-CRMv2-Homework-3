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

    @Enumerated(value = EnumType.STRING)
    private Industry industry;

    @Column(name = "employees")
    private int employeeCount;

    private String city;

    private String country;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @Column(name = "opportunity_list")
    private Set<Opportunity> opportunityList;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @Column(name = "contact_list")
    private Set<Contact> contactList;

    // Constructor

    public Account(Industry industry, int employeeCount, String city, String country) {
        setIndustry(industry);
        setEmployeeCount(employeeCount);
        setCity(city);
        setCountry(country);
    }

    @Override
    public String toString() {
        return "=== Account " + getId() + " ===" + '\n' +
                "· industry : " + industry + '\n' +
                "· number of employees : " + employeeCount + '\n' +
                "· city : " + city + '\n' +
                "· country : " + country + '\n';
    }
}
