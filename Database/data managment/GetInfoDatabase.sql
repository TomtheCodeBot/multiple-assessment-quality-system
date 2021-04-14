CREATE DEFINER=`root`@`localhost` PROCEDURE `GetInfoDatabase`(parameter VARCHAR(30))
BEGIN
declare choice VARCHAR(30);
set choice = parameter;

if parameter = "academic_year" then 
set parameter = 'AY';
end if;
if parameter = "faculty" then 
set parameter = 'F';
end if;
if parameter = "program" then 
set parameter = 'P';
end if;
if parameter = "module" then 
set parameter = 'M';
end if;
if parameter = "lecturer" then 
set parameter = 'L';
end if;
if parameter = "class" then 
set parameter = 'C';
end if;
if parameter = "semester" then 
set parameter = 'S';
end if;
SET @Query:=CONCAT("SELECT ",parameter,"Code, ",parameter,"Name
	FROM ",choice,"
    ORDER BY ",parameter,"Name;");
       
	Prepare stmt FROM @Query;  
	EXECUTE stmt;
END