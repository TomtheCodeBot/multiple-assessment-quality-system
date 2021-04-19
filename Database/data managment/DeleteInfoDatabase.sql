CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteInfoDatabase`(parameter VARCHAR(30), Code VARCHAR(6), Name VARCHAR(50))
BEGIN
DECLARE Subdetails INT;
declare choice VARCHAR(30);
DECLARE a INT;
SET a = 0;
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
SET @Query:=CONCAT("SELECT COUNT(*)FROM ",choice," WHERE ",parameter,"Code = '",Code,"' AND ",parameter,"Name = '",Name,"'  INTO @b");
PREPARE stmt FROM @Query;
EXECUTE stmt;
SET Subdetails=@b;
DEALLOCATE PREPARE stmt; 

IF (Subdetails>0)THEN
        SET a=1;
        
		SET @Query:=CONCAT("DELETE FROM ",choice," WHERE ",parameter,"Code = '",Code,"' AND ",parameter,"Name = '",Name,"'");
		Prepare stmt FROM @Query;
        EXECUTE stmt;
	END IF;
    select a;
END
