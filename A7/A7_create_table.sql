create table customerdata(
	customerid number,
	name varchar2(20),
	totalpurchase number
);
create table category(
	customerid number,
	name varchar2(20),
	class varchar2(20)
);