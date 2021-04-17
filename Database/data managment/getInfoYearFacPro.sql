CREATE DEFINER=`root`@`localhost` PROCEDURE `getInfoYearFacPro`()
BEGIN
SELECT PFCode,AYName, FName,PName
FROM aca_fac_pro NATURAL JOIN faculty NATURAL JOIN Aca_Faculty NATURAL JOIN academic_year NATURAL JOIN program
ORDER BY PFCode;
END
