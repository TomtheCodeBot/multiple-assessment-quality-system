CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertAcaFacProMod`(PFCode VARCHAR(6),MCode VARCHAR(6))
BEGIN
    DECLARE a INT;
    SET a = 0;
    IF PFCode IN (SELECT A.PFCode FROM aca_fac_pro A)
    THEN IF MCode IN (SELECT A.MCode FROM module A) 
		THEN IF (PFCode,MCode) NOT IN (SELECT A.PFCode, A.MCode from aca_pro_mod as A)
			THEN
				SET a=1;
				INSERT INTO aca_pro_mod VALUES (PFCode,MCode);
			END IF;
		END IF;
	END IF;
    SELECT a;
END
