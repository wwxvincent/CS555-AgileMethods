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

//import java.util.Date;
//import java.text.SimpleDateFormat;
import org.junit.Before; 
public class Sprint3Test {

	private GedcomParse gedObj;
	List<Family> familyList = new ArrayList<Family>();
	List<Individual> individualList = new ArrayList<Individual>();
	Map<String, Individual> individualMap;
	
	public Sprint3Test() {
		gedObj = new GedcomParse();
		gedObj.readFile("/Users/wenxuanwang/Desktop/testCase.ged");
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
	@Test
	public void testUS07() {
		for(Individual person: individualList) {
			if(person.getDeath().equals("NA")) {
				if(person.getAge()>150) {
					//print the error message
					System.out.println("ERROR: INDIVIDUAL: US07: "+person.getbirthdayLine()+": "
										+person.getId()+": More then 150 years old - Birth date "
										+person.getBirthday());
				}
						
			}else {
				String birth =person.getBirthday();
				String death = person.getDeath();
				String yearBirthStr = birth.substring(0,4);
				String yearDeathStr = death.substring(0,4);
				int yearBirth =Integer.parseInt(yearBirthStr);
				int yearDeath =Integer.parseInt(yearDeathStr);
				if( (yearDeath-yearBirth)> 150) {
					System.out.println("ERROR: INDIVIDUAL: US07: "+person.getbirthdayLine()+": "
							+person.getId()+": More then 150 years old - Birth date "
							+person.getBirthday());
				}
			}
		}
	}
	
	@Test
	public void testUS08() {
		for(Family one: familyList) {
			//1. step get the married date of this family
			String marrStr = one.getMarried();
			String divoStr = one.getDivorced();
			//2. step get every child of this family and get his/her birthday
			List<String> childList = one.getChildren();
			for(String childId: childList) {
				if(!childId.equals("NA")) {
					Individual child = individualMap.get(childId);
					//System.out.println(child.getBirthday());//for test only
					String birthStr = child.getBirthday();
					//check married before child born
					if(!gedObj.checkOneDateAfterOneDateValid(birthStr, marrStr)) {
						//print the error message
						System.out.println("ANOMALY: FAMILY: US08: "+one.getmarriedLine()+": "
										+one.getID()+": "+"Child "+childId
										+" born "+birthStr+" before marriage on "
										+marrStr);
					}
					//check divorced after child born
					if(!divoStr.equals("NA")) {
						if(!gedObj.checkOneDateAfterOneDateValid(divoStr, birthStr)) {
							//print the error message
							System.out.println("ANOMALY: FAMILY: US08: "+one.getdivorcedLine()+": "
											+one.getID()+": "+"Child "+childId
											+" born "+birthStr+" after divorce on "
											+divoStr);
						}
					}
				}
			}
		}
	}
	
	/***********************************************
	 * Wenxuan Wang part done!
	 ************************************************/

	
	
	
	
	
	//-----------------Shweta Singh Part-------------------	
	//-----------------Shweta Singh part done--------------
	
	
	//-----------------Chengyi Zhang Part-------------------
	//-----------------Chengyi Zhang part done--------------
	
	
	
	
	
	
	
}