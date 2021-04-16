CREATE DEFINER=`root`@`localhost` FUNCTION `CheckProgram`(Login VARCHAR(20)) RETURNS varchar(20) CHARSET utf8mb4
BEGIN
	DECLARE flag varchar(20);
    SET flag = NULL;
	IF (Login) IN (SELECT A.Login FROM prog_cordinator Pc NATURAL JOIN user U NATURAL JOIN program Pr NATURAL JOIN account A WHERE Pc.Username = U.Username AND Pr.PCode = Pc.PCode AND A.Login = U.Login)
		THEN SET flag=(SELECT PName FROM prog_cordinator Pc NATURAL JOIN user U NATURAL JOIN program Pr NATURAL JOIN account A WHERE Pc.Username = U.Username AND Pr.PCode = Pc.PCode AND A.Login = U.Login AND A.Login = Login);
	ELSE SET flag = null;
	END IF;
RETURN flag;
END
