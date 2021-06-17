CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertClass`(CName VARCHAR(50), Size INT, MCode VARCHAR(6), SCode VARCHAR(6))
BEGIN
	DECLARE CCode VARCHAR(6);
    DECLARE a INT;
    SET a = 0;
    SET CCode = concat('C', floor(rand()*100000));
    WHILE CCode IN (SELECT C.CCode FROM Class C)
		DO
			SET CCode = concat('C', floor(rand()*100000));
		END WHILE;
	IF CName!=''then
		IF (MCode,CCode) IN (SELECT MCode,CCode FROM  Aca_Faculty
			NATURAL JOIN Academic_year
			NATURAL JOIN Lecturer
			NATURAL JOIN Faculty 
			NATURAL JOIN aca_fac_pro
			NATURAL JOIN Program
			NATURAL JOIN aca_pro_mod
			NATURAL JOIN Module
			NATURAL JOIN Semester  )
			THEN IF CName NOT IN (SELECT C.CName FROM class C)
				THEN IF (MCode,CCode) NOT IN(SELECT MCode,CCode FROM class)
					THEN INSERT INTO class VALUES (CCode, CName, Size, MCode, SCode);
						SET a = 1;
				END IF;
			END IF;
		END IF;
	END IF;
    SELECT a;
END
