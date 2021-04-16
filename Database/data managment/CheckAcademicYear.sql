CREATE DEFINER=`root`@`localhost` FUNCTION `CheckAcademicYear`(AYCode VARCHAR(6), AYName VARCHAR(50)) RETURNS int
BEGIN
	DECLARE flag INT;
    SET flag = 0;
    IF (AYCode,AYName) IN (SELECT A.AYCode, A.AYName FROM academic_year A)
    THEN SET flag=1;
    END IF;
RETURN flag;
END