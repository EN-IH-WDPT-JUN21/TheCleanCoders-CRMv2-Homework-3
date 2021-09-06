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

    @Query(value = "SELECT accounts_table.country, COUNT(accounts_table.country) " +
            "FROM accounts_table JOIN opportunities_table ON accounts_table.id=opportunities_table.account " +
            "GROUP BY accounts_table.country", nativeQuery = true)
    String[][] reportOpportunitiesByCountry();

    @Query(value = "SELECT accounts_table.country, COUNT(accounts_table.country) " +
            "FROM accounts_table JOIN opportunities_table ON accounts_table.id=opportunities_table.account " +
            "WHERE opportunities_table.opportunity_status='CLOSED_WON' " +
            "GROUP BY accounts_table.country", nativeQuery = true)
    String[][] reportClosedWonOpportunitiesByCountry();

    @Query(value = "SELECT accounts_table.country, COUNT(accounts_table.country) " +
            "FROM accounts_table JOIN opportunities_table ON accounts_table.id=opportunities_table.account " +
            "WHERE opportunities_table.opportunity_status='CLOSED_LOST' " +
            "GROUP BY accounts_table.country", nativeQuery = true)
    String[][] reportClosedLostOpportunitiesByCountry();

    @Query(value = "SELECT accounts_table.country, COUNT(accounts_table.country) " +
            "FROM accounts_table JOIN opportunities_table ON accounts_table.id=opportunities_table.account " +
            "WHERE opportunities_table.opportunity_status='OPEN' " +
            "GROUP BY accounts_table.country", nativeQuery = true)
    String[][] reportOpenOpportunitiesByCountry();

    @Query(value = "SELECT accounts_table.city, COUNT(accounts_table.city) " +
            "FROM accounts_table JOIN opportunities_table ON accounts_table.id=opportunities_table.account " +
            "GROUP BY accounts_table.city", nativeQuery = true)
    String[][] reportOpportunitiesByCity();

    @Query(value = "SELECT accounts_table.city, COUNT(accounts_table.city) " +
            "FROM accounts_table JOIN opportunities_table ON accounts_table.id=opportunities_table.account " +
            "WHERE opportunities_table.opportunity_status='CLOSED_WON' " +
            "GROUP BY accounts_table.city", nativeQuery = true)
    String[][] reportClosedWonOpportunitiesByCity();

    @Query(value = "SELECT accounts_table.city, COUNT(accounts_table.city) " +
            "FROM accounts_table JOIN opportunities_table ON accounts_table.id=opportunities_table.account " +
            "WHERE opportunities_table.opportunity_status='CLOSED_LOST' " +
            "GROUP BY accounts_table.city", nativeQuery = true)
    String[][] reportClosedLostOpportunitiesByCity();

    @Query(value = "SELECT accounts_table.city, COUNT(accounts_table.city) " +
            "FROM accounts_table JOIN opportunities_table ON accounts_table.id=opportunities_table.account " +
            "WHERE opportunities_table.opportunity_status='OPEN' " +
            "GROUP BY accounts_table.city", nativeQuery = true)
    String[][] reportOpenOpportunitiesByCity();

    @Query(value = "SELECT accounts_table.industry, COUNT(accounts_table.industry) " +
            "FROM accounts_table JOIN opportunities_table ON accounts_table.id=opportunities_table.account " +
            "GROUP BY accounts_table.industry", nativeQuery = true)
    String[][] reportOpportunitiesByIndustry();

    @Query(value = "SELECT accounts_table.industry, COUNT(accounts_table.industry) " +
            "FROM accounts_table JOIN opportunities_table ON accounts_table.id=opportunities_table.account " +
            "WHERE opportunities_table.opportunity_status='CLOSED_WON' " +
            "GROUP BY accounts_table.industry", nativeQuery = true)
    String[][] reportClosedWonOpportunitiesByIndustry();

    @Query(value = "SELECT accounts_table.industry, COUNT(accounts_table.industry) " +
            "FROM accounts_table JOIN opportunities_table ON accounts_table.id=opportunities_table.account " +
            "WHERE opportunities_table.opportunity_status='CLOSED_LOST' " +
            "GROUP BY accounts_table.industry", nativeQuery = true)
    String[][] reportClosedLostOpportunitiesByIndustry();

    @Query(value = "SELECT accounts_table.industry, COUNT(accounts_table.industry) " +
            "FROM accounts_table JOIN opportunities_table ON accounts_table.id=opportunities_table.account " +
            "WHERE opportunities_table.opportunity_status='OPEN' " +
            "GROUP BY accounts_table.industry", nativeQuery = true)
    String[][] reportOpenOpportunitiesByIndustry();

    @Query(value = "SELECT AVG(quantity) FROM opportunities_table", nativeQuery = true)
    double meanQuantity();

    @Query(value = "SELECT AVG(dd.quantity) AS median_val " +
            "FROM (SELECT opportunities_table.quantity, @rownum\\:=@rownum+1 as 'row_number', @total_rows\\:=@rownum " +
            "FROM opportunities_table, (SELECT @rownum\\:=0) r " +
            "ORDER BY opportunities_table.quantity) as dd " +
            "WHERE dd.row_number IN ( FLOOR((@total_rows+1)/2), FLOOR((@total_rows+2)/2) )", nativeQuery = true)
    double medianQuantity();

    @Query(value = "SELECT MAX(quantity) FROM opportunities_table", nativeQuery = true)
    int maxQuantity();

    @Query(value = "SELECT MIN(quantity) FROM opportunities_table", nativeQuery = true)
    int minQuantity();

    @Query(value = "SELECT AVG(a.opps_per_account) " +
            "FROM (SELECT account, COUNT(account) AS opps_per_account " +
            "FROM opportunities_table GROUP BY account) AS a", nativeQuery = true)
    double meanOppsPerAccount();

    @Query(value = "SELECT AVG(dd.opps_per_account) AS median_val " +
            "FROM (SELECT a.opps_per_account, @rownum\\:=@rownum+1 as 'row_number', @total_rows\\:=@rownum " +
            "FROM (SELECT account, COUNT(account) AS opps_per_account " +
            "FROM opportunities_table GROUP BY account) AS a, (SELECT @rownum\\:=0) r " +
            "ORDER BY a.opps_per_account) as dd " +
            "WHERE dd.row_number IN ( FLOOR((@total_rows+1)/2), FLOOR((@total_rows+2)/2) )", nativeQuery = true)
    double medianOppsPerAccount();

    @Query(value = "SELECT MAX(a.opps_per_account) FROM (SELECT account, COUNT(account) AS opps_per_account " +
            "FROM opportunities_table GROUP BY account) AS a", nativeQuery = true)
    int maxOppsPerAccount();

    @Query(value = "SELECT MIN(a.opps_per_account) FROM (SELECT account, COUNT(account) AS opps_per_account " +
            "FROM opportunities_table GROUP BY account) AS a", nativeQuery = true)
    int minOppsPerAccount();
    
}
