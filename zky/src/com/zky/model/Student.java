package com.zky.model;

public class Student {
	int id;
	String name;
	String number;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", number=" + number + "]";
	}
	public Student() {
		super();
	}
	public Student(int id, String name, String number) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
	}
	

}
