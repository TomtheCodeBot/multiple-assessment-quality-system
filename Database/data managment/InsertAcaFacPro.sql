CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertAcaFacPro`(AFCode VARCHAR(6),PCode VARCHAR(6))
BEGIN
    DECLARE code int;
	DECLARE PFCode VARCHAR(6);
    DECLARE a INT;
    SET a = 0;
    SET code = (SELECT max(num)FROM(SELECT CAST(A.AFCode AS UNSIGNED)AS num FROM aca_faculty as A)as number);
    SET PFCode= CAST((code+1)as char(6));
	IF AFCode IN (SELECT A.AFCode FROM aca_faculty A)
    THEN IF PCode IN (SELECT A.PCode FROM program A) 
		THEN IF (AFCode,PCode) NOT IN (SELECT A.AFCode, A.PCode from aca_fac_pro as A)
			THEN
				INSERT INTO aca_fac_pro VALUES (PFCode,AFCode, PCode);
				SET a = 1;
			END IF;
		END IF;
	END IF;
    SELECT a;
END
