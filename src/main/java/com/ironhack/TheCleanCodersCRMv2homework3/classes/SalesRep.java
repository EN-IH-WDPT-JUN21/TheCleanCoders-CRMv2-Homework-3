package com.ironhack.TheCleanCodersCRMv2homework3.classes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "sales_rep")
public class SalesRep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    //SalesRep should have OneToMany relations with Leads and Opportunities
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salesRep")
    private Set<Lead> salesRepLeads;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salesRep")
    private Set<Opportunity> salesRepOpportunities;

    public SalesRep(String name) {
        this.name = name;
    }
}
