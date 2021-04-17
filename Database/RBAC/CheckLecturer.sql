
CREATE DEFINER=`root`@`localhost` PROCEDURE `CheckLecturer`(Login VARCHAR(20))
BEGIN
DECLARE lecturer varchar(20);    
  SET lecturer = NULL;
	IF (Login) IN (SELECT A.Login 
		       FROM user U 
		       		NATURAL JOIN lecturer L 
		       		NATURAL JOIN account A 
		       WHERE L.LName = U.Username 
		       		AND A.Login = U.Login)
	THEN SET lecturer = (SELECT L.LName 
			 FROM user U 
			 	NATURAL JOIN lecturer L 
			 	NATURAL JOIN account A 
			 WHERE L.LName = U.Username 
			 	AND A.Login = U.Login 
			 	AND A.Login = Login);
	END IF;
	SELECT lecturer;
END
