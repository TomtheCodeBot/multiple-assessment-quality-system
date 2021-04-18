CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertFaculty`(FCode VARCHAR(6), FName VARCHAR(50))
BEGIN
	DECLARE flag INT;
    SET flag=0;
	SET flag =(SELECT SUM(CheckFaculty(FCode, FName)) FROM faculty);
    IF flag=0 THEN
		SELECT 'Success';
		INSERT INTO faculty (FCode, FName) VALUES (FCode, FName);
	ELSE 
		SELECT 'Duplicate';
    END IF;
END
