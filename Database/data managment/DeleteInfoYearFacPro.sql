CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteInfoYearFacPro`(pro_fac_code VARCHAR(6))
BEGIN
	DECLARE flag INT;
    DECLARE size1 INT;
    SET size1 = (SELECT COUNT(*) FROM aca_fac_pro);
	DELETE FROM aca_fac_pro 
	WHERE PFCode = pro_fac_code;
    IF size1 = (SELECT COUNT(*) FROM aca_fac_pro) THEN SELECT 0;
    ELSE SELECT 1;
    END IF;
END
