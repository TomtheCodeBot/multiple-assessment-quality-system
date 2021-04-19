CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteInfoYearFac`(aca_fac_code VARCHAR(6))
BEGIN
	DECLARE flag INT;
    DECLARE size1 INT;
    SET size1 = (SELECT COUNT(*) FROM aca_faculty);
	DELETE FROM aca_faculty
	WHERE AFCode = aca_fac_code;
    IF size1 = (SELECT COUNT(*) FROM aca_faculty) THEN SELECT 0;
    ELSE SELECT 1;
    END IF;
END
