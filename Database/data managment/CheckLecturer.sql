CREATE DEFINER=`root`@`localhost` FUNCTION `CheckLecturer`(Login VARCHAR(20)) RETURNS varchar(20) CHARSET utf8mb4
BEGIN
	DECLARE flag varchar(20);
    SET flag = NULL;
	IF (Login) IN (SELECT A.Login FROM user U NATURAL JOIN lecturer L NATURAL JOIN account A WHERE L.LName = U.Username AND A.Login = U.Login)
		THEN SET flag=(SELECT L.LName FROM user U NATURAL JOIN lecturer L NATURAL JOIN account A WHERE L.LName = U.Username AND A.Login = U.Login AND A.Login = Login);
	ELSE SET flag = null;
	END IF;
RETURN flag;
END
