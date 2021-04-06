CREATE DEFINER=`root`@`localhost` 
PROCEDURE `GetClassesLecturer`(class VARCHAR(50))
BEGIN
	set class=concat(class,'%'); 
    if class is NULL then 
		set class = '%';
	end if;
    
    SELECT LName AS Lecturer_Name
	FROM Class
		NATURAL JOIN Lecturer
	WHERE CName LIKE class;
END