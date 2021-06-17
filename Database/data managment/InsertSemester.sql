CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertSemester`(SName VARCHAR(50), AYCode VARCHAR(6))
BEGIN
	DECLARE SCode VARCHAR(6);
    DECLARE a INT;
    SET a = 0;
    SET SCode = concat('S', floor(rand()*100000));
    WHILE SCode IN (SELECT S.SCode FROM semester S)
		DO
			SET SCode = concat('S', floor(rand()*100000));
		END WHILE;
	IF SName !="" THEN
		IF AYCode IN (SELECT A.AYCode FROM academic_year A)
		THEN IF SName NOT IN (SELECT S.SName FROM semester S) 
			THEN INSERT INTO semester VALUES (SCode, SName, AYCode);
			SET a = 1;
			END IF;
		END IF;
	END IF;
    SELECT a;
END
