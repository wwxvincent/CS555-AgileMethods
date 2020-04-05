package project3;
import java.util.List;
//Author: Wenxuan Wang
//Team Members: Wenxuan; Chengyi; Shweta
//purpose: for CS555-project3:
//Date: 2/13/2020
//description: sub class:Individual for GedcomParse;

public class Family {
	
	//fileds;
	private String ID;
	private int IDLine;
	private String married;
	private int marriedLine;
	private String divorced;
	private int divorcedLine;
	private String husbandID;
	private int husbandIDLine;
	private String husbandName;
	private int husbandNameLine;
	private String wifeID;
	private int wifeIDLine;
	private String wifeName;
	private int wifeNameLine;
	private List<String> children;
	
	
	//add for sprint find the line number of the gedcom file;
	public void setIDLine(int IDLine) {
		this.IDLine = IDLine;
	}
	public int getIDLine() {
		return IDLine;
	}
	public void setmarriedLine(int marriedLine) {
		this.marriedLine = marriedLine;
	}
	public int getmarriedLine() {
		return marriedLine;
	}
	public void setdivorcedLine(int divorcedLine) {
		this.divorcedLine = divorcedLine;
	}
	public int getdivorcedLine() {
		return divorcedLine;
	}
	public void sethusbandIDLine(int husbandIDLine) {
		this.husbandIDLine = husbandIDLine;
	}
	public int gethusbandIDLine() {
		return husbandIDLine;
	}
	public void sethusbandNameLine(int husbandNameLine) {
		this.husbandNameLine = husbandNameLine;
	}
	public int gethusbandNameLine() {
		return husbandNameLine;
	}
	public void setwifeIDLine(int wifeIDLine) {
		this.wifeIDLine = wifeIDLine;
	}
	public int getwifeIDLine() {
		return wifeIDLine;
	}
	public void setwifeNameLine(int wifeNameLine) {
		this.wifeNameLine = wifeNameLine;
	}
	public int getwifeNameLine() {
		return wifeNameLine;
	}
	
	//accessors
	public String getID() {
		return ID;
	}
	public String getMarried() {
		return married;
	}
	public String getDivorced() {
		return divorced;
	}
	public String getHusbandID() {
		return husbandID;
	}
	public String getHusbandName() {
		return husbandName;
	}
	public String getWifeID() {
		return wifeID;
	}
	public String getWifeName() {
		return wifeName;
	}
	public List<String> getChildren() {
		return children;
	}
	
	// mutators
	public void setID(String ID) {
		this.ID = ID;
	}
	public void setMarried(String married) {
		this.married = married;
	}
	public void setDivorced(String divorced) {
		this.divorced = divorced;
	}
	public void setHusbandId(String husbandID) {
		this.husbandID = husbandID;
	}
	public void setHusbandName(String husbandName) {
		this.husbandName = husbandName;
	}
	public void setWifeId(String wifeID) {
		this.wifeID = wifeID;
	}
	public void setWifeName(String wifeName) {
		this.wifeName = wifeName;
	}
	public void setChildren(List<String> children) {
		this.children = children;
	}

}
