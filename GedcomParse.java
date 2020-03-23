package project3;
//Author: Wenxuan Wang
//Team Members: Wenxuan; Chengyi; Shweta
//purpose: for CS555-project3:
//Date: 2/13/2020
//description: main class;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import project3.Family;
import project3.Individual;

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
	public void readFile(String filePath) {
		//dataGet = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader( new FileReader(filePath));
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
			reader.close();
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
					
					//set the gedom line info
					int count = i;
					thisPerson.setIDline(count+1);
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
						//set the name gedcom line info
						int count =i;
						individualList.get(individualList.size()-1).setNameLine(count+1);
						//if level is 0 & tag is Sex, then add the gender
					}else if(str[1].equals("SEX")) {
						individualList.get(individualList.size()-1).setGender(str[2]);
						//set the gender gedcom line info
						int count =i;
						individualList.get(individualList.size()-1).setgenderLine(count+1);
						
					}else if(str[1].equals("BIRT")) { //check if string is BIRT
						//then skip to the next line
						int count = i;
						String birthString = dataGet.get(++i);
						String[] birthArray = birthString.split(" ");
						//check the tag
						if(birthArray[0].equals("2") && birthArray[1].equals("DATE")) {//CREATE DATE FROM STRING
							String day = birthArray[2]; //get day
							String month = birthArray[3]; //get month
							String year = birthArray[4]; //get year
							
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
							/*int age = 2020 - Integer.parseInt(year);
							individualList.get(individualList.size()-1).setAge(age);*/
							//set birthday ged line info
							individualList.get(individualList.size()-1).setbirthdayLine(count+1);
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
							
//							/*
//							 * * Sprint 1: Birth before death
//							 *  original method;
//							 */
//							boolean flag = false;
//							String birthStr = individualList.get(individualList.size()-1).getBirthday();
//							String yearBirthStr = birthStr.substring(0,4);
//							int yearBirthInt = Integer.parseInt(yearBirthStr);
//							int yearDeathInt = Integer.parseInt(year);
//							//check;
//							if(yearDeathInt > yearBirthInt) {
//								flag = true;
//								System.out.println("test: year to death is okay");
//							}else if(yearDeathInt == yearBirthInt) {
//								String monthBirthStr = birthStr.substring(6,8);
//								int monthBirthInt = Integer.parseInt(monthBirthStr);
//								int monthDeathInt = Integer.parseInt(month);
//								if(monthBirthInt < monthDeathInt) {
//									flag = true;
//									System.out.println("test: month to death is okay");
//								}else if(monthBirthInt == monthDeathInt) {
//									String dayBirthStr = birthStr.substring(8);
//									int dayBirthInt = Integer.parseInt(dayBirthStr);
//									int dayDeathInt = Integer.parseInt(day);
//									if(dayBirthInt < dayDeathInt) {
//										flag = true;
//										System.out.println("test: day to death is okay");
//									}
//								}
//							}
							//String birthStr = individualList.get(individualList.size()-1).getBirthday();
							//boolean flag = checkOneDateAfterOneDateValid(year,month,day,birthStr);
							
							//if(flag) {
								
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
								
								//set the death info from the gedcom line;
								int count = i;
								individualList.get(individualList.size()-1).setdeathLine(count+1);
							//}
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
						//set family id gedcom line info
						int count = i;
						thisFamily.setIDLine(count+1);
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
							//set married gedcom line info
							int count =i;
							familyList.get(familyList.size()-1).setmarriedLine(count+1);
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
					}else if(str[1].equals("DIV")) {
						//then skip into next line, get the date
						//System.out.println("DIV yes!");
						String divString = dataGet.get(++i);
						String[] divArray = divString.split(" ");
						//check the tag
						if(divArray[0].equals("2") && divArray[1].equals("DATE")) {
							if(divArray.length>=5) {
								String day = divArray[2];
								String month = divArray[3];
								String year = divArray[4];
							/*
							 * Sprint Story: Marriage before divorce:
							 */
							//String marriStr= familyList.get(familyList.size()-1).getMarried();
							//boolean flag = checkOneDateAfterOneDateValid(year, month, day,marriStr);
						
							//if(flag) {
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
							//}
							}else if(divArray.length==3) {
								String year = divArray[2];
								//String marriStr= familyList.get(familyList.size()-1).getMarried();
								//String marrYearStr= marriStr.substring(0,4);
								//int yearDiv = Integer.parseInt(year);
								//int yearMar = Integer.parseInt(marrYearStr);
								//if(yearDiv > yearMar) {
									familyList.get(familyList.size()-1).setDivorced(year);
								//}else {
								//	System.out.println(familyList.get(familyList.size()-1).getID()+" divoce date error");
								//}
							}
							//set divorced line gedcom info
							int count = i;
							familyList.get(familyList.size()-1).setdivorcedLine(count+1);
						}
					}
				}
					
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * for sprint 1 Wenxuan Wang part
	 * story 1: birth before death
	 * story 2: Marriage before divorce
	 */
	public boolean checkOneDateAfterOneDateValid(String deathStr, String birthStr) {
		
		boolean flag = false;
		//convert birthStr to
		//test
		//System.out.println(deathStr);
		//System.out.println(birthStr);
		String yearBirthStr = birthStr.substring(0,4);
		String yearDeathStr = deathStr.substring(0,4);
		int yearBirthInt = Integer.parseInt(yearBirthStr);
		int yearDeathInt = Integer.parseInt(yearDeathStr);
		//check;
		if(yearDeathInt > yearBirthInt) {
			flag = true;
			//System.out.println("test: year to death is okay");
		}else if(yearDeathInt == yearBirthInt) {
			String monthBirthStr = birthStr.substring(5,7);
			String monthDeathStr = deathStr.substring(5,7);
			int monthBirthInt = Integer.parseInt(monthBirthStr);
			int monthDeathInt = Integer.parseInt(monthDeathStr);
			if(monthBirthInt < monthDeathInt) {
				flag = true;
				//System.out.println("test: month to death is okay");
			}else if(monthBirthInt == monthDeathInt) {
				String dayBirthStr = birthStr.substring(8);
				String dayDeathStr = deathStr.substring(8);
				int dayBirthInt = Integer.parseInt(dayBirthStr);
				int dayDeathInt = Integer.parseInt(dayDeathStr);
				if(dayBirthInt < dayDeathInt) {
					flag = true;
					//System.out.println("test: day to death is okay");
				}
			}
		}
		
		return flag;
	}
	// story 1: birth before death
	public void US03checkBBD(List<Individual> individualList) {
		List<Individual> deathErrorList = new ArrayList<>();
		for(Individual person: individualList) {
			//IF the death is not "NA", then check the death is valid or not
			if(!person.getDeath().equals("NA")) {
				String birthStr = person.getBirthday();
				String deathStr = person.getDeath();
				if(!checkOneDateAfterOneDateValid(deathStr,birthStr)) {
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
	// story 2: Marriage before divorce
	public void US04checkMDB(List<Family> familyList) {
		List<Family> marriedErrorList = new ArrayList<>();
		for(Family one: familyList) {
			//IF the death is not "NA", then check the death is valid or not
			//for test
			//System.out.println(one.getMarried());
			if(!one.getDivorced().equals("NA")) {
				String marrStr = one.getMarried();
				String divStr = one.getDivorced();
				if(!checkOneDateAfterOneDateValid(divStr,marrStr)) {
					marriedErrorList.add(one);
				}
			}
				//do something to display error!
		}
		//System.out.println(marriedErrorList.size());
		if(marriedErrorList.isEmpty()) {
					System.out.println("No Married error");
		}else {
//			System.out.println("Married Error List");
//			System.out.println("+-----+------------+------------+------------+--------------------+-----------+--------------------+--------------------+");
//			System.out.println("| ID  | Married    | Divorced   | Husband ID | Husband Name       | Wife ID   | Wife Name          |   Childern         |");
//			System.out.println("+-----+------------+------------+------------+--------------------+-----------+--------------------+--------------------+");
//			for (Family family : marriedErrorList) {
//				String childString="";
//				if(!(family.getChildren()==null)) {
//					for(String child: family.getChildren()) {
//						//for test to debug
//						//System.out.println(child);
//						childString = childString+"','"+child;	
//					}
//					childString = childString.substring(2);
//					childString = "{"+childString+"'}";
//				}else {
//					childString = "{'"+"NA"+"'}";
//				}
//				System.out.printf("|%-5s|%-12s|%-12s|%-12s|%-20s|%-11s|%-20s|%-20s|%n",
//						family.getID(), family.getMarried(), family.getDivorced(), family.getHusbandID(), family.getHusbandName(),
//						family.getWifeID(), family.getWifeName(), childString);
//			}
//			System.out.println("+-----+------------+------------+------------+--------------------+-----------+--------------------+--------------------+");
//			System.out.println();
			for (Family family : marriedErrorList) {
				System.out.println("ERROR: FAMILY: US04: "+family.getdivorcedLine()
									+": "+family.getID()+": Divorced "+family.getDivorced()+
									" before married "+family.getMarried());
			}
		}
	}
	
	//shweta singh US 30 LIST living married
	public void listLivingMarried() {
		int count=1;
		System.out.println("--------------Sprint 2-US30-List living married individuals-Shweta Singh----------------");
		for (Individual person : individualList) {
			if(!person.getSpouse().equals("NA")) {
				if(person.getIsAlive()) {
					System.out.println("Living married Individual: "+count+" "+person.getName());
					count++;
				}
			
			}
		}
	}
	//shweta singh US 27 LIST individual ages
	
	public void individualAge() throws ParseException {
		
		for (Individual person : individualList) {
			int age =0;
			DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			String bddate =person.getBirthday();//birthday
			String byear = bddate.substring(0,4);
			String bmonth = bddate.substring(5,7);
			String bday = bddate.substring(8);
			Date bdate = new SimpleDateFormat("MM/dd/yyyy").parse(bmonth+"/"+bday+"/"+byear);
			//System.out.println(person.getBirthday()+"\t"+bdate);
			
			if(person.getDeath() != "NA") { //deathday
				String sddate =person.getDeath();
				String dyear = sddate.substring(0,4);
				String dmonth = sddate.substring(5,7);
				String dday = sddate.substring(8);//"dd/MM/yyyy"
				Date ddate = new SimpleDateFormat("MM/dd/yyyy").parse(dmonth+"/"+dday+"/"+dyear);
				
			    int d1 = Integer.parseInt(formatter.format(bdate));                            
			    int d2 = Integer.parseInt(formatter.format(ddate));                          
			    age = (d2 - d1) / 10000; 
			    //individualList.get(individualList.size()-1).setAge(age);
			}
			
			else {
				int d1 = Integer.parseInt(formatter.format(bdate));                            
			    int d2 = Integer.parseInt(formatter.format(new Date()));                          
			    age = (d2 - d1) / 10000;
			    //System.out.println(age);
			}

			person.setAge(age); //age is updated in individual table
		}
	}
	
	public void maleLastTime() {// shweta singh user story16
		
		System.out.println("\n-------------Sprint 1-US16-Male last names-Shweta Singh---------------\n");
		for (Family family : familyList) {
			//System.out.print("\nFamily Last name: ");
			String lastName="";
			if(!(family.getChildren()==null)) {
				lastName = (family.getHusbandName().split("/"))[1].trim();
				//System.out.print(lastName+"\n");
				for(String child: family.getChildren()) {
					//childLastName = child
					//System.out.print("Child surname: ");
					for(Individual person : individualList) {
						String childName = (person.getName().split("/"))[1].trim();
						String personName = person.getName().replace("/", "");
						
						if(person.getId().equals(child) && person.getGender().equals("M")) {
							if(lastName.equals(childName)) {
								
								//true
								//System.out.print(childName+"\n");
							}
							else {
								System.out.println("\nERROR: FAMILY: US16: 495: "+family.getID()+": "+
							"Surname does not match for child: "+person.getId()+" : "+personName);
							}
						}
					}
				}
			}
		}
	}
	
	public void listDeceased() {// shweta singh user story16

		System.out.println("\n--------------Sprint 1-US29-List deceased-Shweta Singh----------------\n");
		for (Individual person : individualList) {
			 String listDece="";
			 if(person.getIsAlive() == false) {
				 listDece = person.getName();
				 System.out.println("Deceased Person: "+listDece.replace("/", "").trim());
			 }
		}
	}
	
	//Sprint 1 Chengyi Zhang part
	
	public boolean isValid(String datestr){
		String[] nums = datestr.split("-",3);
		int year = Integer.parseInt(nums[0]);
		int month = Integer.parseInt(nums[1]);
		int day = Integer.parseInt(nums[2]);
		HashMap<Integer, Integer> monthdays = new HashMap<>();
		if(year>2020){
			return false;
		}
		if(year%4==0 && year%400!=0){
			monthdays.put(1,31);
			monthdays.put(2,29);
			monthdays.put(3,31);
			monthdays.put(4,30);
			monthdays.put(5,31);
			monthdays.put(6,30);
			monthdays.put(7,31);
			monthdays.put(8,31);
			monthdays.put(9,30);
			monthdays.put(10,31);
			monthdays.put(11,30);
			monthdays.put(12,31);
			if(day<1 || day>monthdays.getOrDefault(month,-1)){
				return false;
			}
		}else{
			monthdays.put(1,31);
			monthdays.put(2,28);
			monthdays.put(3,31);
			monthdays.put(4,30);
			monthdays.put(5,31);
			monthdays.put(6,30);
			monthdays.put(7,31);
			monthdays.put(8,31);
			monthdays.put(9,30);
			monthdays.put(10,31);
			monthdays.put(11,30);
			monthdays.put(12,31);
			if(day<1 || day>monthdays.getOrDefault(month,-1)){
				return false;
			}
		}
		return false;
	}

	public void checkDates(){
		System.out.println("\n-----------------Sprint 1 Chengyi Zhang story 1:------------------------");
		System.out.println("All Dates must be valid\n");
		
		//Check dates in individual list
		List<Individual> Indi_Date_Errors = new ArrayList<>();
		boolean checkdea, checkbir;
		for(Individual one : individualList){
			if(!one.getDeath().equals("NA")){
				checkdea = isValid(one.getDeath());
			}else checkdea = true;
			checkbir = isValid(one.getBirthday());
			if(checkdea&&checkbir == false){
				Indi_Date_Errors.add(one);
			}
		}
		if(Indi_Date_Errors.isEmpty()){
			System.out.println("No Date Error in Individual List.\n");
		}
		else{
			System.out.println("Individual Date Error List:");
			System.out.println("+-----+--------------------+--------+-----------+-----+-------+------------+-----------+-----------+");
			System.out.println("| ID  | Name               | Gender | Birthday  | Age | Alive | Death      | Child     | Spouse    |");
			System.out.println("+-----+--------------------+--------+-----------+-----+-------+------------+-----------+-----------+");
			for (Individual person : Indi_Date_Errors) {
				String child;
				if(!person.getChild().equals("NA")) {
					child = "{'"+person.getChild()+"'}";
				}else{
					child = "NA";
				}
				String spouse;
				if(!person.getSpouse().equals("NA")) {
					spouse = "{'"+person.getSpouse()+"'}";
				}else{
					spouse = "NA";
				}
				System.out.printf("|%-5s|%-20s|%-8s|%-11s|%-5d|%-7b|%-12s|%-11s|%-11s|%n", 
				person.getId(), person.getName(), person.getGender(), person.getBirthday(),
				person.getAge(), person.getIsAlive(), person.getDeath(),  child  ,  spouse );
			}
			System.out.println("+-----+--------------------+--------+-----------+-----+-------+------------+-----------+-----------+");
			System.out.println();
		}
		//Check dates in family list
		List<Family> Fam_Date_Errors = new ArrayList<>();
		boolean checkmar, checkdiv;
		for(Family one : familyList){
			if(!one.getDivorced().equals("NA")){
				checkdiv = isValid(one.getDivorced());
			}else checkdiv = true;
			checkmar = isValid(one.getMarried());
			if(checkmar && checkdiv == false){
				Fam_Date_Errors.add(one);
			}
		}
		if(Fam_Date_Errors.isEmpty()){
			System.out.println("No Date Error in Family List.\n");
		}else{
			System.out.println("Family Date Error List:");
			System.out.println("+-----+------------+------------+------------+--------------------+-----------+--------------------+--------------------+");
			System.out.println("| ID  | Married    | Divorced   | Husband ID | Husband Name       | Wife ID   | Wife Name          |   Childern         |");
			System.out.println("+-----+------------+------------+------------+--------------------+-----------+--------------------+--------------------+");
			for (Family family : Fam_Date_Errors) {
				String childString="";
				if(!(family.getChildren()==null)) {
					for(String child: family.getChildren()) {
						childString = childString+"','"+child;	
					}
					childString = childString.substring(2);
					childString = "{"+childString+"'}";
				}else {
					childString = "{'"+"NA"+"'}";
				}
				System.out.printf("|%-5s|%-12s|%-12s|%-12s|%-20s|%-11s|%-20s|%-20s|%n",
						family.getID(), family.getMarried(), family.getDivorced(), family.getHusbandID(), family.getHusbandName(),
						family.getWifeID(), family.getWifeName(), childString);
			}
			System.out.println("+-----+------------+------------+------------+--------------------+-----------+--------------------+--------------------+");
			System.out.println();
		}
	}
	
	public String takelastname(String name){
		return name.substring(name.indexOf('/')+1,name.lastIndexOf('/'));
	}

	public void checkFName(){
		System.out.println("\n---------------Sprint 1 Chengyi Zhang Story 2:----------------");
		System.out.println("For a family in which Divorce is NA, all members should share the same family name (Non-Chinese Families)\n");
		for(Family one: familyList){
			String lastname = takelastname(one.getHusbandName());
			System.out.println("Family: Last Name is "+ lastname);
			if(one.getDivorced().equals("NA")){
				System.out.println("This Family got divorced.\n");
				continue;
			}
			else{
				System.out.println("This Family is not divorced.");
				if(!takelastname(one.getWifeName()).equals(lastname)){
					System.out.println("This Family has different family names.\n");
					continue;
				}
				for(String ID : one.getChildren()){
					for(Individual person : individualList){
						if(person.getId().equals(ID)){
							if(!takelastname(person.getName()).equals(lastname)){
								System.out.println("This Family has different family names.\n");
								continue;
							}
							else{
								break;
							}
						}
					}
				}
			}
			System.out.println("This Family has no different family names.\n");
		}
	}
	
	// Chengyi Zhang part ends
	public void display() {
		System.out.println("Individuals");
		System.out.println("+-----+--------------------+--------+-----------+-----+-------+------------+-----------+-----------+");
		System.out.println("| ID  | Name               | Gender | Birthday  | Age | Alive | Death      | Child     | Spouse    |");
		System.out.println("+-----+--------------------+--------+-----------+-----+-------+------------+-----------+-----------+");
		for (Individual person : individualList) {
			 String child;
			 if(!person.getChild().equals("NA")) {
				 child = "{'"+person.getChild()+"'}";
			 }else {
				 child = "{'"+"NA"+"'}";
			 }
			 String spouse;
			 if(!person.getSpouse().equals("NA")) {
				 spouse = "{'"+person.getSpouse()+"'}";
			 }else {
				 spouse = "{'"+"NA"+"'}";
			 }
			System.out.printf("|%-5s|%-20s|%-8s|%-11s|%-5d|%-7b|%-12s|%-11s|%-11s|%n", 
					person.getId(), person.getName(), person.getGender(), person.getBirthday(),
					person.getAge(), person.getIsAlive(), person.getDeath(),  child  ,  spouse );
		}
		System.out.println("+-----+--------------------+--------+-----------+-----+-------+------------+-----------+-----------+");
		System.out.println();
		System.out.println("Families");
		System.out.println("+-----+------------+------------+------------+--------------------+-----------+--------------------+--------------------+");
		System.out.println("| ID  | Married    | Divorced   | Husband ID | Husband Name       | Wife ID   | Wife Name          |   Childern         |");
		System.out.println("+-----+------------+------------+------------+--------------------+-----------+--------------------+--------------------+");
		for (Family family : familyList) {
			String childString="";
			if(!(family.getChildren()==null)) {
				for(String child: family.getChildren()) {
					//for test to debug
					//System.out.println(child);
					childString = childString+"','"+child;	
				}
				childString = childString.substring(2);
				childString = "{"+childString+"'}";
			}else {
				childString = "{'"+"NA"+"'}";
			}
			System.out.printf("|%-5s|%-12s|%-12s|%-12s|%-20s|%-11s|%-20s|%-20s|%n",
					family.getID(), family.getMarried(), family.getDivorced(), family.getHusbandID(), family.getHusbandName(),
					family.getWifeID(), family.getWifeName(), childString);
		}
		System.out.println("+-----+------------+------------+------------+--------------------+-----------+--------------------+--------------------+");
		System.out.println();
	}
	
	public static void main(String[] args) {
		GedcomParse proj3 = new GedcomParse();
		proj3.readFile("/Users/wenxuanwang/Desktop/testForSprint2.ged");
		proj3.writeIntoIndividualList();
		proj3.writeIntofamilyList();
		//proj3.display();
		//System.out.println();
		
		//Shweta Singh US 27- list individual ages in table
		try {
			proj3.individualAge();
		} catch (ParseException e) {
			System.out.println("error:"+e.getMessage());
		}
		//
		System.out.println("Individuals------------Shweta Singh US 27-Include individual ages-------------------------");
		System.out.println("+-----+--------------------+--------+-----------+-----+-------+------------+-----------+-----------+");
		System.out.println("| ID  | Name               | Gender | Birthday  | Age | Alive | Death      | Child     | Spouse    |");
		System.out.println("+-----+--------------------+--------+-----------+-----+-------+------------+-----------+-----------+");
		for (Individual person : proj3.individualList) {
			 String child;
			 if(!person.getChild().equals("NA")) {
				 child = "{'"+person.getChild()+"'}";
			 }else {
				 child = "{'"+"NA"+"'}";
			 }
			 String spouse;
			 if(!person.getSpouse().equals("NA")) {
				 spouse = "{'"+person.getSpouse()+"'}";
			 }else {
				 spouse = "{'"+"NA"+"'}";
			 }
			System.out.printf("|%-5s|%-20s|%-8s|%-11s|%-5d|%-7b|%-12s|%-11s|%-11s|%n", 
					person.getId(), person.getName(), person.getGender(), person.getBirthday(),
					person.getAge(), person.getIsAlive(), person.getDeath(),  child  ,  spouse );
		}
		System.out.println("+-----+--------------------+--------+-----------+-----+-------+------------+-----------+-----------+");
		System.out.println();
		System.out.println("Families");
		System.out.println("+-----+------------+------------+------------+--------------------+-----------+--------------------+--------------------+");
		System.out.println("| ID  | Married    | Divorced   | Husband ID | Husband Name       | Wife ID   | Wife Name          |   Childern         |");
		System.out.println("+-----+------------+------------+------------+--------------------+-----------+--------------------+--------------------+");
		for (Family family : proj3.familyList) {
			String childString="";
			if(!(family.getChildren()==null)) {
				for(String child: family.getChildren()) {
					//for test to debug
					//System.out.println(child);
					childString = childString+"','"+child;	
				}
				childString = childString.substring(2);
				childString = "{"+childString+"'}";
			}else {
				childString = "{'"+"NA"+"'}";
			}
			System.out.printf("|%-5s|%-12s|%-12s|%-12s|%-20s|%-11s|%-20s|%-20s|%n",
					family.getID(), family.getMarried(), family.getDivorced(), family.getHusbandID(), family.getHusbandName(),
					family.getWifeID(), family.getWifeName(), childString);
		}
		System.out.println("+-----+------------+------------+------------+--------------------+-----------+--------------------+--------------------+");
		System.out.println();
		
		/*
		 * 							Sprint
		 */
		
		/**
		 * for sprint 1 Wenxuan Wang part
		 * story 1: birth before death
		 * story 2: Marriage before divorce
		 */
		System.out.println();
		proj3.US03checkBBD(proj3.individualList);
		proj3.US04checkMDB(proj3.familyList);
		
//		
//		//Shweta Singh US16 Male last names
		proj3.maleLastTime();
//		
//		//Shweta Singh US29 List deceased
		proj3.listDeceased();
		
		//Shweta Singh US30 List living married
		proj3.listLivingMarried();
//
		//Chengyi Zhang Story 1: All Dates must be valid
		proj3.checkDates();
//		//Chengyi Zhang Story 2: For a family in which Divorce is NA, all members should share the same family name (Non-Chinese Families)
		proj3.checkFName();
	}
}

//	
