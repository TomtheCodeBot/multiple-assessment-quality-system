CREATE DEFINER=`root`@`localhost` PROCEDURE `GetUserDatabase`()
BEGIN
	SELECT Username 
    FROM user;
END
