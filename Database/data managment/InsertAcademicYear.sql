CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertAcdemicYear`(AYName VARCHAR(50))
BEGIN
	DECLARE AYCode VARCHAR(6);
    DECLARE a INT;
    SET a = 0;
    SET AYCode = concat(floor(rand()*1000000));
    WHILE AYCode IN (SELECT A.AYCode FROM academic_year A)
		DO
			SET AYCode = concat(floor(rand()*1000000));
		END WHILE;
	IF AYName NOT IN (SELECT A.AYName FROM academic_year A)
    THEN IF AYCode NOT IN (SELECT A.AYCode FROM academic_year A) 
		THEN INSERT INTO academic_year VALUES (AYCode, AYName);
        SET a = 1;
		END IF;
	END IF;
    SELECT a;
END
