CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertClass`(CCode VARCHAR(6), CName VARCHAR(50), Size INT, MCode VARCHAR(6), SCode VARCHAR(6))
BEGIN
	DECLARE flag INT;
    SET flag=0;
	SET flag =(SELECT SUM(CheckClass(CCode, CName, Size, MCode, SCode)) FROM class);
    IF flag=0 THEN
		SELECT 'Success';
		INSERT INTO class (CCode, CName, Size, MCode, SCode) VALUES (CCode, CName, Size, MCode, SCode);
	ELSE 
		SELECT 'Duplicate';
    END IF;
END
