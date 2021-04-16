CREATE DEFINER=`root`@`localhost` FUNCTION `CheckLogin`(Login VARCHAR(20), Pass VARCHAR(20)) RETURNS int
BEGIN
	DECLARE flag INT;
    SET flag = 0;
    IF (Login, Pass) IN (SELECT A.Login, A.Pass FROM account A)
    THEN SET flag=1;
    END IF;
RETURN flag;
END
