CREATE DEFINER=`root`@`localhost` PROCEDURE `getInfoYearFacProMod`()
BEGIN
SELECT PFCode, MCode,AYName, FName,PName,MName
FROM aca_pro_mod NATURAL JOIN module NATURAL JOIN aca_fac_pro NATURAL JOIN faculty NATURAL JOIN Aca_Faculty NATURAL JOIN academic_year NATURAL JOIN program
ORDER BY PFCode, MCode;
END
