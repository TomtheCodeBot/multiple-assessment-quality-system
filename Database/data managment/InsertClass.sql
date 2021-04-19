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
	IF MCode IN (SELECT M.MCode FROM module M)
		THEN IF SCode IN (SELECT S.SCode FROM semester S) 
			THEN IF CName NOT IN (SELECT C.CName FROM class C)
				THEN INSERT INTO class VALUES (CCode, CName, Size, MCode, SCode);
                SET a = 1;
			END IF;
		END IF;
	END IF;
    SELECT a;
END
