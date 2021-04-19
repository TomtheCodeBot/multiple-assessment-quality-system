CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteInfoYearFacPro`(pro_fac_code VARCHAR(6))
BEGIN
	DELETE FROM aca_fac_pro 
	WHERE PFCode = pro_fac_code;
END
