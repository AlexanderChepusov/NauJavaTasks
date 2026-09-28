package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        List<Employee> list = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            int age = 18 + (int)(Math.random() * (70 - 18));
            String dep = "Department_" + (1 + (int)(Math.random() * (4)));
            double sal = 30000 + Math.random() * (200000);
            Employee empl = new Employee("Employee_" + i, age, dep, sal);
            list.add(empl);
        }
        System.out.println("--ORIGINAL LIST--");
        System.out.println(list);
        System.out.println();

        //t1 - 30-older
        System.out.println("--LIST with COLLECTOR--");
        List<Employee> task1 = list.stream().filter(n -> n.getAge() > 30).collect(Collectors.toList());
        System.out.println("30-older: \n\r" + task1);
        System.out.println();

        //t2 - average
        OptionalDouble average = list.stream().mapToDouble(n -> n.getSalary()).average();  //peek(n -> n.getSalary()).collect(Collectors.toList()));
        System.out.println("--Average salary = " + String.valueOf(average) + "--");
        System.out.println();

        //t3
        //List<Employee> task3 =
        System.out.println("--Sorted by salary: --") ;
        list.stream().sorted(Comparator.comparingDouble(Employee::getSalary)).forEach(System.out::print);  //filter(n -> n.getAge() > 30).collect(Collectors.toList());
        System.out.println();

        //t4
        System.out.println("--NAME - DEARTMENT list:--");
        list.stream().forEach(n -> System.out.println(n.getFullName() + " - " + n.getDepartment()));
        System.out.println();

        //t5
        System.out.print("--Is there anyone with the salsry more than 100000? - ");
        System.out.println(list.stream().anyMatch(n -> n.getSalary() > 100000) ? "YES!--" : "NO...--");




//        System.out.println("--FOREACH--");
//        list.stream().filter(n -> n.getAge() > 30).forEach(System.out::println);
        //System.out.println("30-older: " + task1);


    }
}
