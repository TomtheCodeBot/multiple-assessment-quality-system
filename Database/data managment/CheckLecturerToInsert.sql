CREATE DEFINER=`root`@`localhost` FUNCTION `CheckLecturerToInsert`(LCode VARCHAR(6), LName VARCHAR(50),	CCode VARCHAR(6)) RETURNS int
BEGIN
	DECLARE flag INT;
    SET flag = 0;
    IF (LCode, LName, CCode) IN (SELECT L.LCode, L.LName, L.CCode FROM Lecturer L)
    THEN SET flag=1;
    END IF;
RETURN flag;
END
