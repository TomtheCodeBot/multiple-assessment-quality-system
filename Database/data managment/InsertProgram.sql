CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertProgram`(PName VARCHAR(50))
BEGIN
	DECLARE PCode VARCHAR(6);
    DECLARE a INT;
    SET a = 0;
    SET PCode = concat('P', floor(rand()*100000));
    WHILE PCode IN (SELECT P.PCode FROM program P)
		DO
			SET PCode = concat('P', floor(rand()*100000));
		END WHILE;
	IF PName NOT IN (SELECT P.PName FROM program P)
    THEN IF PCode NOT IN (SELECT P.PCode FROM program P) 
		THEN INSERT INTO program VALUES (PCode, PName);
        SET a = 1;
		END IF;
	END IF;
    SELECT a;
END
