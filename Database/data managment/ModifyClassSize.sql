CREATE DEFINER=`root`@`localhost` PROCEDURE `ModifyClassSize`(NEWCCode VARCHAR(6), newsize INT)
BEGIN
	DECLARE AYCode VARCHAR(6);
    DECLARE a INT;
    SET a = 0;
	IF newsize>(SELECT COUNT(*) FROM Questionnaire 
		NATURAL JOIN Aca_Faculty
		NATURAL JOIN Academic_year
		NATURAL JOIN Lecturer
		NATURAL JOIN Faculty 
		NATURAL JOIN aca_fac_pro
		NATURAL JOIN Program
		NATURAL JOIN aca_pro_mod
		NATURAL JOIN Module
		NATURAL JOIN Class
		NATURAL JOIN Semester WHERE CCode=NEWCCode )
	Then UPDATE class
		SET Size=newsize
		WHERE CCode=NEWCCode;
        SET a=1;
	END IF;
    SELECT a;
END
