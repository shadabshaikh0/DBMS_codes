create table customer(
	rollno number,
	name varchar2(50),
	issuedate date,
	nameofbook varchar2(50),
	status varchar2(1)
);

create table fine(
	rollno number,
	returndate date,
	amount number
);
