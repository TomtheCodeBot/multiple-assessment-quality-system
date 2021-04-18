CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteInfoYearFacProMod`(pro_fac_code VARCHAR(6), module_code VARCHAR(6))
BEGIN
	DELETE FROM aca_pro_mod 
 	WHERE PFCode LIKE pro_fac_code
	AND MCode LIKE module_code;
END
