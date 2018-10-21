DECLARE
	
	empdata oldemptable%ROWTYPE;
	L_empid number;

	CURSOR cursor_old_emp is
		SELECT * from oldemptable;

	CURSOR cursor_new_emp(empid_number number) is
		SELECT empid from newemptable where empid = empid_number;


BEGIN

		FOR empdata in cursor_old_emp 
		LOOP 

			OPEN cursor_new_emp(empdata.empid);

			FETCH cursor_new_emp into L_empid;

			IF L_empid <> empdata.empid THEN
				insert into newemptable select * from oldemptable where empid = empdata.empid; 
			END IF;

			CLOSE cursor_new_emp;

		END LOOP;

END;
/