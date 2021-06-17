CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertModule`(MName VARCHAR(50))
BEGIN
	DECLARE MCode VARCHAR(6);
    DECLARE a INT;
    SET a = 0;
    SET MCode = concat('M', floor(rand()*100000));
    WHILE MCode IN (SELECT M.MCode FROM module M)
		DO
			SET MCode = concat('M', floor(rand()*100000));
		END WHILE;
	IF MName!="" THEN
		IF MName NOT IN (SELECT M.MName FROM module M)
		THEN IF MCode NOT IN (SELECT M.MCode FROM module M) 
			THEN INSERT INTO module VALUES (MCode, MName);
			SET a = 1;
			END IF;
		END IF;
	END IF;
    SELECT a;
END
