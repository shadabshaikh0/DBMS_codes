DECLARE
	L_rollno NUMBER := &L_rollno;
	L_nameofbook VARCHAR2(50) := &L_nameofbook;
	L_numberofdays INT;
	L_fineamount NUMBER;
	L_dateofissue DATE;
	L_status VARCHAR2(1);
	INVALID_DATE EXCEPTION;
	ALREADY_PAID EXCEPTION;

BEGIN
	SELECT status INTO L_status FROM customer WHERE rollno = L_rollno AND nameofbook = L_nameofbook;
	SELECT issuedate INTO L_dateofissue FROM customer WHERE rollno = L_rollno AND nameofbook = L_nameofbook;
	SELECT (CURRENT_DATE - L_dateofissue) INTO L_numberofdays FROM dual;

	IF L_status = 'P' THEN
		RAISE ALREADY_PAID;
	END IF;

	IF L_numberofdays < 0 THEN
		RAISE INVALID_DATE;
	END IF;

	IF L_numberofdays > 30 THEN
		L_fineamount := ((L_numberofdays-30) * 50) + (15 * 5) ;
	ELSIF L_numberofdays > 15 AND L_numberofdays < 30 THEN
		L_fineamount := (L_numberofdays-15) * 5;
	ELSE
		L_fineamount := 0;
	END IF;

	UPDATE customer SET status='P' WHERE rollno = L_rollno;

	IF L_fineamount > 0 THEN
		INSERT INTO fine VALUES(L_rollno,SYSDATE,L_fineamount);
	END IF;


EXCEPTION 
	WHEN INVALID_DATE THEN
		dbms_output.put_line('INVALID DATE');
	WHEN ALREADY_PAID THEN
		dbms_output.put_line('ALREADY_PAID');
	WHEN no_data_found THEN
		dbms_output.put_line('NO DATA FOUND');

END;
/