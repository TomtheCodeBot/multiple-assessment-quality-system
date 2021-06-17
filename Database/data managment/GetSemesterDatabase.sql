CREATE DEFINER=`root`@`localhost` PROCEDURE `GetSemesterDatabase`()
BEGIN
	Select SCode,SName,AYName
    from semester natural join academic_year;
END
