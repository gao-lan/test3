package com.example.test3;

public abstract class BusinessCard {
	
	protected String name;
	protected String tel;
	protected int age;
	protected String work;
	
	public BusinessCard(String name, String tel, int age, String work) {
		super();
		this.name = name;
		this.tel = tel;
		this.age = age;
		this.work = work;
		
	}
	
	public String getWork() {
		
		return work;
	}

	public void setWork(String work){
		this.work = work;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
