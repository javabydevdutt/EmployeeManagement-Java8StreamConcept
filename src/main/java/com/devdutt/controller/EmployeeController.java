package com.devdutt.controller;

import com.devdutt.dao.EmployeeDAO;
import com.devdutt.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeController {

    public static void main(String[] args) {

        //Query 1.1 : How many male and female employees are there in the organization
        Map<String, Long> noOfMaleAndFemaleEmployees = EmployeeDAO.empDataBase().stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println(noOfMaleAndFemaleEmployees);

        System.out.println("===============================================");
        //Query 1.2 : Print the name of all departments in the organization
        EmployeeDAO.empDataBase().stream().map(Employee::getDepartment).distinct().forEach(System.out::println);

        System.out.println("===============================================");
        //Query 1.3 : What is the average age of male and female employees?
        Map<String, Double> avgAgeOfMaleAndFemaleEmployees = EmployeeDAO.empDataBase().stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
        System.out.println(avgAgeOfMaleAndFemaleEmployees);

        System.out.println("===============================================");
        //Query 1.4 : Get the details of highest paid employee in the organization
        Optional<Employee> highestPaidEmployee = EmployeeDAO.empDataBase().stream().collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)));
        System.out.println("Details Of Highest Paid Employee : ");

        System.out.println("==================================");
        System.out.println("ID : " + highestPaidEmployee.get().getId());

        System.out.println("Name : " + highestPaidEmployee.get().getName());

        System.out.println("Age : " + highestPaidEmployee.get().getAge());

        System.out.println("Gender : " + highestPaidEmployee.get().getGender());

        System.out.println("Department : " + highestPaidEmployee.get().getDepartment());

        System.out.println("Year Of Joining : " + highestPaidEmployee.get().getYearOfJoining());

        System.out.println("Salary : " + highestPaidEmployee.get().getSalary());

        System.out.println("==================================");
        //Query 1.5 : Get the names of all employees who have joined after 2015
        EmployeeDAO.empDataBase().stream().filter(e -> e.getYearOfJoining() > 2015).map(Employee::getName).forEach(System.out::println);

        System.out.println("==================================");
        //Query 1.6 : Count the number of employees in each department
        Map<String, Long> employeeCountByDepartment = EmployeeDAO.empDataBase().stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        Set<Map.Entry<String, Long>> entrySet = employeeCountByDepartment.entrySet();
        for (Map.Entry<String, Long> entry : entrySet) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        System.out.println("==================================");
        //Query 1.7 : What is the average salary of each department
        Map<String, Double> avgSalaryOfDepartments = EmployeeDAO.empDataBase().stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
        Set<Map.Entry<String, Double>> entrySet1 = avgSalaryOfDepartments.entrySet();

        for (Map.Entry<String, Double> entry : entrySet1) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        System.out.println("==================================");
        //Query 1.8 : Get the details of youngest male employee in the product development department

        Optional<Employee> youngestMaleEmployeeInProductDevelopmentWrapper = EmployeeDAO.empDataBase().stream().filter(e -> e.getGender().equalsIgnoreCase("Male") && e.getDepartment().equalsIgnoreCase("Product Development")).min(Comparator.comparingInt(Employee::getAge));
        Employee youngestMaleEmployeeInProductDevelopment = youngestMaleEmployeeInProductDevelopmentWrapper.get();

        System.out.println("Details Of Youngest Male Employee In Product Development");

        System.out.println("----------------------------------------------");

        System.out.println("ID : " + youngestMaleEmployeeInProductDevelopment.getId());

        System.out.println("Name : " + youngestMaleEmployeeInProductDevelopment.getName());

        System.out.println("Age : " + youngestMaleEmployeeInProductDevelopment.getAge());

        System.out.println("Year Of Joining : " + youngestMaleEmployeeInProductDevelopment.getYearOfJoining());

        System.out.println("Salary : " + youngestMaleEmployeeInProductDevelopment.getSalary());

        System.out.println("==================================");
        //Query 1.9 : Who has the most working experience in the organization

        Optional<Employee> seniorMostEmployeeWrapper = EmployeeDAO.empDataBase().stream().sorted(Comparator.comparingInt(Employee::getYearOfJoining)).findFirst();
        Employee seniorMostEmployee = seniorMostEmployeeWrapper.get();

        System.out.println("Senior Most Employee Details :");

        System.out.println("----------------------------");

        System.out.println("ID : " + seniorMostEmployee.getId());

        System.out.println("Name : " + seniorMostEmployee.getName());

        System.out.println("Age : " + seniorMostEmployee.getAge());

        System.out.println("Gender : " + seniorMostEmployee.getGender());

        System.out.println("Age : " + seniorMostEmployee.getDepartment());

        System.out.println("Year Of Joinging : " + seniorMostEmployee.getYearOfJoining());

        System.out.println("Salary : " + seniorMostEmployee.getSalary());

        System.out.println("==================================");
        //Query 1.10 : How many male and female employees are there in the sales and marketing team
        Map<String, Long> countMaleFemaleEmployeesInSalesMarketing = EmployeeDAO.empDataBase().stream().filter(e -> e.getDepartment().equalsIgnoreCase("Sales And Marketing")).collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println(countMaleFemaleEmployeesInSalesMarketing);

        System.out.println("==================================");
        //Query 1.11 : What is the average salary of male and female employees
        Map<String, Double> avgSalaryOfMaleAndFemaleEmployees = EmployeeDAO.empDataBase().stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(avgSalaryOfMaleAndFemaleEmployees);

        System.out.println("==================================");
        //Query 1.12 : List down the names of all employees in each department
        Map<String, List<Employee>> employeeListByDepartment = EmployeeDAO.empDataBase().stream().collect(Collectors.groupingBy(Employee::getDepartment));
        Set<Map.Entry<String, List<Employee>>> entrySet2 = employeeListByDepartment.entrySet();

        for (Map.Entry<String, List<Employee>> entry : entrySet2) {
            System.out.println("--------------------------------------");

            System.out.println("Employees In " + entry.getKey() + " : ");

            System.out.println("--------------------------------------");

            List<Employee> list = entry.getValue();

            for (Employee e : list) {
                System.out.println(e.getName());
            }
        }

        System.out.println("==================================");
        //Query 1.13 : What is the average salary and total salary of the whole organization
        DoubleSummaryStatistics employeeSalaryStatistics = EmployeeDAO.empDataBase().stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println("Average Salary:- " + employeeSalaryStatistics.getAverage());
        System.out.println("Total Salary:- " + employeeSalaryStatistics.getSum());

        System.out.println("==================================");
        //Query 1.14 : Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years
        Map<Boolean, List<Employee>> partitionEmployeesByAge = EmployeeDAO.empDataBase().stream().collect(Collectors.partitioningBy(e -> e.getAge() > 25));
        Set<Map.Entry<Boolean, List<Employee>>> entrySet3 = partitionEmployeesByAge.entrySet();
        for (Map.Entry<Boolean, List<Employee>> entry : entrySet3) {
            System.out.println("----------------------------");
            if (entry.getKey()) {
                System.out.println("Employees older than 25 years :");
            } else {
                System.out.println("Employees younger than or equal to 25 years :");
            }

            System.out.println("----------------------------");
            List<Employee> list = entry.getValue();
            for (Employee e : list) {
                System.out.println(e.getName());
            }
        }

        System.out.println("==================================");
        //Query 1.15 : Who is the oldest employee in the organization? What is his age and which department he belongs to
        Optional<Employee> oldestEmployee = EmployeeDAO.empDataBase().stream().max(Comparator.comparingInt(Employee::getAge));
        //Optional<Employee> oldestEmployee = EmployeeDAO.empDataBase().stream().sorted(Comparator.comparingInt(Employee::getAge).reversed()).findFirst();
        System.out.println("Name:- " + oldestEmployee.get().getName());
        System.out.println("Age:- " + oldestEmployee.get().getAge());
        System.out.println("Department:- " + oldestEmployee.get().getDepartment());
    }//main
}//class
