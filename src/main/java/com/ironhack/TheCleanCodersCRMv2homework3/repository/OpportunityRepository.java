package com.ironhack.TheCleanCodersCRMv2homework3.repository;


import com.ironhack.TheCleanCodersCRMv2homework3.dao.Opportunity;
import com.ironhack.TheCleanCodersCRMv2homework3.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Opportunity o SET o.status = :status WHERE o.id = :id")
    void setOpportunityStatusById(@Param("id")Long id, @Param("status") Status status);

    @Query(value = "SELECT product, COUNT(product) FROM opportunities_table GROUP BY product", nativeQuery = true)
    String[][] reportOpportunityByProduct();

    @Query(value = "SELECT product, COUNT(product) FROM opportunities_table WHERE opportunity_status = 'CLOSED_WON' " +
            "GROUP BY product", nativeQuery = true)
    String[][] reportOpportunityClosedWonByProduct();

    @Query(value = "SELECT product, COUNT(product) FROM opportunities_table WHERE opportunity_status = 'CLOSED_LOST' " +
            "GROUP BY product", nativeQuery = true)
    String[][] reportOpportunityClosedLostByProduct();

    @Query(value = "SELECT product, COUNT(product) FROM opportunities_table WHERE opportunity_status = 'OPEN' " +
            "GROUP BY product", nativeQuery = true)
    String[][] reportOpportunityOpenByProduct();
    
}
