CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertAcaFacPro`(NEWAFCode VARCHAR(6),NEWPCode VARCHAR(6))
BEGIN
    DECLARE code int;
	DECLARE NEWPFCode VARCHAR(6);
    DECLARE a INT;
    SET a = 0;
    SET code = (SELECT max(num)FROM(SELECT CAST(PFCode AS UNSIGNED)AS num FROM aca_fac_pro)as number);
    SET NEWPFCode= CAST((code+1)as char(6));
	IF NEWAFCode IN (SELECT A.AFCode FROM aca_faculty A)
    THEN IF NEWPCode IN (SELECT A.PCode FROM program A) 
		THEN IF (SELECT AYName FROM academic_year NATURAL JOIN 
        aca_faculty WHERE AFCode=NEWAFCode) NOT IN (SELECT AYName FROM academic_year NATURAL JOIN 
        aca_faculty NATURAL JOIN aca_fac_pro  WHERE PCode=NEWPCode)
			THEN
				INSERT INTO aca_fac_pro VALUES (NEWPFCode,NEWAFCode, NEWPCode);
				SET a = 1;
			END IF;
		END IF;
	END IF;
    SELECT a;
END
