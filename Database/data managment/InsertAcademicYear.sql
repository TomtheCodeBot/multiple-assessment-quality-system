CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertAcdemicYear`(AYCode VARCHAR(6), AYName VARCHAR(50))
BEGIN
	DECLARE flag INT;
    SET flag=0;
	SET flag =(SELECT SUM(CheckAcademicYear(AYCode,AYName)) FROM academic_year);
    IF flag=0 THEN
		SELECT 'Success';
		INSERT INTO academic_year (AYCode, AYName) VALUES (AYCode, AYName);
	ELSE 
		SELECT 'Duplicate';
    END IF;
END
