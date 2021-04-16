CREATE DEFINER=`root`@`localhost` FUNCTION `CheckFaculty`(Login VARCHAR(20)) RETURNS varchar(20) CHARSET utf8mb4
BEGIN
	DECLARE flag varchar(20);
    SET flag = NULL;
	IF (Login) IN (SELECT A.Login FROM dean D NATURAL JOIN user U NATURAL JOIN faculty F NATURAL JOIN account A WHERE D.Username = U.Username AND F.FCode = D.FCode AND A.Login = U.Login)
		THEN SET flag=(SELECT FName from dean D NATURAL JOIN user U NATURAL JOIN faculty F NATURAL JOIN account A WHERE D.Username = U.Username AND F.FCode = D.FCode AND A.Login = U.Login and A.Login = Login);
	ELSE SET flag = null;
	END IF;
RETURN flag;
END
