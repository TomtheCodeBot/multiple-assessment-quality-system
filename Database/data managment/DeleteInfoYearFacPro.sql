CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteInfoYearFacPro`(pro_fac_code VARCHAR(6))
BEGIN
	DECLARE a INT;
    SET a = 0;
	IF ((pro_fac_code) IN (SELECT PFCode FROM aca_fac_pro)) THEN
		SET a=1;
		DELETE FROM aca_fac_pro 
		WHERE PFCode=pro_fac_code;
	END IF;
    SELECT a;
END
