CREATE DEFINER=`root`@`localhost` PROCEDURE `CheckRole`(Login VARCHAR(20))
BEGIN
    DECLARE faculty VARCHAR(20);
    DECLARE program VARCHAR(20);
    SET faculty = NULL;
    SET program = NULL;
    IF (Login) IN (SELECT A.Login 
		   FROM prog_cordinator Pc NATURAL 
		   	JOIN user U NATURAL 
		   	JOIN program Pr NATURAL 
		   	JOIN account A 
		  WHERE Pc.Username = U.Username AND Pr.PCode = Pc.PCode AND A.Login = U.Login)
		THEN SET program=(SELECT PName 
				  FROM prog_cordinator Pc NATURAL 
				  	JOIN user U NATURAL 
				  	JOIN program Pr NATURAL 
				  	JOIN account A 
				 WHERE Pc.Username = U.Username AND Pr.PCode = Pc.PCode AND A.Login = U.Login AND A.Login = Login);
	END IF;
								      
    IF (Login) IN (SELECT A.Login 
		   FROM dean D NATURAL 
		   	JOIN user U NATURAL 
		   	JOIN faculty F NATURAL 
		   	JOIN account A 
		   WHERE D.Username = U.Username AND F.FCode = D.FCode AND A.Login = U.Login)
		THEN SET faculty=(SELECT FName 
				 from dean D NATURAL 
				  	JOIN user U NATURAL 
				  	JOIN faculty F NATURAL 
				  	JOIN account A 
				  WHERE D.Username = U.Username AND F.FCode = D.FCode AND A.Login = U.Login and A.Login = Login);
	END IF;
								      
    SELECT faculty, program;
END
