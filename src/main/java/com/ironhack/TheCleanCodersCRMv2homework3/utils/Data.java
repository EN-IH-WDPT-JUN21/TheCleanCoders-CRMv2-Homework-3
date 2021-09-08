package com.ironhack.TheCleanCodersCRMv2homework3.utils;

import com.ironhack.TheCleanCodersCRMv2homework3.dao.*;
import com.ironhack.TheCleanCodersCRMv2homework3.enums.Industry;
import com.ironhack.TheCleanCodersCRMv2homework3.enums.Product;
import com.ironhack.TheCleanCodersCRMv2homework3.output.Style;
import com.ironhack.TheCleanCodersCRMv2homework3.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Data {

	final AccountRepository accountRepository;
	final SalesRepRepository salesRepRepository;
	final ContactRepository contactRepository;
	final LeadRepository leadRepository;
	final OpportunityRepository opportunityRepository;

	@Autowired
	public Data(AccountRepository accountRepository, SalesRepRepository salesRepRepository, ContactRepository contactRepository, LeadRepository leadRepository, OpportunityRepository opportunityRepository) {
		this.accountRepository = accountRepository;
		this.salesRepRepository = salesRepRepository;
		this.contactRepository = contactRepository;
		this.leadRepository = leadRepository;
		this.opportunityRepository = opportunityRepository;
	}

	List<SalesRep> salesRepList;
	List<Lead> leadList;
	List<Contact> contactList;
	List<Opportunity> opportunityList;
	List<Account> accountList;

	// When this method is called, it populates the mySQL database with some sample data
	public void populateRepos() {

	    accountList = accountRepository.saveAll(List.of(
	            new Account(Industry.MANUFACTURING, 100, "Basel", "Switzerland"),
                new Account(Industry.ECOMMERCE, 74, "Vigo", "Spain"),
                new Account(Industry.MEDICAL, 250, "Gdansk", "Poland"),
                new Account(Industry.OTHER, 12, "Faro", "Portugal"),
				new Account(Industry.PRODUCE, 52, "Weimar", "Germany")
        ));

        salesRepList = salesRepRepository.saveAll(List.of(
                new SalesRep("Jaime Jordan"),
                new SalesRep("Marian Garcia"),
                new SalesRep("Julia Dunst"),
                new SalesRep("Steve McDuck"),
                new SalesRep("Atul Gupta")
        ));

		contactList = contactRepository.saveAll(List.of(
				new Contact("Mara", "135791113", "mara@ironhack.es", "theCleanCoders Inc", accountList.get(0)),
				new Contact("Katarzyna", "2468101214", "catKat@ironhack.es", "theCleanCodersPoland", accountList.get(1)),
				new Contact("Natalia", "314152878", "natalia@ironhack.es", "theCleanCoders Ltd", accountList.get(2)),
				new Contact("Vitaliano", "2112345678", "vitaliano@ironhack.es", "theCleanCoders Lda", accountList.get(3)),
				new Contact("Joao Lopes", "351962458752", "meumail@meuservidor.pt", "cleanDevelopers", accountList.get(4))
		));

		leadList = leadRepository.saveAll(List.of(
				new Lead("Harry Potter", "0044123456", "harryp@hogwarts.wiz", "Gryffindor Ltd", salesRepList.get(1)),
				new Lead("Raistlin", "1234564879", "wizkid@majere.org", "TheCompanyOfDragons", salesRepList.get(4)),
				new Lead("Verissimo", "351969696585", "rambodeolhao@malucos.pt", "A Doca de Pesca", salesRepList.get(3)),
				new Lead("Arkansas San", "+011962458752", "arkansaswell@gmail.com", "Wellness Co.", salesRepList.get(0)),
				new Lead("Miguel Naves", "5643218563", "miguel@nevermind.com", "Os fortesnight", salesRepList.get(3))
		));

        opportunityList = opportunityRepository.saveAll(List.of(
                new Opportunity(Product.BOX, 8, contactList.get(0), salesRepList.get(1), accountList.get(1)),
                new Opportunity(Product.FLATBED, 7, contactList.get(1), salesRepList.get(1), accountList.get(0)),
                new Opportunity(Product.HYBRID, 1, contactList.get(2), salesRepList.get(0), accountList.get(1)),
                new Opportunity(Product.HYBRID, 9, contactList.get(3), salesRepList.get(0), accountList.get(2)),
                new Opportunity(Product.BOX, 7, contactList.get(4), salesRepList.get(2), accountList.get(3))
        ));

		System.out.println(Style.OCHER + "\nDATA SUCCESSFULLY IMPLEMENTED\n" + Style.DEFAULT);
    }

    public void cleanAllTables(){
		accountRepository.deleteAll();
		contactRepository.deleteAll();
		opportunityRepository.deleteAll();
		leadRepository.deleteAll();
		salesRepRepository.deleteAll();

		System.out.println(Style.OCHER + "\nDATA SUCCESSFULLY DELETED\n" + Style.DEFAULT);
	}
}

