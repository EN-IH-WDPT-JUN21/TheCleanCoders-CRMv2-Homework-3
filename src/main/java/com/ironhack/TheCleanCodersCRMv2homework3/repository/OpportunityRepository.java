package com.ironhack.TheCleanCodersCRMv2homework3.repository;


import com.ironhack.TheCleanCodersCRMv2homework3.dao.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {
}
