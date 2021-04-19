CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteInfoYearFac`(academic_year_code VARCHAR(6), faculty_code VARCHAR(6))
BEGIN
	IF ((academic_year_code,faculty_code) IN (SELECT AYCode,FCode FROM aca_faculty)) THEN
		SELECT '1';
		DELETE FROM aca_faculty WHERE AYCode = academic_year_code AND FCode = faculty_code;
    ELSE
		SELECT '0';
    END IF;
	
END