Individuals
+-----+--------------------+--------+-----------+-----+-------+------------+-----------+-----------+
| ID  | Name               | Gender | Birthday  | Age | Alive | Death      | Child     | Spouse    |
+-----+--------------------+--------+-----------+-----+-------+------------+-----------+-----------+
|I1   | Wenxuan /Test/     |M       |2030-10-1  |-10  |true   |NA          |{'F1'}     |{'NA'}     |
|I2   | Mingxiang /Wang/   |M       |1967-06-30 |53   |true   |NA          |{'F2'}     |{'F1'}     |
|I3   | Qiuxiang /Wang/    |F       |1966-07-13 |54   |true   |NA          |{'F3'}     |{'F1'}     |
|I4   | GuangSheng /Wang/  |M       |1930-10-10 |90   |false  |1984-07-15  |{'NA'}     |{'F2'}     |
|I5   | Xiaonian /Jiang/   |F       |1930-10-1  |90   |true   |NA          |{'NA'}     |{'F2'}     |
|I6   | Minghua /Wang/     |M       |1957-05-10 |63   |true   |NA          |{'F2'}     |{'F4'}     |
|I7   | Yinling /Wang/     |F       |1986-08-15 |34   |true   |NA          |{'F4'}     |{'NA'}     |
|I8   | Qiaoping /Zhang/   |F       |1960-06-9  |60   |true   |NA          |{'NA'}     |{'F4'}     |
|I9   | Qiling /Wang/      |M       |2024-10-8  |-4   |true   |NA          |{'F4'}     |{'NA'}     |
|I10  | Mingzheng /Wang/   |F       |1969-07-7  |51   |true   |NA          |{'F2'}     |{'F5'}     |
|I11  | Jiecheng /Chen/    |F       |1992-06-7  |28   |true   |NA          |{'F5'}     |{'NA'}     |
|I12  | Li /Chen/          |M       |1967-07-13 |53   |true   |NA          |{'F6'}     |{'F5'}     |
|I13  | Diedie /Wang/      |M       |1927-08-9  |93   |false  |1927-05-7   |{'NA'}     |{'F3'}     |
|I14  | Jiajia /Yao/       |F       |1929-06-10 |91   |false  |1911-07-21  |{'NA'}     |{'F3'}     |
|I15  | Chuanpeng /Wang/   |M       |1955-06-6  |65   |true   |NA          |{'F3'}     |{'F8'}     |
|I16  | Fei /Wang/         |M       |1982-08-9  |38   |true   |NA          |{'F7'}     |{'NA'}     |
|I17  | Zhen /Hu/          |F       |1960-08-8  |60   |false  |1982-06-12  |{'NA'}     |{'F7'}     |
|I18  | Jiangxun /Wang/    |M       |1988-09-9  |32   |true   |NA          |{'F8'}     |{'NA'}     |
|I19  | Jinghua /Du/       |F       |1962-04-9  |58   |true   |NA          |{'NA'}     |{'F8'}     |
|I20  | Ha /Chen/          |M       |1920-05-5  |100  |true   |NA          |{'NA'}     |{'F6'}     |
|I21  | Suibian /Chen/     |F       |1921-08-4  |99   |true   |NA          |{'NA'}     |{'F6'}     |
|I22  | Yong /Chen/        |M       |1955-03-12 |65   |true   |NA          |{'F6'}     |{'F9'}     |
|I23  | Huhu /Xiong/       |F       |1958-08-10 |62   |true   |NA          |{'NA'}     |{'F9'}     |
|I24  | Wenwen /Zha/       |M       |2001-07-7  |19   |true   |NA          |{'F2'}     |{'NA'}     |
|I25  | Yam /Hu/           |M       |2000-05-5  |20   |true   |NA          |{'F2'}     |{'NA'}     |
+-----+--------------------+--------+-----------+-----+-------+------------+-----------+-----------+

Families
+-----+------------+------------+------------+--------------------+-----------+--------------------+--------------------+
| ID  | Married    | Divorced   | Husband ID | Husband Name       | Wife ID   | Wife Name          |   Childern         |
+-----+------------+------------+------------+--------------------+-----------+--------------------+--------------------+
|F1   |1989-06-9   |NA          |I2          | Mingxiang /Wang/   |I3         | Qiuxiang /Wang/    |{'I1'}              |
|F2   |1952-06-8   |NA          |I4          | GuangSheng /Wang/  |I5         | Xiaonian /Jiang/   |{'I2','I6','I10','I24','I25'}|
|F3   |1949-08-8   |NA          |I13         | Diedie /Wang/      |I14        | Jiajia /Yao/       |{'I3','I15'}        |
|F4   |1984-09-14  |NA          |I6          | Minghua /Wang/     |I8         | Qiaoping /Zhang/   |{'I7','I9'}         |
|F5   |1988-09-11  |NA          |I12         | Li /Chen/          |I10        | Mingzheng /Wang/   |{'I11'}             |
|F6   |1940-06-4   |NA          |I20         | Ha /Chen/          |I21        | Suibian /Chen/     |{'I12','I22'}       |
|F7   |1978-03-14  |NA          |I15         | Chuanpeng /Wang/   |I17        | Zhen /Hu/          |{'I16'}             |
|F8   |1986-07-8   |NA          |I15         | Chuanpeng /Wang/   |I19        | Jinghua /Du/       |{'I18'}             |
|F9   |1980-06-5   |1978-06-6   |I22         | Yong /Chen/        |I23        | Huhu /Xiong/       |{'NA'}              |
+-----+------------+------------+------------+--------------------+-----------+--------------------+--------------------+


ERROR: INDIVIDUAL: US03: 138: I13: Died 1927-05-7 before born 1927-08-9
ERROR: INDIVIDUAL: US03: 149: I14: Died 1911-07-21 before born 1929-06-10
ERROR: FAMILY: US04: 330: F9: Divorced 1978-06-6 before married 1980-06-5

-------------Sprint 1-US16-Male last names-Shweta Singh---------------


ERROR: FAMILY: US16: 495: F1: Surname does not match for child: I1 :  Wenxuan Test

ERROR: FAMILY: US16: 495: F2: Surname does not match for child: I24 :  Wenwen Zha

ERROR: FAMILY: US16: 495: F2: Surname does not match for child: I25 :  Yam Hu

--------------Sprint 1-US29-List deceased-Shweta Singh----------------

Deceased Person: GuangSheng Wang
Deceased Person: Diedie Wang
Deceased Person: Jiajia Yao
Deceased Person: Zhen Hu

-----------------Sprint 1 Chengyi Zhang story 1:------------------------
All Dates must be valid

Individual Date Error List:
+-----+--------------------+--------+-----------+-----+-------+------------+-----------+-----------+
| ID  | Name               | Gender | Birthday  | Age | Alive | Death      | Child     | Spouse    |
+-----+--------------------+--------+-----------+-----+-------+------------+-----------+-----------+
|I1   | Wenxuan /Test/     |M       |2030-10-1  |-10  |true   |NA          |{'F1'}     |NA         |
|I2   | Mingxiang /Wang/   |M       |1967-06-30 |53   |true   |NA          |{'F2'}     |{'F1'}     |
|I3   | Qiuxiang /Wang/    |F       |1966-07-13 |54   |true   |NA          |{'F3'}     |{'F1'}     |
|I5   | Xiaonian /Jiang/   |F       |1930-10-1  |90   |true   |NA          |NA         |{'F2'}     |
|I6   | Minghua /Wang/     |M       |1957-05-10 |63   |true   |NA          |{'F2'}     |{'F4'}     |
|I7   | Yinling /Wang/     |F       |1986-08-15 |34   |true   |NA          |{'F4'}     |NA         |
|I8   | Qiaoping /Zhang/   |F       |1960-06-9  |60   |true   |NA          |NA         |{'F4'}     |
|I9   | Qiling /Wang/      |M       |2024-10-8  |-4   |true   |NA          |{'F4'}     |NA         |
|I10  | Mingzheng /Wang/   |F       |1969-07-7  |51   |true   |NA          |{'F2'}     |{'F5'}     |
|I11  | Jiecheng /Chen/    |F       |1992-06-7  |28   |true   |NA          |{'F5'}     |NA         |
|I12  | Li /Chen/          |M       |1967-07-13 |53   |true   |NA          |{'F6'}     |{'F5'}     |
|I15  | Chuanpeng /Wang/   |M       |1955-06-6  |65   |true   |NA          |{'F3'}     |{'F8'}     |
|I16  | Fei /Wang/         |M       |1982-08-9  |38   |true   |NA          |{'F7'}     |NA         |
|I18  | Jiangxun /Wang/    |M       |1988-09-9  |32   |true   |NA          |{'F8'}     |NA         |
|I19  | Jinghua /Du/       |F       |1962-04-9  |58   |true   |NA          |NA         |{'F8'}     |
|I20  | Ha /Chen/          |M       |1920-05-5  |100  |true   |NA          |NA         |{'F6'}     |
|I21  | Suibian /Chen/     |F       |1921-08-4  |99   |true   |NA          |NA         |{'F6'}     |
|I22  | Yong /Chen/        |M       |1955-03-12 |65   |true   |NA          |{'F6'}     |{'F9'}     |
|I23  | Huhu /Xiong/       |F       |1958-08-10 |62   |true   |NA          |NA         |{'F9'}     |
|I24  | Wenwen /Zha/       |M       |2001-07-7  |19   |true   |NA          |{'F2'}     |NA         |
|I25  | Yam /Hu/           |M       |2000-05-5  |20   |true   |NA          |{'F2'}     |NA         |
+-----+--------------------+--------+-----------+-----+-------+------------+-----------+-----------+

No Date Error in Family List.


---------------Sprint 1 Chengyi Zhang Story 2:----------------
For a family in which Divorce is NA, all members should share the same family name (Non-Chinese Families)

Family: Last Name is Wang
This Family got divorced.

Family: Last Name is Wang
This Family got divorced.

Family: Last Name is Wang
This Family got divorced.

Family: Last Name is Wang
This Family got divorced.

Family: Last Name is Chen
This Family got divorced.

Family: Last Name is Chen
This Family got divorced.

Family: Last Name is Wang
This Family got divorced.

Family: Last Name is Wang
This Family got divorced.

Family: Last Name is Chen
This Family is not divorced.
This Family has different family names.

