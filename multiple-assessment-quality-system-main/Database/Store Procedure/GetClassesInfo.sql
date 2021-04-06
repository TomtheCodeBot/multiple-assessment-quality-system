CREATE DEFINER=`root`@`localhost` PROCEDURE `GetClassesInfo`(class VARCHAR(50))
BEGIN
	set class=concat(class,'%'); 
    if class is NULL then 
		set class = '%';
	end if;
    
    SELECT AYName AS Academic_Name, SName AS Semester_Name, 
    FName AS Faculty_Name, PName AS Program_Name, MName AS Module_Name
	FROM Academic_Year 
					NATURAL JOIN Aca_Faculty
                    NATURAL JOIN Faculty 
					NATURAL JOIN aca_fac_pro
					NATURAL JOIN Program
					NATURAL JOIN aca_pro_mod
					NATURAL JOIN Module
					NATURAL JOIN Class
					NATURAL JOIN Semester 
	WHERE CName LIKE class;
END