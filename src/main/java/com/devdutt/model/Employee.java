package com.devdutt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private int id;

    private String name;

    private int age;

    private String gender;

    private String department;

    private int yearOfJoining;

    private double salary;
}
