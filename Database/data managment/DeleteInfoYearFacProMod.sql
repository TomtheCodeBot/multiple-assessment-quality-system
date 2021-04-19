CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteInfoYearFacProMod`(pro_fac_code VARCHAR(6), Mod_code VARCHAR(6))
BEGIN
	IF ((pro_fac_code,Mod_code) 
		IN (SELECT PFCode,MCode FROM aca_pro_mod)) THEN
	SELECT '1';
	DELETE FROM aca_pro_mod 
    WHERE PFCode=pro_fac_code AND MCode=Mod_code;
    
    ELSE
		SELECT '0';
    END IF;
	
END
