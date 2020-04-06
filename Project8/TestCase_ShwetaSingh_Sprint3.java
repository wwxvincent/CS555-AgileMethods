package AP;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.junit.Test;
import AP.Family;
import AP.GedcomParse;
import AP.Individual;
import static org.hamcrest.CoreMatchers.*;

public class TestCase_ShwetaSingh_Sprint2 {
	
	private GedcomParse gedObj;
	List<Family> familyList = new ArrayList<Family>();
	List<Individual> individualList = new ArrayList<Individual>();
	Map<String, Individual> individualMap;
	
	public TestCase_ShwetaSingh_Sprint2() {
		gedObj = new GedcomParse();
		gedObj.readFile("C:/Users/Shweta Singh/Desktop/GedcomTestInput.ged");
		//gedObj.readFile("C:/Users/Shweta Singh/eclipse-workspace/AP 555/src/AP/.ged");
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
	public void US31_listUpcomingBirth() {
		assertEquals(individualList.size(), 0);//FALSE
		assertEquals(gedObj.listUpcomingBirth(), true);//TRUE
		 
	}

	@Test
	public void US38_listLivingSingle() throws ParseException {
		assertEquals(gedObj.individualAge(), true);//TRUE
		assertEquals(gedObj.listLivingSingle(), true);//TRUE
		assertFalse(individualList.isEmpty());//TRUE
		 
	}
}
