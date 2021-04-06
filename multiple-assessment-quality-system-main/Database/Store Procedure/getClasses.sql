CREATE DEFINER=`root`@`localhost` PROCEDURE `GetClasses`()
BEGIN
	SELECT CName
    FROM class;
END