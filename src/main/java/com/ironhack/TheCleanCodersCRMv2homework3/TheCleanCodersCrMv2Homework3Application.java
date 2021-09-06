package com.ironhack.TheCleanCodersCRMv2homework3;

import com.ironhack.TheCleanCodersCRMv2homework3.dao.*;
import com.ironhack.TheCleanCodersCRMv2homework3.enums.Product;
import com.ironhack.TheCleanCodersCRMv2homework3.enums.Status;
import com.ironhack.TheCleanCodersCRMv2homework3.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class TheCleanCodersCrMv2Homework3Application implements CommandLineRunner {
	@Autowired
	private SalesRepRepository salesRepRepository;
	@Autowired
	private LeadRepository leadRepository;
	@Autowired
	private ContactRepository contactRepository;
	@Autowired
	private OpportunityRepository opportunityRepository;
	@Autowired
	private AccountRepository accountRepository;

	private List<SalesRep> salesRepList;
	private List<Lead> leadList;
	private List<Contact> contactList;
	private List<Opportunity> opportunityList;
	private List<Account> accountList;


	public static void main(String[] args) {
		SpringApplication.run(TheCleanCodersCrMv2Homework3Application.class, args);

	}

	@Override
	public void run(String... args) {


		System.out.println("\nLeads by SalesRep");
		for (String[] a : salesRepRepository.reportLeadsBySalesRep()) {
			System.out.println(Arrays.toString(a));
		}

		System.out.println("\nOpportunities by SalesRep");
		for (String[] a : salesRepRepository.reportOpportunitiesBySalesRep()) {
			System.out.println(Arrays.toString(a));
		}

		System.out.println("\nClosed-Lost by SalesRep");
		for (String[] a : salesRepRepository.reportClosedLostOpportunitiesBySalesRep()) {
			System.out.println(Arrays.toString(a));
		}

		System.out.println("\nClosed-Won by SalesRep");
		for (String[] a : salesRepRepository.reportClosedWonOpportunitiesBySalesRep()) {
			System.out.println(Arrays.toString(a));
		}

		System.out.println("\nOpen by SalesRep");
		for (String[] a : salesRepRepository.reportOpenOpportunitiesBySalesRep()) {
			System.out.println(Arrays.toString(a));
		}

		System.out.println("\nOpportunity by product");
		for (String[] a : opportunityRepository.reportOpportunityByProduct()) {
			System.out.println(Arrays.toString(a));
		}

		System.out.println("\nOpportunities By Country");
		for (String[] a : opportunityRepository.reportOpportunitiesByCountry()) {
			System.out.println(Arrays.toString(a));
		}

		System.out.println("\nCLOSED_WON Opportunities By Country");
		for (String[] a : opportunityRepository.reportClosedWonOpportunitiesByCountry()) {
			System.out.println(Arrays.toString(a));
		}

		System.out.println("\nMean Employee Count:");
		System.out.println(accountRepository.meanEmployeeCount());

		System.out.println("\nMedian Employee Count:");
		System.out.println(accountRepository.medianEmployeeCount());

		System.out.println("\nMax Employee Count:");
		System.out.println(accountRepository.maxEmployeeCount());

		System.out.println("\nMin Employee Count:");
		System.out.println(accountRepository.minEmployeeCount());

		System.out.println("\nMean Quantity:");
		System.out.println(opportunityRepository.meanQuantity());

		System.out.println("\nMedian Quantity:");
		System.out.println(opportunityRepository.medianQuantity());

		System.out.println("\nMax Quantity:");
		System.out.println(opportunityRepository.maxQuantity());

		System.out.println("\nMin Quantity:");
		System.out.println(opportunityRepository.minQuantity());

		System.out.println("\nMean Opportunities Per Account:");
		System.out.println(opportunityRepository.meanOppsPerAccount());

		System.out.println("\nMedian Opportunities Per Account:");
		System.out.println(opportunityRepository.medianOppsPerAccount());

		System.out.println("\nMax Opportunities Per Account:");
		System.out.println(opportunityRepository.maxOppsPerAccount());

		System.out.println("\nMin Opportunities Per Account:");
		System.out.println(opportunityRepository.minOppsPerAccount());




	}
}
