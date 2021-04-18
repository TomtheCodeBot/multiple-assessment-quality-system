CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertModule`(MCode VARCHAR(6), MName VARCHAR(50))
BEGIN
	DECLARE flag INT;
    SET flag=0;
	SET flag =(SELECT SUM(CheckModule(MCode, MName)) FROM module);
    IF flag=0 THEN
		SELECT 'Success';
		INSERT INTO module (MCode, MName) VALUES (MCode, MName);
	ELSE 
		SELECT 'Duplicate';
    END IF;
END
