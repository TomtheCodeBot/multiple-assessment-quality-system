CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteInfoYearFacProMod`(academic_year_code VARCHAR(6), faculty_code VARCHAR(6), program_code VARCHAR(6), module_code VARCHAR(6))
BEGIN
	IF ((academic_year_code,faculty_code,program_code,module_code) 
		IN (SELECT AYCode,FCode,PCode,MCode FROM aca_pro_mod 
												NATURAL JOIN aca_fac_pro
                                                NATURAL JOIN aca_faculty)) THEN
	SELECT '1';
	DELETE FROM aca_pro_mod 
    WHERE PFCode LIKE (
		SELECT B.PFCode
        FROM aca_fac_pro B
         WHERE AFCode LIKE (
			SELECT A.AFCode
			FROM aca_faculty A
			WHERE A.AYCODE LIKE academic_year_code 
			AND A.FCode LIKE faculty_code)
		AND B.PCode LIKE program_code)
    AND MCode LIKE module_code;
    
    ELSE
		SELECT '0';
    END IF;
	
END