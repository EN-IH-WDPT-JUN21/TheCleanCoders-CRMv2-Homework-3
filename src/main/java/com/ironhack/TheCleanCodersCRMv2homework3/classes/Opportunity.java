package com.ironhack.TheCleanCodersCRMv2homework3.classes;


import com.ironhack.TheCleanCodersCRMv2homework3.enums.Product;
import com.ironhack.TheCleanCodersCRMv2homework3.enums.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Opportunity extends Item{

    //Properties

    private Product product;
    private int quantity;
    private Contact decisionMaker;
    private Status status;
    public static List<Item> allOpportunities = new ArrayList<>();

    //Constructor

    public Opportunity(Product product, int quantity, Contact decisionMaker) {
        super(allOpportunities);
        setProduct(product);
        setQuantity(quantity);
        setDecisionMaker(decisionMaker);
        setStatus(Status.OPEN);
    }

    public Opportunity(int id, Product product, int quantity, Contact decisionMaker, Status status) {
        super(id, allOpportunities);
        setProduct(product);
        setQuantity(quantity);
        setDecisionMaker(decisionMaker);
        setStatus(status);
    }

    // Methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Opportunity that = (Opportunity) o;
        return quantity == that.quantity && product == that.product && Objects.equals(decisionMaker, that.decisionMaker) && status == that.status;
    }

    // Setters

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("The number of trucks must be greater than zero.");
        }
        this.quantity = quantity;
    }

    public void setDecisionMaker(Contact decisionMaker) {
        this.decisionMaker = decisionMaker;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    //Getters

    public Product getProduct() {
        return this.product;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public Contact getDecisionMaker() {
        return this.decisionMaker;
    }

    public Status getStatus() {
        return status;
    }

    public static List<Item> getAllOpportunities() {
        return allOpportunities;
    }

    public String getOpportunityInfo() {
        return "Product: " + this.product + ". Quantity: " + this.quantity + ". STATUS: " + this.status;
    }

    @Override
    public String toString() {
        return "=== Opportunity " + getId() + " ===" + '\n' +
                "· product : " + product + '\n' +
                "· quantity : " + quantity + '\n' +
                "· decision maker -> " + decisionMaker.toStringInOppClass() + '\n' +
                "· status : " + status + '\n';
    }
}
