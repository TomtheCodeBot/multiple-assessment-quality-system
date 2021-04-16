CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertSemester`(SCode VARCHAR(6), SName VARCHAR(50), AYCode VARCHAR(6))
BEGIN
	DECLARE flag INT;
    SET flag=0;
	SET flag =(SELECT SUM(CheckSemester(SCode, SName, AYCode)) FROM semester);
    IF flag=0 THEN
		SELECT 'Success';
		INSERT INTO semester (SCode, SName, AYCode) VALUES (SCode, SName, AYCode);
	ELSE 
		SELECT 'Duplicate';
    END IF;
END
