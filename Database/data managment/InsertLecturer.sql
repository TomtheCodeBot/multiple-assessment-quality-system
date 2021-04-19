CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertLecturer`(LName VARCHAR(50), CCode VARCHAR(6))
BEGIN
	DECLARE LCode VARCHAR(6);
    DECLARE a INT;
    SET a = 0;
    SET LCode = concat(floor(rand()*1000000));
	IF (LName, CCode) NOT IN (SELECT L.FName, L.CCode FROM lecturer L)
    THEN IF LCode NOT IN (SELECT L.LCode FROM lecturer L) 
		THEN INSERT INTO lecturer VALUES (LCode, LName, CCode);
        SET a = 1;
		END IF;
	END IF;
    SELECT a;
END
