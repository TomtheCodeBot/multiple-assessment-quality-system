CREATE DEFINER=`root`@`localhost` FUNCTION `CheckProgram`(PCode VARCHAR(6), PName VARCHAR(50)) RETURNS int
BEGIN
	DECLARE flag INT;
    SET flag = 0;
    IF (PCode, PName) IN (SELECT P.PCode, P.PName FROM program P)
    THEN SET flag=1;
    END IF;
RETURN flag;
END
