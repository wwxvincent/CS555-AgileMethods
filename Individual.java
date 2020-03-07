package project3;
//Author: Wenxuan Wang
//Team Members: Wenxuan; Chengyi; Shweta singh 10457493
//purpose: for CS555-project3:
//Date: 2/13/2020
//description: sub class:Individual for GedcomParse;

public class Individual {
	
	//fields;
	private String ID;
	private String name;
	private String gender;
	private String birthday;
	private int age;
	private boolean alive;
	private String death;
	private String child;
	private String spouse;
	
	//accessors;
	public String getId() {
		return ID;
	}
	public String getName() {
		return name;
	}
	public String getGender() {
		return gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public int getAge() {
		return age;
	}
	public boolean getIsAlive() {
		return alive;
	}
	public String getDeath() {
		return death;
	}
	public String getChild() {
			return child;
	}
	public String getSpouse() {
			return spouse;
	}
	//mutators;
	public void setId(String ID) {
		this.ID = ID;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public void setDeath(String death) {
		this.death = death;
	}
	public void setChild(String child) {
		this.child = child;
	}
	public void setSpouse(String spouse) {
		this.spouse = spouse;
	}
}
