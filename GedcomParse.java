package project3;
//Author: Wenxuan Wang
//Team Members: Wenxuan; Chengyi; Shweta hello
//purpose: for CS555-project3:
//Date: 2/13/2020
//description: main class;
// hhh
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GedcomParse {
	//fileds;
	List<Individual> individualList;
	List<Family> familyList;
	List<String> dataGet;
	Map<String, Individual> individualMap;
	
	//constructor;
	public GedcomParse() {
		individualList = new ArrayList<Individual>();
		familyList = new ArrayList<Family>();
		dataGet = new ArrayList<String>();
		individualMap = new HashMap<String, Individual>();
	}
	
	//read the data from file;
	public void readFile() {
		//dataGet = new ArrayList<String>();
		try {
//			InputStream file = new FileInputStream("/Users/wenxuanwang/Desktop/export-BloodTree.ged");
			InputStream file = new FileInputStream("/Users/wenxuanwang/Desktop/My-Family-15-Feb-2020-387.ged");
			BufferedReader reader = new BufferedReader( new InputStreamReader(file));
			String str = null;
			while(true) {
				// read the gedcom line by line
				str = reader.readLine();
				if(str!=null) {
					dataGet.add(str);
				}else {
					break;
				}
			}
			file.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	//analysis the data, then put it into individualList and famliyList separately;
	public void writeIntoIndividualList() {
		//tag 0:INDI
		//tag 1: NAME; SEX; BIRT; DEAT; FAMC; FAMS
		
		for(int i = 0; i<dataGet.size();i++) {
			String strTem = dataGet.get(i);
			// delete the blank from the string ;
			String str[] = strTem.split(" ");
			//if level is '0' & tag is 'INDI', then create and add this person
			try {
				if(str[0].equals("0") && str.length>=3 && str[2].equals("INDI")) {
					//create an new Individual object, then store the information;
					Individual thisPerson = new Individual();
					String id = str[1].replaceAll("@","");
					//Set the information about this person so far;
					thisPerson.setId(id);
					thisPerson.setAlive(true);
					thisPerson.setDeath("NA");
					thisPerson.setChild("NA");
					thisPerson.setSpouse("NA");
					individualList.add(thisPerson);
					//put this person into map also, in order to find it to add other information;
					individualMap.put(id,thisPerson);
					//if level is '0 & tag is "FAM', then create and add this family;
				}
//				else if(str[0].equals("0") && str.length>=3 && str[2].equals("FAM")) {
//					//create a new Family object, then store the information;
//					Family thisFamily = new Family();
//					String id = str[1].replaceAll("@", "");
//					thisFamily.setID(id);
//					thisFamily.setDivorced("NA");
//					familyList.add(thisFamily);
//				}
				
				if(str[0].equals("1")) {
					if(str[1].equals("NAME")) {
						//If level is 0; and tag is NAME; then add the name to the person just put into the list;
						String name="";
						for(int j=2; j<str.length;j++) {
							name = name +" "+ str[j];
						}
						individualList.get(individualList.size()-1).setName(name);
						//if level is 0 & tag is Sex, then add the gender
					}else if(str[1].equals("SEX")) {
						individualList.get(individualList.size()-1).setGender(str[2]);
					}else if(str[1].equals("BIRT")) {
						//then skip to the next line
						String birthString = dataGet.get(++i);
						String[] birthArray = birthString.split(" ");
						//check the tag
						if(birthArray[0].equals("2") && birthArray[1].equals("DATE")) {
							String day = birthArray[2];
							String month = birthArray[3];
							String year = birthArray[4];
							
							switch(month) {
							case "JAN": month = "-01-"; break;
							case "FEB": month = "-02-"; break;
							case "MAR": month = "-03-"; break;
							case "APR": month = "-04-"; break;
							case "MAY": month = "-05-"; break;
							case "JUN": month = "-06-"; break;
							case "JUL": month = "-07-"; break;
							case "AUG": month = "-08-"; break;
							case "SEP": month = "-09-"; break;
							case "OCT": month = "-10-"; break;
							case "NOV": month = "-11-"; break;
							case "DEC": month = "-12-"; break;
							default: month ="-Error-";
							}
							//get this person and set his/her birthday;
							individualList.get(individualList.size()-1).setBirthday(year+month+day);
							//set his/her age;
							int age = 2020 - Integer.parseInt(year);
							individualList.get(individualList.size()-1).setAge(age);
						}
					}else if(str[1].equals("DEAT")) {
						//the skip to the next Line
						String deathString = dataGet.get(++i);
						String[] deathArray = deathString.split(" ");
						//check the tag
						if(deathArray[0].equals("2") && deathArray[1].equals("DATE")) {
							String day = deathArray[2];
							String month = deathArray[3];
							String year = deathArray[4];
							
							switch(month) {
							case "JAN": month = "-01-"; break;
							case "FEB": month = "-02-"; break;
							case "MAR": month = "-03-"; break;
							case "APR": month = "-04-"; break;
							case "MAY": month = "-05-"; break;
							case "JUN": month = "-06-"; break;
							case "JUL": month = "-07-"; break;
							case "AUG": month = "-08-"; break;
							case "SEP": month = "-09-"; break;
							case "OCT": month = "-10-"; break;
							case "NOV": month = "-11-"; break;
							case "DEC": month = "-12-"; break;
							default: month ="-Error-";
							}
							//get this person and set his/her birthday;
							individualList.get(individualList.size()-1).setDeath(year+month+day);
							//set his/her isAlive;
							individualList.get(individualList.size()-1).setAlive(false);
						}
					}else if(str[1].equals("FAMC")) {
						//FAMC: individual is a child in family with family_ID
						String family_ID = str[2];
						family_ID = family_ID.replaceAll("@", "");
						individualList.get(individualList.size()-1).setChild(family_ID);

					}else if(str[1].equals("FAMS")) {
						//FAMS: individual is a spouse in family with family_ID
						String family_ID = str[2];
						family_ID = family_ID.replaceAll("@", "");
						individualList.get(individualList.size()-1).setSpouse(family_ID);
					}
					
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void writeIntofamilyList() {
		//tag 0: FAM
		//tag 1: MARR; HUSB; WIFE; CHIL; DIV; 
		for(int i = 0; i<dataGet.size();i++) {
			String strTem = dataGet.get(i);
			// delete the blank from the string ;
			String str[] = strTem.split(" ");
			
			try {
				if(str[0].equals("0")) {
					if(str.length>=3 && str[2].equals("FAM")) {
						//create an new Family object; then store the information got so far into this object
						Family thisFamily = new Family();
						String id = str[1].replaceAll("@","");
						thisFamily.setID(id);
						thisFamily.setDivorced("NA");
						//then add this family into List;
						familyList.add(thisFamily);
					}
				}else if(str[0].equals("1")){
					//Marriage event for family.
					//Typically followed by 2 DATE record that specifies the date.
					if(str[1].equals("MARR")) {
						//skip into next line;
						String marrString = dataGet.get(++i);
						String[] marrArray = marrString.split(" ");
						//check the tag
						if(marrArray[0].equals("2") && marrArray[1].equals("DATE")) {
							
							if(marrArray.length>=5) {
								String day = marrArray[2];
								String month = marrArray[3];
								String year = marrArray[4];
							
								switch(month) {
								case "JAN": month = "-01-"; break;
								case "FEB": month = "-02-"; break;
								case "MAR": month = "-03-"; break;
								case "APR": month = "-04-"; break;
								case "MAY": month = "-05-"; break;
								case "JUN": month = "-06-"; break;
								case "JUL": month = "-07-"; break;
								case "AUG": month = "-08-"; break;
								case "SEP": month = "-09-"; break;
								case "OCT": month = "-10-"; break;
								case "NOV": month = "-11-"; break;
								case "DEC": month = "-12-"; break;
								default: month ="-Error-";
								}
								familyList.get(familyList.size()-1).setMarried(year+month+day);
							}else if(marrArray.length==3) {
								String year = marrArray[2];
								familyList.get(familyList.size()-1).setMarried(year);
							}
						}
					}else if(str[1].equals("HUSB")) {
						String id = str[2];
						id = id.replaceAll("@", "");
						//get this person from individualMap
						Individual husband = individualMap.get(id);
						familyList.get(familyList.size()-1).setHusbandId(id);
						familyList.get(familyList.size()-1).setHusbandName(husband.getName());
					}else if(str[1].equals("WIFE")) {
						String id = str[2];
						id = id.replaceAll("@", "");
						//get this person from individualMap
						Individual wife = individualMap.get(id);
						familyList.get(familyList.size()-1).setWifeId(id);
						familyList.get(familyList.size()-1).setWifeName(wife.getName());	
					}else if(str[1].equals("CHIL")) {
						String id = str[2];
						id = id.replaceAll("@", "");
						//Individual chil = individualMap.get(id);
						Family thisFamily = familyList.get(familyList.size()-1);
						List<String> childList = thisFamily.getChildren();
						if(childList == null) {
							//if there are no child before, create an new list to hold it
							childList = new ArrayList<String>();
						}
						//then add this child into list;
						childList.add(id);
						//add the child list into this family;
						thisFamily.setChildren(childList);
					}
				}else if(str[1].equals("DIV")) {
					//then skip into next line, get the date
					String divString = dataGet.get(++i);
					String[] divArray = divString.split(" ");
					//check the tag
					if(divArray[0].equals("2") && divArray[1].equals("DATE")) {
						
						if(divArray.length>=5) {
							String day = divArray[2];
							String month = divArray[3];
							String year = divArray[4];
						
							switch(month) {
							case "JAN": month = "-01-"; break;
							case "FEB": month = "-02-"; break;
							case "MAR": month = "-03-"; break;
							case "APR": month = "-04-"; break;
							case "MAY": month = "-05-"; break;
							case "JUN": month = "-06-"; break;
							case "JUL": month = "-07-"; break;
							case "AUG": month = "-08-"; break;
							case "SEP": month = "-09-"; break;
							case "OCT": month = "-10-"; break;
							case "NOV": month = "-11-"; break;
							case "DEC": month = "-12-"; break;
							default: month ="-Error-";
							}
							familyList.get(familyList.size()-1).setDivorced(year+month+day);
						}else if(divArray.length==3) {
							String year = divArray[2];
							familyList.get(familyList.size()-1).setDivorced(year);
						}
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		GedcomParse proj3 = new GedcomParse();
		proj3.readFile();
		proj3.writeIntoIndividualList();
		proj3.writeIntofamilyList();
		//
		
		 
		System.out.println("Individuals");
		System.out.println("+-----+--------------------+--------+-----------+-----+-------+------------+-----------+-----------+");
		System.out.println("| ID  | Name               | Gender | Birthday  | Age | Alive | Death      | Child     | Spouse    |");
		System.out.println("+-----+--------------------+--------+-----------+-----+-------+------------+-----------+-----------+");
		for (Individual person : proj3.individualList) {
			 String child;
			 if(!person.getChild().equals("NA")) {
				 child = "{'"+person.getChild()+"'}";
			 }else {
				 child = "NA";
			 }
			 String spouse;
			 if(!person.getSpouse().equals("NA")) {
				 spouse = "{'"+person.getSpouse()+"'}";
			 }else {
				 spouse = "NA";
			 }
			System.out.printf("|%-5s|%-20s|%-8s|%-11s|%-5d|%-7b|%-12s|%-11s|%-11s|%n", 
					person.getId(), person.getName(), person.getGender(), person.getBirthday(),
					person.getAge(), person.getIsAlive(), person.getDeath(),  child  ,  spouse );
		}
		System.out.println("+-----+--------------------+--------+-----------+-----+-------+------------+-----------+-----------+");
		System.out.println("Families");
		System.out.println("+-----+------------+------------+------------+--------------------+-----------+--------------------+--------------------+");
		System.out.println("| ID  | Married    | Divorced   | Husband ID | Husband Name       | Wife ID   | Wife Name          |   Childern         |");
		System.out.println("+-----+------------+------------+------------+--------------------+-----------+--------------------+--------------------+");
		for (Family family : proj3.familyList) {
			String childString="";
			for(String child: family.getChildren()) {
				childString = childString+"','"+child;
			}
			childString = childString.substring(2);
			childString = "{"+childString+"'}";
			System.out.printf("|%-5s|%-12s|%-12s|%-12s|%-20s|%-11s|%-20s|%-20s|%n",
					family.getID(), family.getMarried(), family.getDivorced(), family.getHusbandID(), family.getHusbandName(),
					family.getWifeID(), family.getWifeName(), childString);
		}
		System.out.println("+-----+------------+------------+------------+--------------------+-----------+--------------------+--------------------+");
	

	}
}
