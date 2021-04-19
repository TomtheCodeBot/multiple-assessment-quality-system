CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertLecturer`(LName VARCHAR(50), CCode VARCHAR(6))
BEGIN
	DECLARE LCode VARCHAR(6);
    DECLARE a INT;
    SET a = 0;
    SET LCode = concat('L', floor(rand()*100000));
    WHILE LCode IN (SELECT L.LCode FROM lecturer L)
		DO
			SET LCode = concat('L', floor(rand()*100000));
		END WHILE;
	IF (LName, CCode) NOT IN (SELECT L.LName, L.CCode FROM lecturer L)
    THEN IF LCode NOT IN (SELECT L.LCode FROM lecturer L) 
		THEN INSERT INTO lecturer VALUES (LCode, LName, CCode);
        SET a = 1;
		END IF;
	END IF;
    SELECT a;
END
