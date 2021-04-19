CREATE DEFINER=`root`@`localhost` PROCEDURE `CheckRole`(Login VARCHAR(20))
BEGIN
	DECLARE lecturer VARCHAR(20);
    DECLARE faculty VARCHAR(20);
    DECLARE program VARCHAR(20);
    SET lecturer = CheckLecturer(Login);
    SET faculty = CheckFaculty(Login);
    SET program = CheckProgram(Login);
    SELECT lecturer, faculty, program;
END
