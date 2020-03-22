package project3;


//Author:Wenxuan Wang
import java.util.List; 
//import org.hamcrest.core.CombinableMatcher; 
import org.junit.Test;

//import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List; 
import java.util.Map;
import java.util.Set;

import org.junit.Before; 
public class Sprint2Test {

	private GedcomParse gedObj;
	List<Family> familyList = new ArrayList<Family>();
	List<Individual> individualList = new ArrayList<Individual>();
	Map<String, Individual> individualMap;
	
	public Sprint2Test() {
		gedObj = new GedcomParse();
		gedObj.readFile("/Users/wenxuanwang/Desktop/testForSprint2.ged");
		gedObj.writeIntoIndividualList();
		gedObj.writeIntofamilyList();
	}
	
	@Before
	public void set() {
		familyList = gedObj.familyList;
		individualList = gedObj.individualList;
		individualMap = gedObj.individualMap;
	}
	
	
	
	@Test
	public void testUS05() {
		//check: Marriage before death;
		for(Family one: familyList) {	//check every single family one
			//if this family married , then do next;
			if(!one.getMarried().equals("NA")) {
				String marrStr = one.getMarried();	//get the married date
				//System.out.println(marrStr);
				//get husband and wife ID, then find them.
				String husbandID = one.getHusbandID();
				String wifeID = one.getWifeID();
				Individual husband = individualMap.get(husbandID);
				Individual wife = individualMap.get(wifeID);
				//check if one of then dead, then print the error message;
				if(!husband.getDeath().equals("NA")) {
					String husbandDeath = husband.getDeath();
					//System.out.println(husbandDeath);
					if(!gedObj.checkOneDateAfterOneDateValid(husbandDeath, marrStr)) {
						System.out.println("ERROR: FAMILY: US05: "+one.getmarriedLine()+
								": "+one.getID()+": Married "+one.getMarried()
								+" after husband's ("+husbandID+") death on "
								+husband.getDeath());
					}
				}
				if(!wife.getDeath().equals("NA")) {
					String wifeDeath = wife.getDeath();
					if(!gedObj.checkOneDateAfterOneDateValid(wifeDeath, marrStr)) {
						System.out.println("ERROR: FAMILY: US05: "+one.getmarriedLine()+
								": "+one.getID()+": Married "+one.getMarried()
								+" after wife's ("+wifeID+") death on "
								+wife.getDeath());
					}
				}
			}
		}
	}
	
	
	@Test
	public void testUS06() {
		//check: Divorce before death
		//Divorce can only occur before death of both spouses
		for(Family one: familyList) {
			// if this family divorced, then do next
			if(!one.getDivorced().equals("NA")) {
				//get the divorced date
				String divStr = one.getDivorced();
				//then find this couple
				String husbandID = one.getHusbandID();
				String wifeID = one.getWifeID();
				Individual husband = individualMap.get(husbandID);
				Individual wife = individualMap.get(wifeID);
				
				//check one of they dead or not, 
				//if yes, then check the date valid or not
				//if yes, then print the error message;
				if(!husband.getDeath().equals("NA")) {
					String husbandDeath = husband.getDeath();
					if(!gedObj.checkOneDateAfterOneDateValid(husbandDeath, divStr)) {
						System.out.println("ERROR: FAMILY: US06: "+one.getmarriedLine()
											+": "+one.getID()+": Divorced "+one.getDivorced()
											+" after husband's ("+husbandID+") death on "
											+husbandDeath);
					}
				}
				if(!wife.getDeath().equals("NA")) {
					String wifeDeath = husband.getDeath();
					if(!gedObj.checkOneDateAfterOneDateValid(wifeDeath, divStr)) {
						System.out.println("ERROR: FAMILY: US06: "+one.getmarriedLine()
											+": "+one.getID()+": Divorced "+one.getDivorced()
											+" after wife's ("+wifeID+") death on "
											+wifeDeath);
					}
				}
			}
		}	
	}
	
	
	
	
}
