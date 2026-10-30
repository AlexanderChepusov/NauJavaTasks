package ru.chepusov.NauJava;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.chepusov.NauJava.criteriaapi.OperationRepositoryCriteriaImpl;
import ru.chepusov.NauJava.criteriaapi.UserRepositoryCriteriaEmpl;
import ru.chepusov.NauJava.entities.Category;
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

	UserRepositoryCriteriaEmpl uRepCr;

	@PersistenceContext
	private EntityManager entityManager;

	OperationRepositoryCustom opRep2;
	OperationRepositoryCriteriaImpl opRepCr;

	@Autowired
	public NauJavaApplicationTests(CategoryRepository catRep, AccountRepository accRep, MarketplaceRepository MPRep, OperationRepository opRep, UserRepository uRep, OperationRepositoryCustom opRep2, UserRepositoryCriteriaEmpl uRepCr, OperationRepositoryCriteriaImpl opRepCr) {
		this.catRep = catRep;
		this.accRep = accRep;
		this.MPRep = MPRep;
		this.opRep = opRep;
		this.uRep = uRep;
		this.opRep2 = opRep2;
		this.uRepCr = uRepCr;
		this.opRepCr = opRepCr;
	}

//	@Test
//	void contextLoads() {
//	}


	@Test
	void findYoungUsersTest() {	//Задание 5 - Query  Lookup  Strategies
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
	void userByNameSearchTest() {	//Задание 5 - Query  Lookup  Strategies
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
	void operationByCategorySearchTest() {
		System.out.println();
		System.out.println("________!!!!!OPERATIONS!!!!!__________");
		List<Operation> list = opRep.findByCategoryName("Food");
		System.out.println("Найдено " + list.size());
		for (Operation x : list
			 ) {
			System.out.println("Found: " + x);
		}
		System.out.println("____________________________");
		System.out.println();

		//CRITERIA
		System.out.println(">---------CRITERIA SEARCH!!!-------<");
		list = opRepCr.findByCategory("Food"); // .findOperationsByCategoryName("Food");
		for (Operation x : list
		) {
			System.out.println("Found2_cr: " + x);

			System.out.println("Operation ID: " + x.getId());
			System.out.println("Category Name (из БД): " + x.getCategory());

		}
		System.out.println();


		list = opRep.findByCat("Food");
		System.out.println("<<<<<<<<<<<<>>>>>>>>>>>>>>");
		for (Operation x : list
		) {
			System.out.println("Found3: " + x);
		}
		System.out.println("<<<<<<<<<<<<----------->>>>>>>>>>>>>>");
		System.out.println();
	}

	@Test
	public void findByCatQueryTest() {	//Задание 5 - через @Query
		String categoryName = "gasoline";

		List<Operation> list = opRep.findByCat(categoryName);

		System.out.println("<<<<<<<<<<<<>>>>>>>>>>>>>>");
		TypedQuery<Operation> query = entityManager.createQuery(
				"SELECT o FROM Operation o WHERE o.category.name = :name", Operation.class);
		query.setParameter("name", categoryName);

		List<Operation> results = query.getResultList();

		System.out.println("Query done: EntMan.size = " + results.size() + " & MyTest.size = " + list.size());

		Assertions.assertEquals(results.size(), list.size());

		System.out.println("<<<<<<<<<<<<----------->>>>>>>>>>>>>>");
		System.out.println();
	}

	@Test
	public void addNewCatTest() {	//Текст функций CRUD - создания и удаления категории
		System.out.println("<--- Insert new category disCo --->");

		if (catRep.findByName("disco") != null) {
			System.out.println("This cat was found");
			catRep.deleteByName("disco");
			System.out.println("This cat was DELETED");
		}

		int n = (int)catRep.count();
		catRep.addNewCategory("disCo", 10, "p");
		int m = (int)catRep.count();
		System.out.println("----------new CAT created!!!------------");
		System.out.println(n + " ---> " + m);
		Assertions.assertTrue((m - n) == 1);

		catRep.deleteByNameIgnoreCase("disco");
		System.out.println("----------new CAT deleted!!!------------");
	}


	@Test
	public void criteriaTest() { //тест метода на CriteriaAPI
		System.out.println("----SERACHING LOGIN------");
		String login = System.getProperty("login", "007");
		User u1 = uRep.findByLogin(login);
		long i1 = u1.getId();

		User u2 = uRepCr.findByName(login);
		long i2 = u2.getId();

		Assertions.assertEquals(i1, i2);
		System.out.println("Users are " + ((i1 == i2) ? "" : "not ") + "identical");
	}

	@Test
	public void criteriaTest2() {
		System.out.println("----SERACHING BY FULL NAME------");
		String fName = System.getProperty("fName", "Ivan");
		String lName = System.getProperty("lName", "Fury");
		System.out.println(fName + " & " + lName);

		List<User> list = uRepCr.findByFirstAndLastNames(fName, lName);

		for (User x : list
			 ) {
			System.out.println(x);
		}
	}

}