CREATE DEFINER=`root`@`localhost` PROCEDURE `CheckRole`(Login VARCHAR(20))
BEGIN
	DECLARE lecturer VARCHAR(20);
    DECLARE faculty VARCHAR(20);
    DECLARE program VARCHAR(20);
    SET lecturer = NULL;
    SET faculty = NULL;
    SET program = NULL;
    IF (Login) IN (SELECT A.Login FROM user U NATURAL JOIN lecturer L NATURAL JOIN account A WHERE L.LName = U.Username AND A.Login = U.Login)
		THEN SET lecturer=(SELECT L.LName FROM user U NATURAL JOIN lecturer L NATURAL JOIN account A WHERE L.LName = U.Username AND A.Login = U.Login AND A.Login = Login);
	END IF;
    IF (Login) IN (SELECT A.Login FROM prog_cordinator Pc NATURAL JOIN user U NATURAL JOIN program Pr NATURAL JOIN account A WHERE Pc.Username = U.Username AND Pr.PCode = Pc.PCode AND A.Login = U.Login)
		THEN SET program=(SELECT PName FROM prog_cordinator Pc NATURAL JOIN user U NATURAL JOIN program Pr NATURAL JOIN account A WHERE Pc.Username = U.Username AND Pr.PCode = Pc.PCode AND A.Login = U.Login AND A.Login = Login);
	END IF;
    IF (Login) IN (SELECT A.Login FROM dean D NATURAL JOIN user U NATURAL JOIN faculty F NATURAL JOIN account A WHERE D.Username = U.Username AND F.FCode = D.FCode AND A.Login = U.Login)
		THEN SET faculty=(SELECT FName from dean D NATURAL JOIN user U NATURAL JOIN faculty F NATURAL JOIN account A WHERE D.Username = U.Username AND F.FCode = D.FCode AND A.Login = U.Login and A.Login = Login);
	END IF;
    SELECT lecturer, faculty, program;
END
