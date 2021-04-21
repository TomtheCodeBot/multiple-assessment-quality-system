CREATE DEFINER=`root`@`localhost` PROCEDURE `GetModuleForInsert`(NEWSCode VARCHAR(6))
BEGIN
	set NEWSCode=concat(NEWSCode,'%'); 
	if NEWSCode is NULL then 
		set NEWSCode = '%';
	end if;
    SELECT MCode,MName FROM  Aca_Faculty
		NATURAL JOIN Academic_year
		NATURAL JOIN Lecturer
		NATURAL JOIN Faculty 
		NATURAL JOIN aca_fac_pro
		NATURAL JOIN Program
		NATURAL JOIN aca_pro_mod
		NATURAL JOIN Module
		NATURAL JOIN Semester 
        WHERE SCode LIKE NEWSCode
        ;
END
