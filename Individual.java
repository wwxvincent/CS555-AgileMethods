package AP;
//Author: Wenxuan Wang
//Team Members: Wenxuan; Chengyi; Shweta
//purpose: for CS555-project3:
//Date: 2/13/2020
//description: sub class:Individual for GedcomParse;

public class Individual {
	
	//fields;
	private String ID;
	private int IDLine;
	private String name;
	private int nameLine;
	private String gender;
	private int genderLine;
	private String birthday;
	private int birthdayLine;
	private int age;
	private boolean alive;
	private String death;
	private int deathLine;
	private String child;
	private String spouse;
	
	
	//add for sprint find the line number of the gedcom file;
	public void setIDline(int IDLine) {
		this.IDLine = IDLine;
	}
	public int getIDLine() {
		return IDLine;
	}
	public void setNameLine(int nameLine) {
		this.nameLine = nameLine;
	}
	public int getnameLine() {
		return nameLine;
	}
	public void setgenderLine(int genderLine) {
		this.genderLine = genderLine;
	}
	public int getgenderLine() {
		return genderLine;
	}
	public void setbirthdayLine(int birthdayLine) {
		this.birthdayLine = birthdayLine;
	}
	public int getbirthdayLine() {
		return birthdayLine;
	}
	public void setdeathLine(int deathLine) {
		this.deathLine = deathLine;
	}
	public int gedeathLine() {
		return deathLine;
	}
	
	
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
