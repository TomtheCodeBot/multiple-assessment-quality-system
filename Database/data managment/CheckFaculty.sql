CREATE DEFINER=`root`@`localhost` FUNCTION `CheckFaculty`(FCode VARCHAR(6), FName VARCHAR(50)) RETURNS int
BEGIN
	DECLARE flag INT;
    SET flag = 0;
    IF (FCode, FName) IN (SELECT F.FCode, F.FName FROM faculty F)
    THEN SET flag=1;
    END IF;
RETURN flag;
END
