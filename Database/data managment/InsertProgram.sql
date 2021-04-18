CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertProgram`(PCode VARCHAR(6), PName VARCHAR(50))
BEGIN
	DECLARE flag INT;
    SET flag=0;
	SET flag =(SELECT SUM(CheckProgram(PCode, PName)) FROM program);
    IF flag=0 THEN
		SELECT 'Success';
		INSERT INTO program (PCode, PName) VALUES (PCode, PName);
	ELSE 
		SELECT 'Duplicate';
    END IF;
END
