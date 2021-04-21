CREATE DEFINER=`root`@`localhost` PROCEDURE `GetUser`()
BEGIN
	SELECT Username 
    FROM user;
END
