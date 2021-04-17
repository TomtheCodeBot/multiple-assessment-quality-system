
CREATE DEFINER=`root`@`localhost` PROCEDURE `CheckLecturer`(Login VARCHAR(20))
BEGIN
DECLARE lecturer varchar(20);    
  SET lecturer = NULL;
	IF (Login) IN (SELECT A.Login 
		       FROM user U 
		       		NATURAL JOIN lecturer L 
		       		NATURAL JOIN account A 
		       		NATURAL JOIN class C
		       WHERE L.LName = U.Username 
		       		AND A.Login = U.Login
		      		AND L.CCode = C.CCode)
	THEN SET lecturer = (SELECT L.LName 
			 FROM user U 
			 	NATURAL JOIN lecturer L 
			 	NATURAL JOIN account A 
			     	NATURAL JOIN class C
			 WHERE L.LName = U.Username 
			 	AND A.Login = U.Login 
			 	AND A.Login = Login
			    	AND L.CCode = C.CCode);
	END IF;
	SELECT lecturer;
END
