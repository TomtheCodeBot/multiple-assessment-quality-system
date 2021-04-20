CREATE DEFINER=`root`@`localhost` PROCEDURE `GetClassDatabase`()
BEGIN
	SELECT CCode,CName,Size,MName,SName
    from  Aca_Faculty
		NATURAL JOIN Academic_year
		NATURAL JOIN Lecturer
		NATURAL JOIN Faculty 
		NATURAL JOIN aca_fac_pro
		NATURAL JOIN Program
		NATURAL JOIN aca_pro_mod
		NATURAL JOIN Module
		NATURAL JOIN Class
		NATURAL JOIN Semester; 
END
