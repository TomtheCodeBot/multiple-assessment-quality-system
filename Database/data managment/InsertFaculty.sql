CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertFaculty`(FName VARCHAR(50))
BEGIN
	DECLARE FCode VARCHAR(6);
    DECLARE a INT;
    SET a = 0;
    SET FCode = concat('F', floor(rand()*100000));
    WHILE FCode IN (SELECT F.FCode FROM faculty F)
		DO
			SET FCode = concat('F', floor(rand()*100000));
		END WHILE;
	IF FName!=""then
		IF FName NOT IN (SELECT F.FName FROM faculty F)
		THEN IF FCode NOT IN (SELECT F.FCode FROM faculty F) 
			THEN INSERT INTO faculty VALUES (FCode, FName);
			SET a = 1;
			END IF;
		END IF;
	END IF;
    SELECT a;
END
