package project3;


//Format: Wenxuan Wang
//Format Used: Chengyi Zhang
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
public class TestCase_ChengyiZhang_Sprint3 {

	private GedcomParse gedObj;
	List<Family> familyList = new ArrayList<Family>();
	List<Individual> individualList = new ArrayList<Individual>();
	Map<String, Individual> individualMap;
	
	public TestCase_ChengyiZhang_Sprint3() {
		gedObj = new GedcomParse();
		gedObj.readFile("TestCase_ChengyiZhang_Sprint3GED.ged");
		gedObj.writeIntoIndividualList();
		gedObj.writeIntofamilyList();
	}
	
	@Before
	public void set() {
		familyList = gedObj.familyList;
		individualList = gedObj.individualList;
		individualMap = gedObj.individualMap;
	}
	
	
	//-----------------Chengyi Zhang Part-------------------
	
	
	//get the age of death of someone
	public int ageWhenDeath(Individual one) {
		String birth = one.getBirthday();
		String death = one.getDeath();
		int birthyear = Integer.parseInt(birth.substring(0, 3));
		int deathyear = Integer.parseInt(death.substring(0, 3));
		if(deathyear<birthyear)return -1;
		return deathyear - birthyear;
	}
	
	
	@Test
	public void US72() { // Chengyi Zhang US72: Anomaly: List people died before age of 30
		for (Individual one : individualList) {
			if(!one.getIsAlive()) 
				if(ageWhenDeath(one)<30) {
					System.out.println("ANOMALY: INDIVIDUAL: US72: "+one.gedeathLine()+": "
							+one.getId()+": Died before age of 30 in "
							+one.getDeath());
				}
		}
	}
	
	@Test
	public void US88() { // Chengyi Zhang US88: Error: Child birthday is after father's death
		for (Family one : familyList) {
			for(String ID : one.getChildren()) {
				for(Individual person : individualList) {
					if(person.getId().equals(ID)) {
		//Step 1. Till now, we get each child in a family
						for (Individual p2 : individualList) {
							if(p2.getId().equals(one.getHusbandID())) {
		//Step 2. Till now, we get that husband of this family
								if(!p2.getIsAlive())
									if(gedObj.checkOneDateAfterOneDateValid(person.getBirthday(),p2.getDeath())){
										System.out.println("ERROR: INDIVIDUAL: US88: "+person.getbirthdayLine()+": "
												+person.getId()+": Birthday "+ person.getBirthday() +" is after father's death "
												+p2.getDeath());
									}
								break;
							}
						}
					}
				}
			}
		}
	}

	
	//-----------------Chengyi Zhang part done--------------
	
	
}