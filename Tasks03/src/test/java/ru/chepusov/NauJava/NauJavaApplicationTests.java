package ru.chepusov.NauJava;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.chepusov.NauJava.entities.Operation;
import ru.chepusov.NauJava.entities.User;
import ru.chepusov.NauJava.repository.*;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class NauJavaApplicationTests {
	CategoryRepository catRep;
	AccountRepository accRep;
	MarketplaceRepository MPRep;
	OperationRepository opRep;
	UserRepository uRep;

	OperationRepositoryCustom opRep2;

	@Autowired
	public NauJavaApplicationTests(CategoryRepository catRep, AccountRepository accRep, MarketplaceRepository MPRep, OperationRepository opRep, UserRepository uRep, OperationRepositoryCustom opRep2) {
		this.catRep = catRep;
		this.accRep = accRep;
		this.MPRep = MPRep;
		this.opRep = opRep;
		this.uRep = uRep;
		this.opRep2 = opRep2;
	}

	@Test
	void contextLoads() {
	}

	@Test
	void dataSearch() {
		System.out.println("-----------------");
		System.out.println("SEARCH TEST!!!!!");
		System.out.println("-----------------");
		List<User> result = uRep.findByAgeLessThan(100);
		for (User x : result
		) {
			System.out.println(x);
		}
	}

	@Test
	void userSearch() {
		System.out.println("-----------------");
		System.out.println("USER SEARCH TEST!!!!!");
		System.out.println("-----------------");
		List<User> result = uRep.findByFirstNameOrLastName("James","Karenina");
		for (User x : result
		) {
			System.out.println(x);
		}
	}

	@Test
	void accoutByIdSearch() {

	}

	@Test
	void operationSearch() {
		System.out.println();
		System.out.println("________!!!!!OPERATIONS!!!!!__________");
		List<Operation> list = opRep.findByCategoryName("food");
		for (Operation x : list
			 ) {
			System.out.println("Found: " + x);
		}
		System.out.println("____________________________");
		System.out.println();

		list = opRep2.findOperationsByCategoryName("Food");
		for (Operation x : list
		) {
			System.out.println("Found2: " + x);
		}
		System.out.println();
	}
}