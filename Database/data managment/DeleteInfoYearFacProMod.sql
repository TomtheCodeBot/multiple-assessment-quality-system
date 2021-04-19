CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteInfoYearFacProMod`(pro_fac_code VARCHAR(6), Mod_code VARCHAR(6))
BEGIN
	DECLARE a INT;
    SET a = 0;
	IF ((pro_fac_code,Mod_code) 
		IN (SELECT PFCode,MCode FROM aca_pro_mod)) THEN
	SET a=1;
	DELETE FROM aca_pro_mod 
    WHERE PFCode=pro_fac_code AND MCode=Mod_code;
    END IF;
    SELECT a;
	
END
