package com.ironhack.TheCleanCodersCRMv2homework3.repository;

import com.ironhack.TheCleanCodersCRMv2homework3.dao.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {


    @Query(value = "SELECT AVG(employees) FROM accounts_table", nativeQuery = true)
    int meanEmployeeCount();

    @Query(value = "SELECT AVG(dd.employees) AS median_val " +
            "FROM (SELECT accounts_table.employees, @rownum\\:=@rownum+1 as 'row_number', @total_rows\\:=@rownum " +
            "FROM accounts_table, (SELECT @rownum\\:=0) r " +
            "ORDER BY accounts_table.employees) as dd " +
            "WHERE dd.row_number IN ( FLOOR((@total_rows+1)/2), FLOOR((@total_rows+2)/2) )", nativeQuery = true)
    int medianEmployeeCount();

    @Query(value = "SELECT MAX(employees) FROM accounts_table", nativeQuery = true)
    int maxEmployeeCount();

    @Query(value = "SELECT MIN(employees) FROM accounts_table", nativeQuery = true)
    int minEmployeeCount();


}
