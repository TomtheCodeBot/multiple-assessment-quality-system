CREATE DEFINER=`root`@`localhost` PROCEDURE `StoreQuestionnaireDetail`(classname VARCHAR(10), 
lecturername VARCHAR(20), attendance VARCHAR(10), gender VARCHAR(10), question_1 VARCHAR(10), 
question_2 VARCHAR(10), question_3 VARCHAR(10), question_4 VARCHAR(10), question_5 VARCHAR(10), 
question_6 VARCHAR(10), question_7 VARCHAR(10), question_8 VARCHAR(10), question_9 VARCHAR(10), 
question_10 VARCHAR(10), question_11 VARCHAR(10), question_12 VARCHAR(10), question_13 VARCHAR(10), 
question_14 VARCHAR(10), question_15 VARCHAR(10), question_16 VARCHAR(10), question_17 VARCHAR(10), 
question_18 VARCHAR(500))
BEGIN
	declare
    classcode varchar(10);
    declare
    lecturercode varchar(10);
	set classcode=(SELECT CCode from class where CName=classname);
	set lecturercode=(SELECT LCode from lecturer where LName=lecturername and CCode=classcode);
    INSERT INTO questionnaire (CCode, LCode, attend, gender, Q1, Q2, Q3, Q4, Q5, Q6, Q7, Q8, Q9, Q10, 
    Q11, Q12, Q13, Q14, Q15, Q16, Q17, Q18) 
    VALUES(classcode, lecturercode, attendance, gender, question_1, question_2, question_3, question_4, question_5, 
    question_6, question_7, question_8, question_9, question_10, question_11, question_12, question_13, 
    question_14, question_15, question_16, question_17, question_18);
END
