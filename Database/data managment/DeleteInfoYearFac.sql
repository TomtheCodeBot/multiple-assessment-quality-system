CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteInfoYearFac`(aca_fac_code VARCHAR(6))
BEGIN
	DECLARE a INT;
    SET a = 0;
	IF ((aca_fac_code) IN (SELECT AFCode FROM aca_faculty)) THEN
		SET a= 1;
		DELETE FROM aca_faculty WHERE AFCode=aca_fac_code;
    END IF;
	SELECT a;
END
