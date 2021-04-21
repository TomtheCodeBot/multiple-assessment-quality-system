CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertAcaFacProMod`(NEWPFCode VARCHAR(6),NEWMCode VARCHAR(6))
BEGIN
    DECLARE a INT;
    SET a = 0;
    IF NEWPFCode IN (SELECT A.PFCode FROM aca_fac_pro A)
    THEN IF NEWMCode IN (SELECT A.MCode FROM module A) 
		THEN IF (SELECT AYName FROM academic_year NATURAL JOIN 
        aca_faculty NATURAL JOIN aca_fac_pro  WHERE PFCode=NEWPFCode) NOT IN (SELECT AYName FROM academic_year NATURAL JOIN 
        aca_faculty NATURAL JOIN aca_fac_pro NATURAL JOIN aca_pro_mod WHERE MCode=NEWMCode)
			THEN
				SET a=1;
				INSERT INTO aca_pro_mod(PFCode,MCode) VALUES (NEWPFCode,NEWMCode);
			END IF;
		END IF;
	END IF;
    SELECT a;
END
