CREATE DEFINER=`root`@`localhost` PROCEDURE `getInfoYearFac`()
BEGIN
SELECT AFCode,AYName,FName
FROM Aca_Faculty NATURAL JOIN academic_year NATURAL JOIN faculty
ORDER BY AFCode;
END
