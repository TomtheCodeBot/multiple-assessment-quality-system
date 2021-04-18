
CREATE DEFINER=`root`@`localhost` PROCEDURE `CheckLecturer`(Login VARCHAR(20))
BEGIN
SELECT C.CName 
		FROM user U 
		NATURAL JOIN lecturer L 
		NATURAL JOIN account A 
		NATURAL JOIN class C
		WHERE L.LName = U.Username 
			 	AND A.Login = U.Login 
			 	AND A.Login = Login
			    	AND L.CCode = C.CCode;
END
