CREATE DEFINER=`root`@`localhost` FUNCTION `CheckSemester`(SCode VARCHAR(6), SName VARCHAR(50), AYCode VARCHAR(6)) RETURNS int
BEGIN
	DECLARE flag INT;
    SET flag = 0;
    IF (SCode, SName, AYCode) IN (SELECT S.SCode, S.SName, S.AYCode FROM semester S)
    THEN SET flag=1;
    END IF;
RETURN flag;
END
