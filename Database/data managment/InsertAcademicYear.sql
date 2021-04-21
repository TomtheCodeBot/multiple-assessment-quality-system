CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertAcdemicYear`(AYName VARCHAR(50))
BEGIN
	DECLARE AYCode VARCHAR(6);
    DECLARE a INT;
    SET a = 0;
    SET AYCode = concat('A',floor(rand()*100000));
    WHILE AYCode IN (SELECT A.AYCode FROM academic_year A)
		DO
			SET AYCode = concat('A',floor(rand()*100000));
		END WHILE;
	IF AYName!="" THEN
		IF AYName NOT IN (SELECT A.AYName FROM academic_year A) AND concat('',AYName * 1) = AYName
		THEN IF AYCode NOT IN (SELECT A.AYCode FROM academic_year A) 
			THEN INSERT INTO academic_year VALUES (AYCode, AYName);
			SET a = 1;
			END IF;
		END IF;
	END IF;
    SELECT a;
END
