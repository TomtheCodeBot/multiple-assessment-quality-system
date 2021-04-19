CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteInfoYearFac`(aca_fac_code VARCHAR(6))
BEGIN
	IF ((aca_fac_code) IN (SELECT AFCode FROM aca_faculty)) THEN
		SELECT '1';
		DELETE FROM aca_faculty WHERE AFCode=aca_fac_code;
    ELSE
		SELECT '0';
    END IF;
	
END
