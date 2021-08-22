package com.ironhack.TheCleanCodersCRMv2homework3.repository;

import com.ironhack.TheCleanCodersCRMv2homework3.dao.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepRepository extends JpaRepository<SalesRep, Long> {

    @Query(value = "select sales_rep_list.sales_rep_name , count(leads.sales_rep) from leads inner join sales_rep_list" +
            " on sales_rep_list.id = leads.sales_rep group by sales_rep", nativeQuery = true)
    String [][] countLeadsBySalesRep();



}
