package com.ironhack.TheCleanCodersCRMv2homework3;

import com.ironhack.TheCleanCodersCRMv2homework3.dao.*;
import com.ironhack.TheCleanCodersCRMv2homework3.enums.Product;
import com.ironhack.TheCleanCodersCRMv2homework3.enums.Status;
import com.ironhack.TheCleanCodersCRMv2homework3.main.Main;
import com.ironhack.TheCleanCodersCRMv2homework3.menu.Creator;
import com.ironhack.TheCleanCodersCRMv2homework3.menu.Printer;
import com.ironhack.TheCleanCodersCRMv2homework3.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.awt.*;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
@EnableJpaRepositories
public class TheCleanCodersCrMv2Homework3Application implements CommandLineRunner {

	@Autowired
	Main main;

	public static void main(String[] args) {
		SpringApplication.run(TheCleanCodersCrMv2Homework3Application.class, args);
	}

	@Override
	public void run(String... args) throws InterruptedException {

		main.consoleRun();

/*		System.out.println("\nLeads by SalesRep");
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
		}*/


	}
}
