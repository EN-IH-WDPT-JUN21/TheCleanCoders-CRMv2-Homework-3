package com.ironhack.TheCleanCodersCRMv2homework3.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sales_rep_list")
public class SalesRep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "sales_rep_name")
    private String name;
    //SalesRep should have OneToMany relations with Leads and Opportunities
    @OneToMany(mappedBy = "salesRep")
    private Set<Lead> salesRepLeads;
    @OneToMany(mappedBy = "salesRep")
    private Set<Opportunity> salesRepOpportunities;

    public SalesRep(String name) {
        this.name = name;
    }
}
