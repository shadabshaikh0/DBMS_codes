DECLARE

	CURSOR cursor_customer is SELECT * from customerdata;
	L_customerdata customerdata%ROWTYPE;
	L_class varchar2(20);
BEGIN
	
	FOR L_customerdata in cursor_customer
	LOOP

	L_class := proc_gradefunc(L_customerdata.customerid,L_customerdata.name,L_customerdata.totalpurchase);
	insert into category values(L_customerdata.customerid,L_customerdata.name,L_class);
		
	END LOOP;
END;
/