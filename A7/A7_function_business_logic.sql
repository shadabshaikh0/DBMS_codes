CREATE OR REPLACE FUNCTION proc_gradefunc(customerid in number,customername in varchar2,totalpurchase in number)
RETURN varchar2
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

	RETURN class;
END;
/