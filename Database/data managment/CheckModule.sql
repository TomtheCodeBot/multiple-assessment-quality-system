CREATE DEFINER=`root`@`localhost` FUNCTION `CheckModule`(MCode VARCHAR(6), MName VARCHAR(50)) RETURNS int
BEGIN
	DECLARE flag INT;
    SET flag = 0;
    IF (MCode, MName) IN (SELECT M.MCode, M.MName FROM module M)
    THEN SET flag=1;
    END IF;
RETURN flag;
END
