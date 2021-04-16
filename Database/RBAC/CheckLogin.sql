CREATE DEFINER=`root`@`localhost` FUNCTION `CheckLogin`(Login VARCHAR(50)) RETURNS int
BEGIN
	DECLARE flag INT;
    SET flag = 0;
    IF (Login) IN (SELECT Login FROM account)
    THEN SET flag=1;
    END IF;
RETURN flag;
END
