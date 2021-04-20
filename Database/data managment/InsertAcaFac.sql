CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertAcaFac`(AYCode VARCHAR(6),FCode VARCHAR(6))
BEGIN
    DECLARE code int;
	DECLARE AFCode VARCHAR(6);
    DECLARE a INT;
    SET a = 0;
    SET code = (SELECT max(num)FROM(SELECT CAST(A.AFCode AS UNSIGNED)AS num FROM aca_faculty as A)as number);
    SET AFCode= CAST((code+1)as char(6));
	IF AYCode IN (SELECT A.AYCode FROM academic_year A)
    THEN IF FCode IN (SELECT A.FCode FROM faculty A) 
		THEN IF (AYCode,FCode) NOT IN (SELECT A.AYCode, A.FCode from aca_faculty as A)
			THEN
				INSERT INTO aca_faculty VALUES (AFCode,AYCode, FCode);
				SET a = 1;
			END IF;
		END IF;
	END IF;
    SELECT a;
END
