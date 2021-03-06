package project3;



//Author: Wenxuan Wang
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
import java.text.ParseException;
public class SprintTest1 {

	private GedcomParse gedObj;
	List<Family> familyList = new ArrayList<Family>();
	List<Individual> individualList = new ArrayList<Individual>();
	Map<String, Individual> individualMap;
	
	public SprintTest1() {
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
	
	
	//-----------------Wenxuan Wang Part---------------
	@Test
	public void testUS03() {
		//check birth before death
		List<Individual> deathErrorList = new ArrayList<>();
		for(Individual person: individualList) {
			//IF the death is not "NA", then check the death is valid or not
			if(!person.getDeath().equals("NA")) {
				String birthStr = person.getBirthday();
				String deathStr = person.getDeath();
				if(!gedObj.checkOneDateAfterOneDateValid(deathStr,birthStr)) {
					deathErrorList.add(person);
				}
				//do something to display error!
			}
		}
		//System.out.println(deathErrorList.size());
		if(deathErrorList.isEmpty()) {
			System.out.println("No Death error");
		}else {
			for (Individual person : deathErrorList) {
				System.out.println("ERROR: INDIVIDUAL: US03: "+person.gedeathLine()
									+": "+person.getId()+": Died "+person.getDeath()+
									" before born "+person.getBirthday());
			}
		}
	}
	
	@Test
	public void testUS04() {
		//check: Marriage before divorce;
		List<Family> marriedErrorList = new ArrayList<>();
		for(Family one: familyList) {
			//IF the death is not "NA", then check the death is valid or not
			//for test
			//System.out.println(one.getMarried());
			if(!one.getDivorced().equals("NA")) {
				String marrStr = one.getMarried();
				String divStr = one.getDivorced();
				if(!gedObj.checkOneDateAfterOneDateValid(divStr,marrStr)) {
					marriedErrorList.add(one);
				}
			}
				//do something to display error!
		}
		//System.out.println(marriedErrorList.size());
		if(marriedErrorList.isEmpty()) {
					System.out.println("No Married error");
		}else {
			for (Family family : marriedErrorList) {
				System.out.println("ERROR: FAMILY: US04: "+family.getdivorcedLine()
									+": "+family.getID()+": Divorced "+family.getDivorced()+
									" before married "+family.getMarried());
			}
		}
	}
	/***********************************************
	 * Wenxuan Wang part done!
	 ************************************************/
	
	
	//-----------------Shweta Singh Part-------------------
	
//	@Test
//	public void US27_individualAge() throws ParseException {
//		assertEquals(gedObj.individualAge(), true);//TRUE
//		assertFalse(individualList.isEmpty());//TRUE
//		 
//	}
//	
//	@Test
//	public void US30_listLivingMarried() {
//		assertEquals(individualList.size(), 0);//FALSE
//		assertEquals(gedObj.listLivingMarried(), true);//TRUE
//		 
//	}
	
	//-----------------Shweta Singh part done--------------
	
	
	//-----------------Chengyi Zhang Part-------------------
	//-----------------Chengyi Zhang part done--------------
}
