package com.ironhack.TheCleanCodersCRMv2homework3.dao;


import com.ironhack.TheCleanCodersCRMv2homework3.output.Style;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String email;
    @Column(name = "company_name")
    private String companyName;
    @OneToOne(mappedBy = "decisionMaker")
    private Opportunity opportunity;
    @ManyToOne
    @JoinColumn(name = "account")
    private Account account;

    public Contact(Lead lead) {
        setName(lead.getName());
        setPhoneNumber(lead.getPhoneNumber());
        setEmail(lead.getEmail());
        setCompanyName(lead.getCompanyName());
    }

    public Contact(String name, String phoneNumber, String email, String companyName) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
    }

// Setters and getters


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

