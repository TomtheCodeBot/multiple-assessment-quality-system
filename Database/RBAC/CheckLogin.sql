CREATE DEFINER=`root`@`localhost` PROCEDURE `CheckLogin`(Login VARCHAR(20), Pass VARCHAR(20))
BEGIN
    DECLARE flag INT;
    SET flag = 0;
    IF (Login, Pass) IN (SELECT A.Login, A.Pass FROM account A)
    THEN SET flag=1;
    END IF;
    SELECT flag;											 
END
