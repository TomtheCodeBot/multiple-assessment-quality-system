CREATE DEFINER=`root`@`localhost` PROCEDURE `StoreQuestionnaireDetail`(class VARCHAR(50), lecturer VARCHAR(50), attendance VARCHAR(50), gender VARCHAR(50), question_1 VARCHAR(50), question_2 VARCHAR(50), question_3 VARCHAR(50), question_4 VARCHAR(50), question_5 VARCHAR(50), question_6 VARCHAR(50), question_7 VARCHAR(50), question_8 VARCHAR(50), question_9 VARCHAR(50), question_10 VARCHAR(50), question_11 VARCHAR(50), question_12 VARCHAR(50), question_13 VARCHAR(50), question_14 VARCHAR(50), question_15 VARCHAR(50), question_16 VARCHAR(50), question_17 VARCHAR(50), question_18 VARCHAR(50))
BEGIN
	
    INSERT INTO questionaire (CCode, LCode, attdend, gender, Q1, Q2, Q3, Q4, Q5, Q6, Q7, Q8, Q9, Q10, Q11, Q12, Q13, Q14, Q15, Q16, Q17, Q18) 
    VALUES(class, lecturer, attdendance, gender, question_1, question_2, question_3, question_4, question_5, question_6, question_7, question_8, question_9, question_10, question_11, question_12, question_13, question_14, question_15, question_16, question_17, question_18);
END