package project3;


//Author:Wenxuan Wang
import java.util.List; 
//import org.hamcrest.core.CombinableMatcher; 
import org.junit.Test;

import static org.junit.Assert.assertTrue;

//import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List; 
import java.util.Map;
import java.util.Set;

//import java.util.Date;
//import java.text.SimpleDateFormat;
import org.junit.Before; 
public class Sprint4Test {

	private GedcomParse gedObj;
	List<Family> familyList = new ArrayList<Family>();
	List<Individual> individualList = new ArrayList<Individual>();
	Map<String, Individual> individualMap;
	
	public Sprint4Test() {
		gedObj = new GedcomParse();
		gedObj.readFile("/Users/wenxuanwang/Desktop/OriginalGED.ged");
		gedObj.writeIntoIndividualList();
		gedObj.writeIntofamilyList();
	}
	
	@Before
	public void set() {
		familyList = gedObj.familyList;
		individualList = gedObj.individualList;
		individualMap = gedObj.individualMap;
	}
	
	
	/**
	 * --------------------Wenxuan Wang part-------------------------------
	 */
	//Mother should be less than 60 years older than her children 
	//and father should be less than 80 years older than his children
	@Test
	public void testUS12() {
		for(Family one: familyList) {
			String fatherID = one.getHusbandID();
			String momID = one.getWifeID();
			Individual father = individualMap.get(fatherID);
			Individual mom = individualMap.get(momID);
			int fatherAge = father.getAge();
			int momAge = mom.getAge();
			List<String> childList = one.getChildren();
			for(String childID: childList) {
				if(!childID.equals("NA")) {
					Individual child = individualMap.get(childID);
					int childAge = child.getAge();
					assertTrue("ERROR: FAMILY: US12: ",(fatherAge-childAge) <80);
					assertTrue("ERROR: FAMILY: US12:",(momAge-childAge) <60);
				}
			}
		}
		
	}
	
	//Parents should not marry any of their children
	@Test
	public void testUS17() {
		for(Family one: familyList) {
			String fatherID = one.getHusbandID();
			String momID = one.getWifeID();
			List<String> childList = one.getChildren();
			for(String childID: childList) {
				if(!childID.equals("NA")) {
					
					assertTrue("page",!fatherID.equals(childID));
					assertTrue("Error this child",!momID.equals(childID));
				}
			}
		}
		
	}
	
	
	/***********************************************
	 * Wenxuan Wang part done!
	 ************************************************/

	
	
	
	
	
	/**
	 * --------------------Shweta Singh part-------------------------------
	 */
	@Test
	public void testUS21() {

		assertEquals(individualList.size(), 0);//FALSE
		assertEquals(gedObj.checkGender(), true);//TRUE
		assertFalse(individualList.isEmpty());//TRUE
		 
	}
	@Test
	public void testUS15() throws ParseException {

		assertEquals(individualList.size(), 0);//False
		assertEquals(gedObj.checkFewerSibling(), true);//TRUE
		assertFalse(individualList.isEmpty());//TRUE

	/***********************************************
	 * Shweta Singh part done!
	 ************************************************/
	
	
	//-----------------Chengyi Zhang Part-------------------
	//-----------------Chengyi Zhang part done--------------
	
	
	
	
	
	
	
}
