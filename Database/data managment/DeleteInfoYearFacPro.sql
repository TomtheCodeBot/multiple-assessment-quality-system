CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteInfoYearFacPro`(pro_fac_code)
BEGIN
	IF ((pro_fac_code) IN (SELECT PFCode FROM aca_fac_pro)) THEN
		SELECT '1';
		DELETE FROM aca_fac_pro 
		WHERE pro_fac_code=PFCode;
	ELSE
		SELECT '0';
	END IF;
END
