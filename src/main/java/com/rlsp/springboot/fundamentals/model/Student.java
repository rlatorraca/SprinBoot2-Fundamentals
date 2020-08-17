package com.rlsp.springboot.fundamentals.model;

import java.util.ArrayList;
import java.util.List;
import static java.util.Arrays.asList;

public class Student {

	private int id;
	private String name;
	public static List<Student> studentList;
	
	static {
		studentRespository();
	}
	
	

	public Student(int id, String name) {
		this(name);
		this.id = id;
		
	}
	
	private static void studentRespository() {
		studentList = new ArrayList<>(asList(new Student(1, "Rodrigo"), new Student(2, "Ronaldo")));
	}
	


	public Student(String name) {		
		this.name = name;
	}

	
	public Student() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
}
