package com.ironhack.TheCleanCodersCRMv2homework3.dao;


import com.ironhack.TheCleanCodersCRMv2homework3.enums.Product;
import com.ironhack.TheCleanCodersCRMv2homework3.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "opportunities_table")
public class Opportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Product product;
    private int quantity;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "decision_maker")
    private Contact decisionMaker;
    @Enumerated(EnumType.STRING)
    @Column(name = "opportunity_status") //"status" is an SQL keyword
    private Status status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sales_rep")
    private SalesRep salesRep;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account")
    private Account account;


    public Opportunity(Product product, int quantity, Contact decisionMaker, SalesRep salesRep) {
        setProduct(product);
        setQuantity(quantity);
        setDecisionMaker(decisionMaker);
        setStatus(Status.OPEN);
        setSalesRep(salesRep);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Opportunity that = (Opportunity) o;
        return quantity == that.quantity
                && product == that.product
                && Objects.equals(decisionMaker, that.decisionMaker)
                && status == that.status;
    }

    public String getOpportunityInfo() {
        return "Product: " + this.product + ". Quantity: " + this.quantity + ". STATUS: " + this.status;
    }

}
