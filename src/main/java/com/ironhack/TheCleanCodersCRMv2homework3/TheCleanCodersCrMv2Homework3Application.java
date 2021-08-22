package com.ironhack.TheCleanCodersCRMv2homework3;

import com.ironhack.TheCleanCodersCRMv2homework3.dao.*;
import com.ironhack.TheCleanCodersCRMv2homework3.enums.Product;
import com.ironhack.TheCleanCodersCRMv2homework3.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

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

		salesRepList = salesRepRepository.saveAll(List.of(
				new SalesRep("Rick Sanchez"),
				new SalesRep("Homer Simpson"),
				new SalesRep("Peter Griffin"),
				new SalesRep("Stan Smith"),
				new SalesRep("Mark Naird")
		));
		SalesRep salesRep1 = new SalesRep("Andre");
		salesRepRepository.save(salesRep1);

		leadList = leadRepository.saveAll(List.of(
				new Lead("Harry Potter", "0044123456", "harryp@hogwarts.wiz",
						"Gryffindor Ltd", salesRep1),
				new Lead("Raistlin", "1234564879", "wizkid@majere.org",
						"TheCompanyOfDragons", salesRepList.get(4)),
				new Lead("Verissimo", "351969696585", "rambodeolhao@malucos.pt",
						"A Doca de Pesca", salesRepList.get(3)),
				new Lead("Joao Lopes", "351962458752", "meumail@meuservidor.pt",
						"cleanDevelopers", salesRepList.get(0)),
				new Lead("Miguel", "5643218563", "miguel@nevermind.com",
						"Os fortesnight", salesRepList.get(3))
		));

		contactList = contactRepository.saveAll(List.of(
				new Contact("Mara", "135791113", "mara@ironhack.es",
						"theCleanCoders Inc"),
				new Contact("Katarzyna", "2468101214", "catKat@ironhack.pl",
						"theCleanCodersPoland"),
				new Contact("Natalya", "314152878", "natalya@ironhack.pl",
						"theCleanCoders Ltd"),
				new Contact("Vitaliano", "2112345678", "vitaliano@ironhack.pt",
						"theCleanCoders Lda")
		));

		opportunityList = opportunityRepository.saveAll(List.of(
				new Opportunity(Product.BOX, 8, contactList.get(0), salesRep1),
				new Opportunity(Product.FLATBED, 7, contactList.get(0), salesRepList.get(1)),
				new Opportunity(Product.HYBRID, 1, contactList.get(1), salesRepList.get(0)),
				new Opportunity(Product.HYBRID, 9, contactList.get(2), salesRepList.get(0)),
				new Opportunity(Product.BOX, 7, contactList.get(1), salesRepList.get(2))
		));


		for (String[] a : salesRepRepository.countLeadsBySalesRep()) {
			System.out.println(Arrays.toString(a));
		}



	}
}
