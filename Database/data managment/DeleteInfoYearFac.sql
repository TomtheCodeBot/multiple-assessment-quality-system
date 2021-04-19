CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteInfoYearFac`(aca_fac_code VARCHAR(6))
BEGIN
	
	DELETE FROM aca_faculty 
	WHERE AFCode = aca_fac_code;
	
END
