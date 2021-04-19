CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteInfoYearFacProMod`(pro_fac_code VARCHAR(6), module_code VARCHAR(6))
BEGIN
	
    DECLARE flag INT;
    DECLARE size1 INT;
    SET size1 = (SELECT COUNT(*) FROM aca_pro_mod);
	DELETE FROM aca_pro_mod 
 	WHERE PFCode = pro_fac_code
	AND MCode = module_code;
    IF size1 = (SELECT COUNT(*) FROM aca_pro_mod) THEN SELECT 0;
    ELSE SELECT 1;
    END IF;
END
