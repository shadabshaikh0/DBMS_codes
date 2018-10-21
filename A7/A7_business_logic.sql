CREATE OR REPLACE PROCEDURE proc_grade(customerid in number,customername in varchar2,totalpurchase in number)
IS
	class varchar2(20);
BEGIN
	
	if(totalpurchase >= 10000 and totalpurchase <= 20000) then
		class := 'platinum';
	elsif(totalpurchase >= 5000 and totalpurchase <= 9999) then
		class := 'gold';
	elsif(totalpurchase >= 2000 and totalpurchase <= 4999) then
		class := 'silver';
	else
		class := 'other';
	end if;

	
	insert into category values(customerid,customername,class);

END;
/