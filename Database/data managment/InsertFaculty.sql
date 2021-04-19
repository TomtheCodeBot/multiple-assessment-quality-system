CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertFaculty`(FName VARCHAR(50))
BEGIN
	DECLARE FCode VARCHAR(6);
    DECLARE a INT;
    SET a = 0;
    SET FCode = concat(floor(rand()*1000000));
	IF FName NOT IN (SELECT F.FName FROM faculty F)
    THEN IF FCode NOT IN (SELECT F.FCode FROM faculty F) 
		THEN INSERT INTO faculty VALUES (FCode, FName);
        SET a = 1;
		END IF;
	END IF;
    SELECT a;
END
