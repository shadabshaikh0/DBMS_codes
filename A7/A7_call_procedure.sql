DECLARE

	CURSOR cursor_customer is SELECT * from customerdata;
	L_customerdata customerdata%ROWTYPE;
BEGIN
	
	FOR L_customerdata in cursor_customer
	LOOP

	proc_grade(L_customerdata.customerid,L_customerdata.name,L_customerdata.totalpurchase);
	
	END LOOP;
END;
/