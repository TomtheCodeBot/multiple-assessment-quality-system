CREATE DEFINER=`root`@`localhost` FUNCTION `CheckClass`(CCode VARCHAR(6), CName VARCHAR(50), Size INT, MCode VARCHAR(6), SCode VARCHAR(6)) RETURNS int
BEGIN
	DECLARE flag INT;
    SET flag = 0;
    IF (CCode, CName, Size, MCode, SCode) IN (SELECT C.CCode, C.CName, C.Size, C.MCode, C.SCode FROM class C)
    THEN SET flag=1;
    END IF;
RETURN flag;
END
