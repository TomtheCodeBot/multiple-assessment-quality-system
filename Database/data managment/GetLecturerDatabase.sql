CREATE DEFINER=`root`@`localhost` PROCEDURE `GetLecturerDatabase`()
BEGIN
	SELECT LCode,LName,CName
    from Class
		NATURAL JOIN Lecturer; 
END
