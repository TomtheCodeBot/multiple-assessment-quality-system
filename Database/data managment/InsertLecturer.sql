CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertLecturer`(LCode VARCHAR(6), LName VARCHAR(50), CCode VARCHAR(6))
BEGIN
	DECLARE flag INT;
    SET flag=0;
	SET flag =(SELECT SUM(CheckLecturerToInsert(LCode, LName, CCode)) FROM lecturer);
    IF flag=0 THEN
		SELECT 'Success';
		INSERT INTO lecturer (LCode, LName, CCode) VALUES (LCode, LName, CCode);
	ELSE 
		SELECT 'Duplicate';
    END IF;
END
