CREATE DEFINER=`root`@`localhost` PROCEDURE `GetGenders`(academic_year VARCHAR(50), semester VARCHAR(50),
 faculty VARCHAR(50), program VARCHAR(50), module VARCHAR(50), lecturer VARCHAR(50), class VARCHAR(50))
BEGIN
	set academic_year=concat(academic_year,'%'); 
    if academic_year is NULL then 
		set academic_year = '%';
	end if;

	set semester=concat(semester,'%'); 
    if semester is NULL then 
		set semester = '%';
	end if;
    
    set faculty=concat(faculty,'%'); 
    if faculty is NULL then 
		set faculty = '%';
	end if;

	set program=concat(program,'%'); 
    if program is NULL then 
		set program = '%';
	end if;

	set module=concat(module,'%'); 
    if module is NULL then 
		set module = '%';
	end if;

	set lecturer=concat(lecturer,'%'); 
    if lecturer is NULL then 
		set lecturer = '%';
	end if;

	set class=concat(class,'%'); 
    if class is NULL then 
		set class = '%';
	end if;
	
	SELECT 
		SUM(case when gender = 'Male' then 1 else 0 end) AS MALE,
		SUM(case when gender = 'Female' then 1 else 0 end) AS FEMALE,
		SUM(case when gender = 'Other' then 1 else 0 end) AS OTHER
	FROM Questionnaire 
		NATURAL JOIN Aca_Faculty
		NATURAL JOIN Academic_year
		NATURAL JOIN Lecturer
		NATURAL JOIN Faculty 
		NATURAL JOIN aca_fac_pro
		NATURAL JOIN Program
		NATURAL JOIN aca_pro_mod
		NATURAL JOIN Module
		NATURAL JOIN Class
		NATURAL JOIN Semester 
	WHERE AYName LIKE academic_year
		AND SName LIKE semester
		AND FName LIKE faculty
		AND PName LIKE program
		AND MName LIKE module    
		AND CName LIKE class
		AND LName LIKE lecturer;
END